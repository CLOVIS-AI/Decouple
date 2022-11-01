plugins {
	kotlin("multiplatform") apply false
	id("org.jetbrains.dokka") apply false

	id("com.palantir.git-version")
}

group = "opensavvy"
version = calculateVersion()

subprojects {
	group = rootProject.group
	version = rootProject.version
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

fun calculateVersion(): String {
	val versionDetails: groovy.lang.Closure<com.palantir.gradle.gitversion.VersionDetails> by extra
	val details = versionDetails()

	return if (details.commitDistance == 0)
		details.lastTag
	else
		"${details.lastTag}-post.${details.commitDistance}+${details.gitHash}"
}
