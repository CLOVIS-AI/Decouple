@file:Suppress("UNUSED_VARIABLE")

import java.net.URL

plugins {
	kotlin("multiplatform")
	id("org.jetbrains.compose")
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

tasks.withType<org.jetbrains.dokka.gradle.DokkaTaskPartial>().configureEach {
	dokkaSourceSets.configureEach {
		includes.from("${project.projectDir}/README.md")

		sourceLink {
			localDirectory.set(file("src"))
			remoteUrl.set(URL("https://gitlab.com/opensavvy/decouple/-/blob/main/style/testing/src"))
			remoteLineSuffix.set("#L")
		}
	}
}
