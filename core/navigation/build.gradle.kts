@file:Suppress("UNUSED_VARIABLE")

plugins {
	id("opensavvy.versioning")
	id("opensavvy.documentation")

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
				api(projects.core)
			}
		}
	}
}
