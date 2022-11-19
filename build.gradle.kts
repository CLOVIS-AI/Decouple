plugins {
	kotlin("multiplatform") apply false
	kotlin("jvm") apply false
	kotlin("js") apply false
	id("org.jetbrains.dokka") apply false
	id("maven-publish")

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
	plugins.apply("org.jetbrains.dokka")
	plugins.apply("maven-publish")

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

	publishing {
		repositories {
			val projectId = System.getenv("CI_PROJECT_ID")
			val token = System.getenv("CI_JOB_TOKEN")

			if (projectId != null && token != null)
				maven {
					name = "GitLab"
					url = uri("https://gitlab.com/api/v4/projects/$projectId/packages/maven")

					credentials(HttpHeaderCredentials::class.java) {
						name = "Job-Token"
						value = token
					}

					authentication {
						create<HttpHeaderAuthentication>("header")
					}
				}
			else
				logger.debug("The GitLab registry is disabled because credentials are missing.")
		}
	}


	tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
		kotlinOptions {
			jvmTarget = "17"
		}
	}
}

fun calculateVersion(): String {
	val versionDetails: groovy.lang.Closure<com.palantir.gradle.gitversion.VersionDetails> by extra
	val details = versionDetails()

	return if (details.commitDistance == 0)
		details.lastTag
	else
		"${details.lastTag}-post.${details.commitDistance}+${details.gitHash}"
}
