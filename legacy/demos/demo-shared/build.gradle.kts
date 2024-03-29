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
				api(projects.extras.extraNavigation)
				implementation(projects.extras.extraPersistence)
			}
		}

		val commonTest by getting {
			dependencies {
				implementation(libs.kotlin.test)
				implementation(libs.kotlin.test.annotations)

				implementation(projects.styles.styleHeadless)
				implementation(libs.kotlinx.coroutines.test)
			}
		}

		val androidUnitTest by getting {
			dependencies {
				implementation(libs.kotlin.test.jvm)
			}
		}

		val jvmTest by getting {
			dependencies {
				implementation(libs.kotlin.test.jvm)
			}
		}

		val jsTest by getting {
			dependencies {
				implementation(libs.kotlin.test.js)
			}
		}
	}
}

android {
	namespace = "opensavvy.decouple.demo"
}
