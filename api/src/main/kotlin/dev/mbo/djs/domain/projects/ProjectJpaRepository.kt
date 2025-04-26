package dev.mbo.djs.domain.projects

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProjectJpaRepository : JpaRepository<ProjectJpaEntity, UUID> {
    fun findByKey(key: String): ProjectJpaEntity?
}