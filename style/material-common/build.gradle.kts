@file:Suppress("UNUSED_VARIABLE")

import de.fayard.refreshVersions.core.versionFor
import java.net.URL

plugins {
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
	namespace = "opensavvy.decouple.material.common"
}

tasks.withType<org.jetbrains.dokka.gradle.DokkaTaskPartial>().configureEach {
	dokkaSourceSets.configureEach {
		sourceLink {
			localDirectory.set(file("src"))
			remoteUrl.set(URL("https://gitlab.com/opensavvy/decouple/-/blob/main/style/material-common/src"))
			remoteLineSuffix.set("#L")
		}
	}
}
