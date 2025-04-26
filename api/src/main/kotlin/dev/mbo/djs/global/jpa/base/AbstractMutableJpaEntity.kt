package dev.mbo.djs.global.jpa.base

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PreUpdate
import java.io.Serializable
import java.time.Instant

@MappedSuperclass
abstract class AbstractMutableJpaEntity<T : Serializable>(
    createdAt: Instant = Instant.now(),

    @field:Column(name = "updated_at")
    var updatedAt: Instant = createdAt

) : AbstractImmutableJpaEntity<T>(
    createdAt = createdAt
) {

    @PreUpdate
    protected open fun preUpdate() {
        this.updatedAt = Instant.now()
    }

    override fun toStringAttributes(): Map<String, Any?> {
        return super.toStringAttributes()
            .plus("updatedAt" to updatedAt)
    }

}