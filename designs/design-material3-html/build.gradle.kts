plugins {
	alias(opensavvyConventions.plugins.base)
	alias(opensavvyConventions.plugins.kotlin.library)
	alias(opensavvyConventions.plugins.aligned.composeMultiplatform)
	alias(opensavvyConventions.plugins.aligned.composeCompiler)
}

kotlin {
	js(IR) {
		browser()
	}

	sourceSets.commonMain.dependencies {
		dependencies {
			api(projects.components)
			implementation(libs.material3.html)
		}
	}

	sourceSets.all {
		languageSettings.optIn("opensavvy.decouple.components.RestrictedStabilityArgument")
		languageSettings.optIn("opensavvy.decouple.components.ExperimentalComponent")
	}
}

library {
	name.set("Design system: Material3 (HTML implementation)")
	description.set("Material3 design system for Compose HTML")
	homeUrl.set("https://gitlab.com/opensavvy/ui/decouple")

	license.set {
		name.set("Apache 2.0")
		url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
	}
}
