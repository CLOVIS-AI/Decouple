
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(opensavvyConventions.plugins.aligned.kotlin)
    alias(demoLibs.plugins.androidApplication)
    alias(opensavvyConventions.plugins.aligned.composeMultiplatform)
    alias(opensavvyConventions.plugins.aligned.composeCompiler)

    alias(demoLibs.plugins.vite)
    alias(demoLibs.plugins.resources.consumer)
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

    js(IR) {
        browser()
        binaries.executable()
    }
    
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
            implementation(projects.components)
        }

        androidMain.dependencies {
            implementation(compose.uiTooling)
            implementation(demoLibs.androidx.activity.compose)
        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(projects.designs.designMaterial3Androidx)
        }

        jsMain.dependencies {
            implementation(compose.html.core)
            implementation(projects.designs.designPureCss)

            implementation(projects.designs.designMaterial3Html)
            implementation(devNpm("tailwindcss", demoLibs.versions.tailwindcss.get()))
            implementation(devNpm("postcss", demoLibs.versions.postcss.get()))
            implementation(devNpm("autoprefixer", demoLibs.versions.autoprefixer.get()))
            implementation(devNpm("caniuse-lite", demoLibs.versions.caniuse.lite.get()))
            implementation(devNpm("browserslist", demoLibs.versions.browserslist.get()))
        }

        iosMain.dependencies {
            implementation(projects.designs.designMaterial3Androidx)
        }

        all {
            languageSettings.optIn("opensavvy.decouple.components.RestrictedStabilityArgument")
            languageSettings.optIn("opensavvy.decouple.components.ExperimentalComponent")
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
        mainClass = "opensavvy.decouple.demo.MainKt"

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

dependencies {
    transitiveJsResources(libs.material3.html.config)
}
