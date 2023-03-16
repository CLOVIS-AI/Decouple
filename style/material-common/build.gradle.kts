@file:Suppress("UNUSED_VARIABLE")

plugins {
	id("opensavvy.kotlin")
	kotlin("multiplatform")
	id("org.jetbrains.compose")

	id("com.android.library")
	id("opensavvy.android")
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
	namespace = "opensavvy.decouple.material.common"
}
