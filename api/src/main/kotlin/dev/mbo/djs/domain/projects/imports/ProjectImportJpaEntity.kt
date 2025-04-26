package dev.mbo.djs.domain.projects.imports

import dev.mbo.djs.domain.projects.ProjectJpaEntity
import dev.mbo.djs.global.jpa.base.AbstractCoordinateJpaEntity
import dev.mbo.djs.global.jpa.types.Coordinate
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "project_imports")
class ProjectImportJpaEntity(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @field:ManyToOne(optional = false)
    @field:JoinColumn(
        name = "project_id",
        nullable = false,
        foreignKey = ForeignKey(name = "project_imports_fk__project_id")
    )
    var project: ProjectJpaEntity,

    @field:NotBlank
    @field:Column(name = "import_hash", nullable = false, length = 512)
    var hash: String,

    coordinate: Coordinate,
    createdAt: Instant,
) : AbstractCoordinateJpaEntity<UUID>(
    coordinate = coordinate,
    createdAt = createdAt,
) {

    override fun getIdentifier(): UUID? {
        return id
    }

    override fun setIdentifier(id: UUID?) {
        this.id = id
    }

}