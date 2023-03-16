@file:Suppress("UNUSED_VARIABLE")

plugins {
	alias(libs.plugins.kotlin)
	alias(libs.plugins.kotlinMpp)

	alias(libs.plugins.android)
	alias(libs.plugins.androidLibrary)

	alias(libs.plugins.compose)
}

kotlin {
	jvm()
	android()
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

android {
	namespace = "opensavvy.decouple.core"
}
