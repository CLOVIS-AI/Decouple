@file:Suppress("UNUSED_VARIABLE")

plugins {
	id("opensavvy.kotlin")
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
	}
}
