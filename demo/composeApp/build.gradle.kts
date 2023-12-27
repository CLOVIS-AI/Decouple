
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(opensavvyConventions.plugins.aligned.kotlin)
    alias(demoLibs.plugins.androidApplication)
    alias(opensavvyConventions.plugins.aligned.composeMultiplatform)
}

kotlin {
    // WASM is not yet supported in our Compose Multiplatform version
    // @OptIn(ExperimentalWasmDsl::class)
    // wasmJs {
    //     moduleName = "composeApp"
    //     browser {
    //         commonWebpackConfig {
    //             outputFileName = "composeApp.js"
    //         }
    //     }
    //     binaries.executable()
    // }
    
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    jvm("desktop")
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
        }

        androidMain.dependencies {
            implementation(compose.uiTooling)
            implementation(demoLibs.androidx.activity.compose)
        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}

android {
    namespace = "opensavvy.decouple.demo"
    compileSdk = demoLibs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "opensavvy.decouple.demo"
        minSdk = demoLibs.versions.android.minSdk.get().toInt()
        targetSdk = demoLibs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "opensavvy.decouple.demo"
            packageVersion = "1.0.0"
        }
    }
}

compose.experimental {
    web.application {}
}
