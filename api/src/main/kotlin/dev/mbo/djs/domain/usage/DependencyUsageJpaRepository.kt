package dev.mbo.djs.domain.usage

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface DependencyUsageJpaRepository : JpaRepository<DependencyUsageJpaEntity, UUID> {
}