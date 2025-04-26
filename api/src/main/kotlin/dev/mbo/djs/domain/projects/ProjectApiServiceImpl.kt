package dev.mbo.djs.domain.projects

import dev.mbo.djs.api.ProjectApiService
import dev.mbo.djs.domain.configurations.ConfigurationJpaEntity
import dev.mbo.djs.domain.configurations.ConfigurationJpaRepository
import dev.mbo.djs.domain.dependencies.DependencyJpaEntity
import dev.mbo.djs.domain.dependencies.DependencyJpaRepository
import dev.mbo.djs.domain.projects.imports.ProjectImportJpaEntity
import dev.mbo.djs.domain.projects.imports.ProjectImportJpaRepository
import dev.mbo.djs.domain.usage.DependencyUsageJpaEntity
import dev.mbo.djs.domain.usage.DependencyUsageJpaRepository
import dev.mbo.djs.global.extensions.sha256
import dev.mbo.djs.global.jpa.types.Coordinate
import dev.mbo.djs.model.DependencyDto
import dev.mbo.djs.model.ProjectDto
import dev.mbo.logging.logger
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ProjectApiServiceImpl(
    private val projectJpaRepository: ProjectJpaRepository,
    private val projectImportJpaRepository: ProjectImportJpaRepository,
    private val configurationJpaRepository: ConfigurationJpaRepository,
    private val dependencyJpaRepository: DependencyJpaRepository,
    private val dependencyUsageJpaRepository: DependencyUsageJpaRepository,
) : ProjectApiService {

    private val log = logger()

    @Transactional
    override fun processProject(key: String, projectDto: ProjectDto) {
        log.debug("new project import {}: {}", key, projectDto)
        val coordinate = createCoordinateFromDto(projectDto)
        val timestampedCoordinateString = coordinate.timestampString(projectDto.timestamp)
        val hash = timestampedCoordinateString.sha256()
        if (projectImportJpaRepository.existsByHash(hash)) {
            log.warn("project already imported before: {}", timestampedCoordinateString)
            return
        }

        log.info("processing {}", timestampedCoordinateString)
        val project = projectJpaRepository.findByKey(key) ?: projectJpaRepository.save(ProjectJpaEntity(key = key))
        val projectImport = projectImportJpaRepository.save(
            ProjectImportJpaEntity(
                project = project,
                coordinate = coordinate,
                createdAt = projectDto.timestamp,
                hash = hash
            )
        )

        val configMap: Map<String, ConfigurationJpaEntity> = projectDto.dependencies.keys.map { configurationName ->
            configurationJpaRepository.findByName(configurationName) ?: configurationJpaRepository.save(
                ConfigurationJpaEntity(name = configurationName)
            )
        }.associateBy { it.name }

        projectDto.dependencies.forEach { configurationName: String, dependencies: List<DependencyDto> ->
            val configuration = configMap[configurationName]
                ?: throw IllegalStateException("configuration not found: $configurationName")

            dependencies.forEach { dependencyDto: DependencyDto ->
                val dtoCoordinate = Coordinate(
                    group = dependencyDto.group,
                    name = dependencyDto.name,
                    version = dependencyDto.version
                )
                val dependency = dependencyJpaRepository.findByCoordinates(dtoCoordinate.toString())
                    ?: dependencyJpaRepository.save(
                        DependencyJpaEntity(
                            coordinate = dtoCoordinate,
                        )
                    )
                dependencyUsageJpaRepository.save(
                    DependencyUsageJpaEntity(
                        projectImport = projectImport,
                        configuration = configuration,
                        dependency = dependency,
                    )
                )
            }
        }
    }

    fun createCoordinateFromDto(projectDto: ProjectDto): Coordinate {
        return Coordinate(
            group = projectDto.group,
            name = projectDto.name,
            version = projectDto.version
        )
    }

}