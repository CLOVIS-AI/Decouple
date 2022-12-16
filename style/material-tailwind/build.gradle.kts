import java.net.URL

plugins {
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

tasks.withType<org.jetbrains.dokka.gradle.DokkaTaskPartial>().configureEach {
	dokkaSourceSets.configureEach {
		sourceLink {
			localDirectory.set(file("src"))
			remoteUrl.set(URL("https://gitlab.com/opensavvy/decouple/-/blob/main/style/material-tailwind/src"))
			remoteLineSuffix.set("#L")
		}
	}
}
