package dev.mbo.djs.global.jpa.base

import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Version
import java.io.Serializable

@Suppress("unused")
@MappedSuperclass
abstract class AbstractVersionedJpaEntity<T : Serializable>(

    @field:Version
    var lockVersion: Int = 0

) : AbstractMutableJpaEntity<T>()