/*
 * This file was generated by the Gradle 'init' task.
 *
 * The settings file is used to specify which projects to include in your build.
 *
 * Detailed information about configuring a multi-project build in Gradle can be found
 * in the user manual at https://docs.gradle.org/8.1.1/userguide/multi_project_builds.html
 */

rootProject.name = "Decouple"

pluginManagement {
	repositories {
		gradlePluginPortal()
		google()

		// OpenSavvy conventions
		maven("https://gitlab.com/api/v4/projects/51233470/packages/maven")

		// Compose Multiplatform
		maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
	}

	includeBuild("gradle/conventions")
}

dependencyResolutionManagement {
	repositories {
		google()
		mavenCentral()

		// Compose Multiplatform
		maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
	}

	versionCatalogs {
		create("demoLibs") {
			from(files("gradle/demo.versions.toml"))
		}
	}
}

plugins {
	id("dev.opensavvy.conventions.settings") version "0.3.4"
}

include(
	"gradle:templates:template-app",
	"gradle:templates:template-lib",

	"polymorphism",
	"components",

	"designs:design-headless",
	"designs:design-headless-prepared",
	"designs:design-material3-androidx",
	"designs:design-material3-html",
	"designs:design-pure-css",

	"demo",
	"demo:composeApp",
)
