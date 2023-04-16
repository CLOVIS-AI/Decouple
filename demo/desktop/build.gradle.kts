plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlinJvm)

    alias(libs.plugins.compose)
}

dependencies {
    implementation(projects.demo)
    implementation(projects.style.material)

    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.ui)
    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application.mainClass = "opensavvy.decouple.demo.desktop.MainKt"
}
