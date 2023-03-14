plugins {
	id("opensavvy.kotlin")
	kotlin("js")
	id("org.jetbrains.compose")
}

kotlin {
	js(IR) {
		browser {
			commonWebpackConfig {
				cssSupport {
					enabled = true
				}
			}
		}
	}
}

dependencies {
	api(projects.style.materialCommon)

	implementation(compose.web.core)
	implementation(compose.web.svg)

	implementation(npm("tailwindcss", "_"))
	implementation(npm("@fontsource/roboto", "_"))
}
