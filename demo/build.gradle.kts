@file:Suppress("UNUSED_VARIABLE")

plugins {
	kotlin("multiplatform")
	id("org.jetbrains.compose")
}

kotlin {
	jvm()
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
