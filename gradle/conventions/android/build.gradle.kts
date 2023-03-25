plugins {
    `kotlin-dsl`
}

group = "opensavvy"

repositories {
    google()
    mavenCentral()
}

dependencies {
    compileOnly(libs.gradle.android)
}
