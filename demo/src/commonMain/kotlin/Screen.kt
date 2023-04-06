package opensavvy.decouple.demo

import androidx.compose.runtime.Composable
import opensavvy.decouple.demo.components.*
import opensavvy.decouple.demo.design.DesignOverview
import opensavvy.decouple.navigation.Destination
import kotlin.js.JsName

enum class Screen(
	override val route: String,
	override val title: String,
	override val parent: Screen?,
	@JsName("renderSelf") val render: @Composable () -> Unit,
) : Destination {
	Home("", "Home", parent = null, { Home() }),
	GettingStarted("start", "Getting started", parent = null, { GettingStarted() }),
	Components("components", "Components", parent = Home, { Components() }),
	Buttons("buttons", "Buttons", parent = Components, { Buttons() }),
	Chips("chips", "Chips", parent = Components, { Chips() }),
	TextFields("fields", "Text fields", parent = Components, { TextFields() }),
	Progression("progress", "Progression", parent = Home, { Progression() }),
	Design("design", "Design", parent = Home, { DesignOverview() }),
	;

	@Composable
	override fun render() = render.invoke()
}
