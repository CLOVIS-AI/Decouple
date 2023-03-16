@file:Suppress("UNUSED_VARIABLE")

plugins {
	alias(libs.plugins.kotlin)
	alias(libs.plugins.kotlinMpp)

	alias(libs.plugins.android)
	alias(libs.plugins.androidLibrary)

	alias(libs.plugins.compose)
}

kotlin {
	android()

	sourceSets {
		val commonMain by getting {
			dependencies {
				api(projects.core)
				api(projects.style.materialCommon)
			}
		}

		val androidMain by getting {
			dependencies {
				implementation("androidx.compose.material3:material3:_")
				implementation("androidx.compose.material3:material3-window-size-class:_")
			}
		}
	}
}

android {
	namespace = "opensavvy.decouple.material.androidx"
}
