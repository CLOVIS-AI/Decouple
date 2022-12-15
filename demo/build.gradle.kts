@file:Suppress("UNUSED_VARIABLE")

plugins {
	kotlin("multiplatform")
	id("org.jetbrains.compose")
}

kotlin {
	js(IR) {
		browser()
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				api(projects.core)
				api(projects.core.navigation)
			}
		}
	}
}
