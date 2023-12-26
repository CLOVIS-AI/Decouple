plugins {
	alias(opensavvyConventions.plugins.base)
	alias(opensavvyConventions.plugins.kotlin.library)
	alias(opensavvyConventions.plugins.aligned.composeMultiplatform)
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
			api(projects.designs.designHeadless)
			api(libs.prepared)
		}
	}

	sourceSets.commonTest.dependencies {
		dependencies {
			implementation(libs.prepared.kotest)
		}
	}

	sourceSets.all {
		languageSettings.optIn("opensavvy.decouple.components.RestrictedStabilityArgument")
	}
}

library {
	name.set("Design system: Headless (Prepared compatibility)")
	description.set("Headless implementation of the Decouple component library to help test the behavior of a UI using the Prepared test framework")
	homeUrl.set("https://gitlab.com/opensavvy/decouple")

	license.set {
		name.set("Apache 2.0")
		url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
	}
}
