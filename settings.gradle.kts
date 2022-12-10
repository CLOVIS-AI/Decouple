rootProject.name = "Decouple"

pluginManagement {
	repositories {
		gradlePluginPortal()
		maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
		google()
	}
}

plugins {
	id("de.fayard.refreshVersions") version "0.51.0"
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
	"core",

	"material",

	"demo",
	"demo-web",

	"documentation",
)
