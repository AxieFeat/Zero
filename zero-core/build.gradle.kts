dependencies {
    api(project(":zero-util"))
    api(project(":zero-annotations"))

    implementation("com.github.spotbugs:spotbugs-annotations:4.9.0")

    ksp(project(":zero-annotation-processor"))
}