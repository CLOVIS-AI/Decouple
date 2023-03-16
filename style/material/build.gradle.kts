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
	namespace = "opensavvy.decouple.material"
}
