@file:Suppress("UNUSED_VARIABLE")

plugins {
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
				implementation(projects.material)
			}
		}
	}
}

tasks.withType<org.jetbrains.dokka.gradle.DokkaTaskPartial>().configureEach {
	dokkaSourceSets.configureEach {
		includes.from("${project.projectDir}/documentation.md")
	}
}
