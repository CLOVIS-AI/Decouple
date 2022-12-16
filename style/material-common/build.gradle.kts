@file:Suppress("UNUSED_VARIABLE")

import java.net.URL

plugins {
	kotlin("multiplatform")
	id("org.jetbrains.compose")
}

kotlin {
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

tasks.withType<org.jetbrains.dokka.gradle.DokkaTaskPartial>().configureEach {
	dokkaSourceSets.configureEach {
		sourceLink {
			localDirectory.set(file("src"))
			remoteUrl.set(URL("https://gitlab.com/opensavvy/decouple/-/blob/main/style/material-common/src"))
			remoteLineSuffix.set("#L")
		}
	}
}
