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
	// iosArm64()
	// iosSimulatorArm64()
	// iosX64()

	sourceSets.commonMain.dependencies {
		dependencies {
			api(projects.components)
			api(libs.kotlinx.coroutines)
		}
	}

	sourceSets.commonTest.dependencies {
		dependencies {
			implementation(libs.prepared.kotest)
			implementation(projects.designs.designHeadlessPrepared)
		}
	}

	sourceSets.all {
		languageSettings.optIn("opensavvy.decouple.components.RestrictedStabilityArgument")
		languageSettings.optIn("opensavvy.decouple.components.ExperimentalComponent")
	}
}

library {
	name.set("Design system: Headless")
	description.set("Headless implementation of the Decouple component library to help test the behavior of a UI")
	homeUrl.set("https://gitlab.com/opensavvy/decouple")

	license.set {
		name.set("Apache 2.0")
		url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
	}
}
