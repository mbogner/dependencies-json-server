package dev.mbo.djs.domain.dependencies

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface DependencyJpaRepository : JpaRepository<DependencyJpaEntity, UUID> {
    fun findByCoordinates(coordinates: String): DependencyJpaEntity?
}