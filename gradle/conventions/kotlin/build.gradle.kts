plugins {
    `kotlin-dsl`
}

group = "opensavvy"

dependencies {
    compileOnly(libs.gradle.kotlin)

    implementation(projects.versioning)
    implementation(projects.documentation)
}
