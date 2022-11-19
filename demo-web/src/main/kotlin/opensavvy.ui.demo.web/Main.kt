package opensavvy.ui.demo.web

import opensavvy.ui.demo.Demo
import opensavvy.ui.material.MaterialUI
import org.jetbrains.compose.web.renderComposable

object MaterialUI : MaterialUI {
	override fun toString() = "Material You"
}

fun main() {
	renderComposable(rootElementId = "root") {
		Demo(listOf(MaterialUI))
	}
}
