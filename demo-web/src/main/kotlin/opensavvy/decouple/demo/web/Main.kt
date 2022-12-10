package opensavvy.decouple.demo.web

import opensavvy.decouple.demo.Demo
import opensavvy.decouple.demo.Screen
import opensavvy.decouple.material.MaterialUI
import opensavvy.decouple.navigation.BrowserNavigation
import org.jetbrains.compose.web.renderComposable

fun main() {
	renderComposable(rootElementId = "root") {
		Demo(listOf(MaterialUI), BrowserNavigation(Screen.Home, Screen.values().asList(), hashNavigation = true))
	}
}
