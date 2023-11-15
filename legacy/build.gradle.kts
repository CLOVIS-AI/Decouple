plugins {
	alias(libs.plugins.kotlin)
	alias(libs.plugins.kotlinMpp) apply false
	alias(libs.plugins.kotlinJvm) apply false
	alias(libs.plugins.kotlinAndroid) apply false

	alias(libs.plugins.androidApplication) apply false
	alias(libs.plugins.androidLibrary) apply false
}

buildscript {
	repositories {
		google()
		mavenCentral()
	}
}
