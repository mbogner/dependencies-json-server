plugins {
    id("org.sonarqube")
    kotlin("jvm") apply false
    jacoco
    id("net.researchgate.release")
}

allprojects {
    group = "dev.mbo.djp.server"

    repositories {
        mavenLocal()
        mavenCentral()
        google()
    }

    tasks {
        val javaVersion: String by System.getProperties()

        withType<JavaCompile> {
            sourceCompatibility = javaVersion
            targetCompatibility = javaVersion
        }

        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            compilerOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.fromTarget(javaVersion)
            }
        }

        withType<Copy> {
            duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        }

        withType<Test> {
            useJUnitPlatform()
        }

        withType<JacocoReport> {
            reports {
                xml.required.set(true)
                html.required.set(false)
            }
        }

        withType<Wrapper> {
            val gradleReleaseVersion: String by System.getProperties()
            gradleVersion = gradleReleaseVersion
            distributionType = Wrapper.DistributionType.BIN
        }
    }
}

sonarqube {
    properties {
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.projectKey", "djs")
        property("sonar.projectName", "dependencies-json-server")
        property("sonar.sources", "src/main/kotlin,src/main/resources")
        property("sonar.exclusions", "**/src/gen/**/*")
    }
}

jacoco {
    val jacocoVersion: String by System.getProperties()
    toolVersion = jacocoVersion
}
