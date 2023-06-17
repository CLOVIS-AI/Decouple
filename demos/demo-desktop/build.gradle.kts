plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlinJvm)

    alias(libs.plugins.compose)
}

dependencies {
    implementation(projects.demos.demoShared)
    implementation(projects.styles.styleMaterial)

    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.ui)
    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application.mainClass = "opensavvy.decouple.demo.desktop.MainKt"
}
