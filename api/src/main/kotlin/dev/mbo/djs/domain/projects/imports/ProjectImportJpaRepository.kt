package dev.mbo.djs.domain.projects.imports

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProjectImportJpaRepository : JpaRepository<ProjectImportJpaEntity, UUID> {
    fun existsByHash(hash: String): Boolean
}