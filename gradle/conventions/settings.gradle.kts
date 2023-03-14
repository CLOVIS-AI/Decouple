rootProject.name = "decouple-conventions"

dependencyResolutionManagement {
	repositories {
		mavenCentral()
		gradlePluginPortal()
	}

	versionCatalogs {
		create("libs") {
			from(files("../libs.versions.toml"))
		}
	}
}

include(
	"versioning",
	"documentation",
	"kotlin",
)

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
