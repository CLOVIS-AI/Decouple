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
	namespace = "opensavvy.decouple.demo"
}
