@file:Suppress("UNUSED_VARIABLE")

plugins {
	alias(libs.plugins.kotlin)
	alias(libs.plugins.kotlinMpp)

	alias(libs.plugins.compose)
}

kotlin {
	jvm()
	js(IR) {
		browser()
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				api(projects.core)
			}
		}

		val commonTest by getting {
			dependencies {
				api(libs.kotlin.test)
				api(libs.kotlin.test.annotations)

				api(libs.kotlinx.coroutines.test)
			}
		}

		val jvmTest by getting {
			dependencies {
				api(libs.kotlin.test.jvm)
			}
		}

		val jsTest by getting {
			dependencies {
				api(libs.kotlin.test.js)
			}
		}
	}
}

tasks.named("compileKotlinJs", org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile::class.java) {
	compilerOptions {
		// Workaround for a false positive IdSignature Clash on UIMetadata.initializeFor
		// https://youtrack.jetbrains.com/issue/KT-56660
		freeCompilerArgs.add("-Xklib-enable-signature-clash-checks=false")
	}
}
