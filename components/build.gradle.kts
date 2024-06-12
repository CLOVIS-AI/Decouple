plugins {
	alias(opensavvyConventions.plugins.base)
	alias(opensavvyConventions.plugins.kotlin.library)
	alias(opensavvyConventions.plugins.aligned.composeMultiplatform)
	alias(opensavvyConventions.plugins.aligned.composeCompiler)
}

kotlin {
	jvm()
	js(IR) {
		browser()
	}
	linuxX64()
	iosArm64()
	iosSimulatorArm64()
	iosX64()

	val commonMain by sourceSets.getting {
		dependencies {
			api(projects.polymorphism)

			api(libs.pedestal.progress)
			api(libs.pedestal.progress.coroutines)
		}
	}

	sourceSets.all {
		languageSettings.optIn("opensavvy.decouple.components.RestrictedStabilityArgument")
	}
}

library {
	name.set("Component library")
	description.set("Standardized component list that forms the basis of most applications")
	homeUrl.set("https://gitlab.com/opensavvy/decouple")

	license.set {
		name.set("Apache 2.0")
		url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
	}
}
