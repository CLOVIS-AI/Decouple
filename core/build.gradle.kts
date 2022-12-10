@file:Suppress("UNUSED_VARIABLE")

import java.net.URL

plugins {
	kotlin("multiplatform")
	id("org.jetbrains.compose")
	id("org.jetbrains.dokka")
}

kotlin {
	jvm()
	js(IR) {
		browser()
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				api(compose.runtime)
				api(KotlinX.coroutines.core)
				api(KotlinX.datetime)

				api("opensavvy:state:_")
				api("opensavvy:logger:_")
			}
		}
	}
}

tasks.withType<org.jetbrains.dokka.gradle.DokkaTaskPartial>().configureEach {
	dokkaSourceSets.configureEach {
		includes.from("${project.projectDir}/core.md")

		sourceLink {
			localDirectory.set(file("src"))
			remoteUrl.set(URL("https://gitlab.com/opensavvy/decouple/-/blob/main/core/src"))
			remoteLineSuffix.set("#L")
		}
	}
}
