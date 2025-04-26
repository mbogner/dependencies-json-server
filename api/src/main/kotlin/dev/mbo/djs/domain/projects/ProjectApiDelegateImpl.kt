package dev.mbo.djs.domain.projects

import dev.mbo.djs.api.ProjectApiDelegate
import dev.mbo.djs.model.ProjectDto
import dev.mbo.logging.logger
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class ProjectApiDelegateImpl(
    private val service: ProjectApiServiceImpl,
) : ProjectApiDelegate {

    private val log = logger()

    override fun processProject(
        key: String,
        projectDto: ProjectDto
    ): ResponseEntity<Unit> {
        log.info("POST /projects/{}: {}", key, projectDto)
        service.processProject(key, projectDto)
        return ResponseEntity.ok().build()
    }
}