@file:Suppress("UNUSED_VARIABLE")

import de.fayard.refreshVersions.core.versionFor

plugins {
	id("opensavvy.versioning")
	id("opensavvy.documentation")

	kotlin("multiplatform")
	id("org.jetbrains.compose")
	id("com.android.library")
}

kotlin {
	android()
	js(IR) {
		browser {
			testTask {
				useKarma {
					useChromeHeadless()
				}
			}
			commonWebpackConfig {
				cssSupport {
					enabled = true
				}
			}
		}
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				api(projects.core)
				api(projects.style.materialCommon)
			}
		}

		val jsMain by getting {
			dependencies {
				implementation(projects.style.materialTailwind)
			}
		}

		val androidMain by getting {
			dependencies {
				implementation(projects.style.materialAndroidx)
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
	namespace = "opensavvy.decouple.material"
}
