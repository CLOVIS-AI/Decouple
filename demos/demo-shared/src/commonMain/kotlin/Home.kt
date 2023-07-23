package opensavvy.decouple.demo

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.atom.Text
import opensavvy.decouple.core.layout.Screen

@Composable
fun Home() = Screen(
	title = "OpenSavvy Decouple (tech preview)",
	subtitle = "Decouple your design system from your multiplatform UI",
) {
	Text(
		"""Our goal is to empower developers to work on their application before the designers are done establishing
			|the visual identity, knowing it will be easy to replace specific components afterwards without needing to
			|rewrite the entire UI.
		""".trimMargin()
	)

	Text(
		"""You are currently looking at our demonstration app, which is built in the common module of a Kotlin
			|Multiplatform project.
			|This single module is compiled for each supported platform with a platform-specific implementation of 
			|all components.
		""".trimMargin()
	)

	Text("Currently, the following platforms are supported out-of-the-box:")
	Text("• Material for Kotlin/JS, based on TailwindCSS and Compose for Web,")
	Text("• Material for Kotlin/JVM, based on Jetpack Compose Material3 and Compose for Desktop,")
	Text("• Material for Android, based on Jetpack Compose Material3.")
	Text("In the future, we are planning support for:")
	Text("• Material for WASM, based on Jetpack Compose Material3 and Compose for Web (canvas),")
	Text("• Jewel for Kotlin/JVM, based on JetBrains' Jewel design system (for IntelliJ plugins),")
	Text("• TailwindUI for Kotlin/JS, a black-box implementation of the TailwindUI design system.")
	Text("""
		Anyone can add support for any platform supported by the Kotlin compiler, as well as any design system.
		We welcome contributions!
	""".trimIndent())

	// Are you reading this because you are currently going through our tutorials?
	// If so, uncomment the following line:

	// Counter()
}

// Also uncomment this:

// @Composable
// fun Counter() = Row {
// 	Button(onClick = { println("Clicked on -") }) {
// 		Text("-")
// 	}
//
// 	Text("0")
//
// 	Button(onClick = { println("Clicked on +") }) {
// 		Text("+")
// 	}
// }
