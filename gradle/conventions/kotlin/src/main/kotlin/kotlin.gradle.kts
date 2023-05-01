package opensavvy

plugins {
    id("maven-publish")

    id("opensavvy.versioning")
    id("opensavvy.documentation")
}

for (kotlinPlugin in listOf("org.jetbrains.kotlin.jvm", "org.jetbrains.kotlin.js", "org.jetbrains.kotlin.multiplatform")) {
    // The Android plugin doesn't support JVM toolchains
    pluginManager.withPlugin(kotlinPlugin) {
        extensions.configure<org.jetbrains.kotlin.gradle.dsl.KotlinTopLevelExtension> {
            jvmToolchain(17)
        }
    }
}

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
