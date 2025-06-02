rootProject.name = "Zero"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        repositories {
            maven {
                name = "Ci-Cd"
                url = uri("https://gitlab.qubique.pw/api/v4/groups/4/-/packages/maven")
                credentials(HttpHeaderCredentials::class) {
                    value = System.getenv("CI_JOB_TOKEN").also {
                        name = "Job-Token"
                    } ?: System.getenv("QUBIQUE_GITLAB_PRIVATE_TOKEN").also {
                        name = "Private-Token"
                    } ?: throw RuntimeException("Token not found!")
                }
                authentication {
                    create<HttpHeaderAuthentication>("header")
                }
            }
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

include("zero-core")
include("zero-util")
include("zero-annotations")
include("zero-world")
include("zero-player")
include("zero-common")
include("zero-command")
include("zero-item")
include("zero-annotation-processor")
