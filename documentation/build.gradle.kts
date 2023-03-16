@file:Suppress("UNUSED_VARIABLE")

plugins {
	alias(libs.plugins.kotlin)
	alias(libs.plugins.kotlinMpp)
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
