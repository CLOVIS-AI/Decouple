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

	val commonMain by sourceSets.getting {
		dependencies {
			api(compose.runtime)
		}
	}
}

library {
	name.set("Core")
	description.set("Architecture to declare polymorphic Compose Multiplatform components")
	homeUrl.set("https://gitlab.com/opensavvy/decouple")

	license.set {
		name.set("Apache 2.0")
		url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
	}
}
