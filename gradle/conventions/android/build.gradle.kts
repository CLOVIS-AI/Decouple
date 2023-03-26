plugins {
    `kotlin-dsl`
}

group = "opensavvy.decouple"

repositories {
    google()
    mavenCentral()
}

dependencies {
    compileOnly(libs.gradle.android)
}
