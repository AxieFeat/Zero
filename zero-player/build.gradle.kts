dependencies {
    implementation(project(":zero-annotations"))
    implementation(project(":zero-core"))
    implementation(project(":zero-util"))
    implementation(project(":zero-world"))

    implementation("net.kyori:adventure-api:4.18.0")

    ksp(project(":zero-annotation-processor"))
}