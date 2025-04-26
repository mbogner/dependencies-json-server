package dev.mbo.djs.global.jpa.base

import jakarta.persistence.*
import org.springframework.data.util.ProxyUtils
import java.io.Serializable
import java.time.Instant

@MappedSuperclass
abstract class AbstractImmutableJpaEntity<T : Serializable>(

    @field:Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: Instant = Instant.now()

) : Identifiable<T> {

    /**
     * id only check for jpa entity
     */
    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (this === other) return true
        if (javaClass != ProxyUtils.getUserClass(other)) return false
        if (other !is AbstractImmutableJpaEntity<*>) return false
        val id = getIdentifier()
        return null != id && id == other.getIdentifier()
    }

    /**
     * @see <a href="https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier">external link</a>
     * for further infos why this is a static value.
     */
    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        return "${javaClass.simpleName}[${toStringAttributes()}]"
    }

    open fun toStringAttributes(): Map<String, Any?> {
        return mapOf(
            "id" to getIdentifier(),
            "createdAt" to createdAt
        )
    }

}