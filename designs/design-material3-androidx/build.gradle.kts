plugins {
	alias(opensavvyConventions.plugins.base)
	alias(opensavvyConventions.plugins.kotlin.library)
	alias(opensavvyConventions.plugins.aligned.composeMultiplatform)
	alias(opensavvyConventions.plugins.aligned.composeCompiler)
}

kotlin {
	jvm()
	iosArm64()
	iosSimulatorArm64()
	iosX64()

	sourceSets.commonMain.dependencies {
		dependencies {
			api(projects.components)
			implementation(compose.material3)
		}
	}

	sourceSets.all {
		languageSettings.optIn("opensavvy.decouple.components.RestrictedStabilityArgument")
		languageSettings.optIn("opensavvy.decouple.components.ExperimentalComponent")
	}
}

library {
	name.set("Design system: Material3 (AndroidX implementation)")
	description.set("Material3 design system from Jetpack Compose")
	homeUrl.set("https://gitlab.com/opensavvy/decouple")

	license.set {
		name.set("Apache 2.0")
		url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
	}
}
