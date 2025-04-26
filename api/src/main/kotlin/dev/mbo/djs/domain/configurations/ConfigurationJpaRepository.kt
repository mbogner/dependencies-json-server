package dev.mbo.djs.domain.configurations

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ConfigurationJpaRepository : JpaRepository<ConfigurationJpaEntity, UUID> {
    fun findByName(name: String): ConfigurationJpaEntity?
}