package dev.mbo.djs.domain.usage

import dev.mbo.djs.domain.configurations.ConfigurationJpaEntity
import dev.mbo.djs.domain.dependencies.DependencyJpaEntity
import dev.mbo.djs.domain.projects.imports.ProjectImportJpaEntity
import dev.mbo.djs.global.jpa.base.AbstractImmutableJpaEntity
import jakarta.persistence.Entity
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID


@Entity
@Table(name = "dependency_usages")
class DependencyUsageJpaEntity(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @field:ManyToOne(optional = false)
    @field:JoinColumn(
        name = "project_import_id",
        nullable = false,
        foreignKey = ForeignKey(name = "dependency_usages_fk__project_import_id")
    )
    var projectImport: ProjectImportJpaEntity,

    @field:ManyToOne(optional = false)
    @field:JoinColumn(
        name = "dependency_id",
        nullable = false,
        foreignKey = ForeignKey(name = "dependency_usages_fk__dependency_id")
    )
    var dependency: DependencyJpaEntity,

    @field:ManyToOne(optional = false)
    @field:JoinColumn(
        name = "configuration_id",
        nullable = false,
        foreignKey = ForeignKey(name = "dependency_usages_fk__configuration_id")
    )
    var configuration: ConfigurationJpaEntity

) : AbstractImmutableJpaEntity<UUID>() {

    override fun getIdentifier(): UUID? {
        return id
    }

    override fun setIdentifier(id: UUID?) {
        this.id = id
    }

}