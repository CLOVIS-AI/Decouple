@file:Suppress("UNUSED_VARIABLE")

plugins {
	id("opensavvy.kotlin")
	kotlin("multiplatform")
	id("org.jetbrains.compose")

	id("com.android.library")
	id("opensavvy.android")
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
