@file:Suppress("UNUSED_VARIABLE")

plugins {
	alias(libs.plugins.kotlin)
	alias(libs.plugins.kotlinMpp)

	alias(libs.plugins.compose)
}

kotlin {
	js(IR) {
		browser()
	}

	sourceSets {
		val jsMain by getting {
			dependencies {
				api(compose.web.core)

				implementation(libs.kotlin.browser)
			}
		}
	}
}
