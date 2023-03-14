@file:Suppress("UNUSED_VARIABLE")

plugins {
	id("opensavvy.kotlin")
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
				implementation(projects.core)
				implementation(projects.style.material)
			}
		}
	}
}
