import pw.qubique.infrastructure.build.feature.Feature

plugins {
    checkstyle
    kotlin("jvm") version "2.1.20"
    id("pw.qubique.infrastructure.build-system") version ("0.0.2-snapshot")
    id("com.google.devtools.ksp") version "2.1.20-1.0.32"
}

allprojects {
    apply(plugin = "checkstyle")
    apply(plugin = "pw.qubique.infrastructure.build-system")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "com.google.devtools.ksp")

    group = "net.zero"
    version = "1.0"

    repositories {
        mavenCentral()
        mavenLocal()
    }

    dependencies {
        api("org.jetbrains:annotations:26.0.1")
    }

    checkstyle {
        configFile = file("${rootDir}/infrastructure/checkstyle/checkstyle.xml")
    }

    build {
        features.set(
            listOf(
                Feature.SCM,
                Feature.TEST,
                Feature.CHECKSTYLE,
                Feature.JACOCO,
                Feature.JAVADOC,
                Feature.REPOSITORIES,
                Feature.PUBLISHING,
            )
        )
    }

    kotlin {
        jvmToolchain(21)
    }
}