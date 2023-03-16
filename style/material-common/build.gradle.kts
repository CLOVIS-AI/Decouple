@file:Suppress("UNUSED_VARIABLE")

plugins {
	alias(libs.plugins.kotlin)
	alias(libs.plugins.kotlinMpp)

	alias(libs.plugins.android)
	alias(libs.plugins.androidLibrary)

	alias(libs.plugins.compose)
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
