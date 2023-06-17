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
					enabled.set(true)
				}
			}
		}
	}
}

dependencies {
	api(projects.styles.styleMaterialCommon)

	implementation(compose.web.core)
	implementation(compose.web.svg)

	implementation(npm("tailwindcss", libs.versions.npm.tailwindcss.get()))
	implementation(npm("@fontsource/roboto", libs.versions.npm.roboto.get()))
}
