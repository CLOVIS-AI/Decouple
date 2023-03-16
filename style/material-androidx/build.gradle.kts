@file:Suppress("UNUSED_VARIABLE")

plugins {
	id("opensavvy.kotlin")
	kotlin("multiplatform")
	id("org.jetbrains.compose")

	id("com.android.library")
	id("opensavvy.android")
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
