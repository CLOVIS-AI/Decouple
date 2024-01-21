package opensavvy.decouple.demo

import opensavvy.decouple.demo.design.InstallSelectedDesign
import org.jetbrains.compose.web.renderComposable

fun main() {
	renderComposable(rootElementId = "root") {
		InstallSelectedDesign {
			App()
		}
	}
}
