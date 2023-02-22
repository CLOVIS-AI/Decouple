@file:Suppress("UNUSED_VARIABLE")

import de.fayard.refreshVersions.core.versionFor

plugins {
	kotlin("multiplatform")
	id("org.jetbrains.compose")
	id("com.android.library")
}

kotlin {
	jvm()
	android()
	js(IR) {
		browser {
			testTask {
				useKarma {
					useChromeHeadless()
				}
			}
		}
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				api(projects.core)
				api(projects.core.navigation)
				implementation(projects.core.persist)
			}
		}

		val commonTest by getting {
			dependencies {
				implementation(Kotlin.test.common)
				implementation(Kotlin.test.annotationsCommon)
			}
		}

		val androidTest by getting {
			dependencies {
				implementation(Kotlin.test.junit)
			}
		}

		val jvmTest by getting {
			dependencies {
				implementation(Kotlin.test.junit)
			}
		}

		val jsTest by getting {
			dependencies {
				implementation(Kotlin.test.js)
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
	namespace = "opensavvy.decouple.demo"
}
