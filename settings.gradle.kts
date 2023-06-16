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

	"demos",
	"demos:web",
	"demos:android",
	"demos:desktop",

	"style:material",
	"style:material-common",
	"style:material-tailwind",
	"style:material-androidx",

	"style:headless",

	"extra:navigation",
	"extra:persistence",
)

buildCache {
	val username = System.getenv("GRADLE_BUILD_CACHE_CREDENTIALS")?.split(':')?.get(0)
	val password = System.getenv("GRADLE_BUILD_CACHE_CREDENTIALS")?.split(':')?.get(1)

	val mainBranch: String? = System.getenv("CI_DEFAULT_BRANCH")
	val currentBranch: String? = System.getenv("CI_COMMIT_REF_NAME")
	val runningForTag = System.getenv().containsKey("CI_COMMIT_TAG")

	remote<HttpBuildCache> {
		url = uri("https://gradle.opensavvy.dev/cache/")

		if (username != null && password != null) credentials {
			this.username = username
			this.password = password
		}

		isPush = (mainBranch != null && currentBranch != null && mainBranch == currentBranch) || runningForTag
	}
}
