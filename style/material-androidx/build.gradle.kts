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
				@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
				implementation(compose.material3)
			}
		}
	}
}

android {
	namespace = "opensavvy.decouple.material.androidx"
}
