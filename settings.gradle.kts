rootProject.name = "OpenSavvy-UI"

pluginManagement {
	repositories {
		gradlePluginPortal()
		maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
		google()
	}
}

plugins {
	id("de.fayard.refreshVersions") version "0.40.2"
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
	"core",
	"material",
	"demo",
)
