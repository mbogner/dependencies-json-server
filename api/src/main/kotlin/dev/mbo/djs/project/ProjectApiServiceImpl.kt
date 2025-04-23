package dev.mbo.djs.project

import dev.mbo.djs.api.ProjectApiService
import dev.mbo.djs.model.ProjectDto
import dev.mbo.logging.logger
import org.springframework.stereotype.Service

@Service
class ProjectApiServiceImpl : ProjectApiService {

    private val log = logger()

    override fun processProject(id: String, projectDto: ProjectDto) {
        log.info("create or update project {}: {}", id, projectDto)
    }

}