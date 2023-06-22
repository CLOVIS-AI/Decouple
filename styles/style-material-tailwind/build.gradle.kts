plugins {
	alias(libs.plugins.kotlin)
	alias(libs.plugins.kotlinMpp)

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

	sourceSets {
		val jsMain by getting {
			dependencies {
				api(projects.styles.styleMaterialCommon)

				implementation(compose.web.core)
				implementation(compose.web.svg)

				implementation(libs.kotlin.browser)

				implementation(npm("tailwindcss", libs.versions.npm.tailwindcss.get()))
				implementation(npm("@fontsource/roboto", libs.versions.npm.roboto.get()))
			}
		}
	}
}
