plugins {
	alias(opensavvyConventions.plugins.base)
	alias(opensavvyConventions.plugins.kotlin.application)
}

kotlin {
	jvm {
		withJava() // required by the application plugin
	}

	sourceSets.commonMain.dependencies {
		implementation(projects.polymorphism)
	}

	sourceSets.commonTest.dependencies {
		implementation(opensavvyConventions.aligned.kotlin.test.junit5)
	}
}

application {
	mainClass.set("opensavvy.decouple.demo.MainKt")
}
