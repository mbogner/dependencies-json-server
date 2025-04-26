package dev.mbo.djs.global.jpa.base

import dev.mbo.djs.global.jpa.types.Coordinate
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.MappedSuperclass
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.annotations.NaturalId
import java.io.Serializable
import java.time.Instant

@MappedSuperclass
abstract class AbstractCoordinateJpaEntity<T : Serializable>(
    @field:NotNull
    @field:Embedded
    val coordinate: Coordinate,

    @field:NaturalId
    @field:NotBlank
    @field:Column(nullable = false, length = Coordinate.Companion.MAX_LENGTH)
    val coordinates: String = coordinate.toString(),

    createdAt: Instant = Instant.now()
) : AbstractImmutableJpaEntity<T>(
    createdAt = createdAt
) {

    override fun toStringAttributes(): Map<String, Any?> {
        return super.toStringAttributes()
            .plus("coordinate" to coordinate)
            .plus("coordinates" to coordinates)
    }

}