group = "opensavvy"
version = "0.1.0-SNAPSHOT"

subprojects {
	group = rootProject.group
	version = rootProject.version
}

plugins {
	kotlin("multiplatform") apply false
	id("org.jetbrains.dokka") apply false
}

buildscript {
	repositories {
		google()
		mavenCentral()
	}
}

allprojects {
	repositories {
		google()
		mavenCentral()

		maven {
			name = "JetBrains Compose"
			url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev")
		}

		maven {
			name = "OpenSavvy Pedestal"
			url = uri("https://gitlab.com/api/v4/projects/37325377/packages/maven")
		}
	}

	tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
		kotlinOptions {
			jvmTarget = "17"
		}
	}

	plugins.apply("org.jetbrains.dokka")
}
