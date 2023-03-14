plugins {
	id("opensavvy.kotlin")

	kotlin("multiplatform") apply false
	kotlin("jvm") apply false
	kotlin("js") apply false

	id("com.android.library") apply false

	id("org.jetbrains.dokka")
}

buildscript {
	repositories {
		google()
		mavenCentral()
	}
}
