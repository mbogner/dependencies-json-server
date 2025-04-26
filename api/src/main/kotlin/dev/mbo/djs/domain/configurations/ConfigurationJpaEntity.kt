package dev.mbo.djs.domain.configurations

import dev.mbo.djs.global.jpa.base.AbstractImmutableJpaEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import jakarta.validation.constraints.NotNull
import org.hibernate.annotations.NaturalId
import java.util.UUID

@Entity
@Table(
    name = "configurations", uniqueConstraints = [
        UniqueConstraint(name = "configurations_uc__name", columnNames = ["name"])
    ]
)
class ConfigurationJpaEntity(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @field:NaturalId
    @field:NotNull
    @field:Column(nullable = false, length = 64)
    var name: String

) : AbstractImmutableJpaEntity<UUID>() {

    override fun getIdentifier(): UUID? {
        return id
    }

    override fun setIdentifier(id: UUID?) {
        this.id = id
    }

    override fun toStringAttributes(): Map<String, Any?> {
        return super.toStringAttributes()
            .plus("name" to name)
    }
}