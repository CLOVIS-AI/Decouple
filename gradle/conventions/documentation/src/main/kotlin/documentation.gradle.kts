package opensavvy

plugins {
	id("org.jetbrains.dokka")
}

tasks.withType<org.jetbrains.dokka.gradle.DokkaTaskPartial>().configureEach {
	dokkaSourceSets.configureEach {
		//region Link to external libraries

		fun linkTo(vararg links: String) {
			for (link in links) {
				externalDocumentationLink {
					url.set(java.net.URL(link))
				}
			}
		}

		linkTo(
			"https://kotlinlang.org/api/kotlinx.coroutines/",
			"https://kotlinlang.org/api/kotlinx.serialization/",
			"https://opensavvy.gitlab.io/pedestal/documentation/",
			"https://apidocs.arrow-kt.io",
		)

		//endregion
		//region Link to the sources

		val gitlab = "https://gitlab.com/opensavvy/decouple/-/blob/main"
		val path = project.absoluteProjectPath("src")
			.removePrefix(":")
			.replace(':', '/')

		sourceLink {
			localDirectory.set(file("src"))
			remoteUrl.set(java.net.URL("$gitlab/$path"))
			remoteLineSuffix.set("#L")
		}

		//endregion
		//region Include the README file

		val sourceSetName = name
			.takeUnless { it == "common" || it == "commonMain" }

		val readme =
			File(project.projectDir, listOfNotNull("README", sourceSetName, "md").joinToString(separator = "."))

		if (readme.exists()) {
			includes.from(readme)
		}

		//endregion
	}
}
