@file:Suppress("UNUSED_VARIABLE")

plugins {
	alias(libs.plugins.kotlin)
	alias(libs.plugins.kotlinMpp)

	alias(libs.plugins.android)
	alias(libs.plugins.androidLibrary)

	alias(libs.plugins.compose)
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
			commonWebpackConfig {
				cssSupport {
					enabled.set(true)
				}
			}
		}
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				api(projects.core)
				api(projects.styles.styleMaterialCommon)
			}
		}

		val jsMain by getting {
			dependencies {
				implementation(projects.styles.styleMaterialTailwind)
			}
		}

		val androidMain by getting {
			dependencies {
				implementation(projects.styles.styleMaterialAndroidx)
			}
		}

		val jvmMain by getting {
			dependencies {
				implementation(projects.styles.styleMaterialAndroidx)
			}
		}
	}
}

android {
	namespace = "opensavvy.decouple.material"
}
