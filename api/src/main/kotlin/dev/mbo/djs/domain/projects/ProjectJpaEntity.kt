package dev.mbo.djs.domain.projects

import dev.mbo.djs.domain.projects.imports.ProjectImportJpaEntity
import dev.mbo.djs.global.jpa.base.AbstractImmutableJpaEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.hibernate.annotations.NaturalId
import java.util.UUID

@Entity
@Table(name = "projects", uniqueConstraints = [UniqueConstraint(name = "projects_uc__key", columnNames = ["key"])])
class ProjectJpaEntity(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @field:NaturalId
    @field:Column(nullable = false, length = 64)
    var key: String,

    @field:OneToMany(mappedBy = "project")
    val imports: MutableSet<ProjectImportJpaEntity> = mutableSetOf()

) : AbstractImmutableJpaEntity<UUID>() {

    override fun getIdentifier(): UUID? {
        return id
    }

    override fun setIdentifier(id: UUID?) {
        this.id = id
    }

    override fun toStringAttributes(): Map<String, Any?> {
        return super.toStringAttributes()
            .plus("key" to key)
    }
}