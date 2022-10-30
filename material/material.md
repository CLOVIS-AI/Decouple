# Module material

## Installation

In order to copy our `tailwind.config.cjs` and our `style.css` containing font imports and Tailwind configuration, you should add a task to your `build.gradle.kts` :

```kotlin

val expandedArchives = "${rootProject.buildDir}/tmp/expandedArchives"
val jsProjectDir = "${rootProject.buildDir}/js/packages/${rootProject.name}"

val kotlinNpmInstall by rootProject.tasks.getting()

val configureMaterialResources by tasks.registering(Copy::class) {
	description = "Copies the ressources of Material module to the build directory"
	group = "other"

	from(expandedArchives) {
		include("/material-js-*/")
		exclude("/material-js-*/package.json", "/material-js-*/default", "/material-js-*/META-INF")
	}

	into(jsProjectDir)
	eachFile {
		path = path.substringAfter("/")
	}
	includeEmptyDirs = false

	dependsOn(kotlinNpmInstall)
}
```

This task must be run before your bundling tasks.

