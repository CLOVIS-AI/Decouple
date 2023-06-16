plugins {
	alias(libs.plugins.kotlin)
	alias(libs.plugins.kotlinJs)

	alias(libs.plugins.compose)
}

kotlin {
	js(IR) {
		browser()
		binaries.executable()
	}
}

dependencies {
	implementation(projects.demos)
	implementation(projects.style.material)

	implementation(devNpm("vite", libs.versions.npm.vite.asProvider().get()))
	implementation(devNpm("postcss", libs.versions.npm.postcss.get()))
	implementation(devNpm("postcss-loader", libs.versions.npm.postcssLoader.get()))
	implementation(devNpm("autoprefixer", libs.versions.npm.autoprefixer.get()))
	implementation(devNpm("@originjs/vite-plugin-commonjs", libs.versions.npm.vite.commonJs.get()))
	implementation(devNpm("@rollup/plugin-commonjs", libs.versions.npm.rollup.commonJs.get()))
}

//region Vite

val jsWorkspace = "${rootProject.buildDir}/js"
val jsProjectDir = "$jsWorkspace/packages/${rootProject.name}-${project.name}"

val kotlinNodeJsSetup by rootProject.tasks.getting(org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsSetupTask::class)
val kotlinNpmInstall by rootProject.tasks.getting(org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask::class)

val productionExecutableCompileSync by tasks.getting(Task::class)

val copyMaterialResources by tasks.registering(Copy::class) {
	description = "Copies Material resources to the build directory"
	group = "vite"

	from(project(":style:material-tailwind").projectDir / "src" / "main" / "resources")
	into(jsProjectDir)

	dependsOn(kotlinNpmInstall)
}

val configureTailwind by tasks.registering(Copy::class) {
	description = "Copies the Tailwind configuration file to the build directory"
	group = "vite"

	from("./tailwind.config.cjs")
	into(jsProjectDir)

	dependsOn(kotlinNpmInstall)
}

val configureVite by tasks.registering(Copy::class) {
	description = "Copies the Vite configuration file to the build directory"
	group = "vite"

	from("./vite.config.js")
	into(jsProjectDir)

	dependsOn(kotlinNpmInstall)
}

val configurePostcss by tasks.registering(Copy::class) {
	description = "Copies the PostCSS configuration file to the build directory"
	group = "vite"

	from("./postcss.config.cjs")
	into(jsProjectDir)

	dependsOn(kotlinNpmInstall)
}

val vite by tasks.registering(Exec::class) {
	description = "Starts a development web server"
	group = "vite"

	workingDir = file(jsProjectDir)
	commandLine(
		kotlinNodeJsSetup.destination / "bin" / "node",
		file(jsWorkspace) / "node_modules" / "vite" / "bin" / "vite.js",
		"dev"
	)

	dependsOn(
		kotlinNodeJsSetup,
		kotlinNpmInstall,
	)
}

val developmentExecutableCompileSync: Task by tasks.getting {
	dependsOn(
		configureTailwind,
		configureVite,
		configurePostcss,
		copyMaterialResources,
	)
}

val production by tasks.registering(Exec::class) {
	description = "Compiles the production web demo"
	group = "vite"

	workingDir = file(jsProjectDir)
	commandLine(
		kotlinNodeJsSetup.destination / "bin" / "node",
		file(jsWorkspace) / "node_modules" / "vite" / "bin" / "vite.js",
		"build"
	)

	dependsOn(
		kotlinNodeJsSetup,
		kotlinNpmInstall,
		configureTailwind,
		configureVite,
		configurePostcss,
		copyMaterialResources,
		productionExecutableCompileSync
	)
}

operator fun File.div(child: String) = File(this, child)

//endregion
