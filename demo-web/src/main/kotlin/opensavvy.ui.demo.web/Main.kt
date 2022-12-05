package opensavvy.ui.demo.web

import opensavvy.ui.demo.Demo
import opensavvy.ui.demo.Screen
import opensavvy.ui.material.MaterialUI
import opensavvy.ui.navigation.BrowserNavigation
import org.jetbrains.compose.web.renderComposable

object MaterialUI : MaterialUI {
	override fun toString() = "Material You"
}

fun main() {
	renderComposable(rootElementId = "root") {
		Demo(listOf(MaterialUI), BrowserNavigation(Screen.Home, Screen.values().asList()))
	}
}
