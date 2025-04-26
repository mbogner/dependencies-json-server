package dev.mbo.djs.global.jpa.types

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.validation.constraints.NotBlank
import java.time.Instant

@Suppress("JpaDataSourceORMInspection")
@Embeddable
data class Coordinate(
    @field:NotBlank
    @field:Column(name = "maven_group", length = 128, nullable = false)
    val group: String,

    @field:NotBlank
    @field:Column(name = "maven_name", length = 64, nullable = false)
    val name: String,

    @field:NotBlank
    @field:Column(name = "maven_version", length = 32, nullable = false)
    val version: String,
) {

    companion object {
        const val MAX_LENGTH = 128 + 64 + 32 + 2
    }

    override fun toString(): String {
        return "$group:$name:$version"
    }

    fun timestampString(ts: Instant): String {
        return "${this}:${ts}"
    }

}