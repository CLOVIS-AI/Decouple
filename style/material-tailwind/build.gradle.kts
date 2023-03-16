plugins {
	alias(libs.plugins.kotlin)
	alias(libs.plugins.kotlinJs)

	alias(libs.plugins.compose)
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
