plugins {
    kotlin("jvm") version "2.1.20"
}

subprojects {
    group = "net.zero"
    version = "1.0"

    repositories {
        mavenCentral()
    }

    kotlin {
        jvmToolchain(21)
    }
}