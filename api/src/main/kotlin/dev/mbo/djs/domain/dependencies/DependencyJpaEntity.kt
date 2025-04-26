package dev.mbo.djs.domain.dependencies

import dev.mbo.djs.global.jpa.base.AbstractCoordinateJpaEntity
import dev.mbo.djs.global.jpa.types.Coordinate
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.util.UUID

@Entity
@Table(
    name = "dependencies", uniqueConstraints = [
        UniqueConstraint(name = "dependencies_uc__coordinates", columnNames = ["coordinates"]),
    ]
)
class DependencyJpaEntity(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    coordinate: Coordinate
) : AbstractCoordinateJpaEntity<UUID>(coordinate) {

    override fun getIdentifier(): UUID? {
        return id
    }

    override fun setIdentifier(id: UUID?) {
        this.id = id
    }

}