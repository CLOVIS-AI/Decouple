plugins {
    `kotlin-dsl`
}

group = "opensavvy.decouple"

dependencies {
    compileOnly(libs.gradle.kotlin)

    implementation(projects.versioning)
    implementation(projects.documentation)
}
