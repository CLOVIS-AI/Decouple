rootProject.name = "Decouple"

pluginManagement {
	repositories {
		gradlePluginPortal()
		maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
		google()
	}

	includeBuild("gradle/conventions")
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
	"core",

	"demo",
	"demo:web",
	"demo:android",

	"style:material",
	"style:material-common",
	"style:material-tailwind",
	"style:material-androidx",

	"style:headless",

	"extra:navigation",
	"extra:persistence",

	"documentation",
)
