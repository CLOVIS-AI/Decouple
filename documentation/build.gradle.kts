@file:Suppress("UNUSED_VARIABLE")

plugins {
	id("opensavvy.versioning")

	kotlin("multiplatform")
	id("org.jetbrains.compose")
	id("org.jetbrains.dokka")
}

kotlin {
	js(IR) {
		browser()
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				implementation(projects.core)
				implementation(projects.style.material)
			}
		}
	}
}

tasks.withType<org.jetbrains.dokka.gradle.DokkaTaskPartial>().configureEach {
	dokkaSourceSets.configureEach {
		includes.from("${project.projectDir}/documentation.md")
	}
}
