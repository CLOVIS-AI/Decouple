@file:Suppress("UNUSED_VARIABLE")

import de.fayard.refreshVersions.core.versionFor
import java.net.URL

plugins {
	kotlin("multiplatform")
	id("com.android.library")

	id("org.jetbrains.compose")
	id("org.jetbrains.dokka")
}

kotlin {
	jvm()
	android()
	js(IR) {
		browser()
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				api(compose.runtime)
				api(KotlinX.coroutines.core)
				api(KotlinX.datetime)

				api("opensavvy:state:_")
				api("opensavvy:logger:_")
			}
		}
	}
}

android {
	compileSdk = versionFor("version.android.compileSdk").toIntOrNull()
	sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
	defaultConfig {
		minSdk = versionFor("version.android.minSdk").toIntOrNull()
		targetSdk = versionFor("version.android.targetSdk").toIntOrNull()
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	namespace = "opensavvy.decouple.core"
}

tasks.withType<org.jetbrains.dokka.gradle.DokkaTaskPartial>().configureEach {
	dokkaSourceSets.configureEach {
		includes.from("${project.projectDir}/core.md")

		sourceLink {
			localDirectory.set(file("src"))
			remoteUrl.set(URL("https://gitlab.com/opensavvy/decouple/-/blob/main/core/src"))
			remoteLineSuffix.set("#L")
		}
	}
}
