@file:Suppress("UnstableApiUsage")

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
		kotlinCompilerExtensionVersion = libs.versions.compose.androidCompilerExtension.orNull
	}

	namespace = "opensavvy.decouple.demo.android"
}

dependencies {
	implementation(libs.androidx.core)
	implementation(libs.androidx.lifecycle.runtime)
	implementation(libs.androidx.activity.compose)

	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.compose.ui)
	implementation(libs.androidx.compose.uiGraphics)
	implementation(libs.androidx.compose.toolingPreview)
	implementation(libs.androidx.compose.material3)

	implementation(projects.demo)
	implementation(projects.style.material)

	testImplementation(libs.kotlin.test.jvm)

	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.compose.junit4)

	debugImplementation(libs.androidx.compose.tooling)
	debugImplementation(libs.androidx.compose.testManifest)
}
