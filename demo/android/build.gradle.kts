@file:Suppress("UnstableApiUsage")

import de.fayard.refreshVersions.core.versionFor

plugins {
	alias(libs.plugins.kotlin)
	alias(libs.plugins.kotlinAndroid)

	alias(libs.plugins.android)
	alias(libs.plugins.androidApplication)
}

android {
	defaultConfig {
		versionCode = 1 //TODO: generate from the Gradle version
		versionName = project.version as String

		vectorDrawables {
			useSupportLibrary = true
		}
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro",
			)
		}
	}

	kotlinOptions {
		jvmTarget = "1.8"
	}

	buildFeatures {
		compose = true
	}

	composeOptions {
		kotlinCompilerExtensionVersion = versionFor("version.androidx.compose.compiler-extensions")
	}

	namespace = "opensavvy.decouple.demo.android"
}

dependencies {
	implementation(AndroidX.core.ktx)
	implementation(AndroidX.lifecycle.runtime.ktx)
	implementation(AndroidX.activity.compose)
	implementation(platform(AndroidX.compose.bom))
	implementation(AndroidX.compose.ui)
	implementation(AndroidX.compose.ui.graphics)
	implementation(AndroidX.compose.ui.toolingPreview)
	implementation(AndroidX.compose.material3)

	implementation(projects.demo)
	implementation(projects.style.material)

	testImplementation(Testing.junit4)

	androidTestImplementation(AndroidX.test.ext.junit)
	androidTestImplementation(AndroidX.test.espresso.core)
	androidTestImplementation(platform(AndroidX.compose.bom))
	androidTestImplementation(AndroidX.compose.ui.testJunit4)

	debugImplementation(AndroidX.compose.ui.tooling)
	debugImplementation(AndroidX.compose.ui.testManifest)
}
