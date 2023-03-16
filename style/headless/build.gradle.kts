@file:Suppress("UNUSED_VARIABLE")

plugins {
	alias(libs.plugins.kotlin)
	alias(libs.plugins.kotlinMpp)

	alias(libs.plugins.compose)
}

kotlin {
	jvm()
	js(IR) {
		browser()
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				api(projects.core)
			}
		}

		val commonTest by getting {
			dependencies {
				api(Kotlin.test.annotationsCommon)
				api(Kotlin.test.common)

				api(KotlinX.coroutines.test)
			}
		}

		val jvmTest by getting {
			dependencies {
				api(Kotlin.test.junit)
			}
		}

		val jsTest by getting {
			dependencies {
				api(Kotlin.test.js)
			}
		}
	}
}
