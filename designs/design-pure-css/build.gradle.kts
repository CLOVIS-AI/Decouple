plugins {
	alias(opensavvyConventions.plugins.base)
	alias(opensavvyConventions.plugins.kotlin.library)
	alias(opensavvyConventions.plugins.aligned.composeMultiplatform)
}

kotlin {
	js(IR) {
		browser()
	}

	sourceSets.commonMain.dependencies {
		dependencies {
			api(projects.components)
			implementation(compose.html.core)

			implementation(npm("purecss", libs.versions.purecss.get()))
		}
	}

	sourceSets.all {
		languageSettings.optIn("opensavvy.decouple.components.RestrictedStabilityArgument")
		languageSettings.optIn("opensavvy.decouple.components.ExperimentalComponent")
	}
}

library {
	name.set("Design system: Pure.css")
	description.set("Components built with a set of small, responsive CSS modules")
	homeUrl.set("https://gitlab.com/opensavvy/decouple")

	license.set {
		name.set("Apache 2.0")
		url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
	}
}
