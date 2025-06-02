dependencies {
    api(project(":zero-core"))
    api(project(":zero-world"))
    api(project(":zero-player"))

    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    api("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5")
    api("com.google.guava:guava:33.4.5-jre")
    api("it.unimi.dsi:fastutil:8.5.15")

    ksp(project(":zero-annotation-processor"))
}