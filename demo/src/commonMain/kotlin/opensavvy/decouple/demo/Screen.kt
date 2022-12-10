package opensavvy.decouple.demo

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.basic.Button
import opensavvy.decouple.core.basic.Text
import opensavvy.decouple.core.layout.Column
import opensavvy.decouple.core.layout.Row
import opensavvy.decouple.demo.components.Buttons
import opensavvy.decouple.demo.components.Chips
import opensavvy.decouple.demo.components.TextFields
import opensavvy.decouple.navigation.Destination

enum class Screen(
	override val route: String,
	override val title: String,
	override val parent: Screen?,
	@JsName("renderSelf") val render: @Composable () -> Unit,
) : Destination {
	Home("", "OpenSavvy UI", parent = null, { Home() }),
	Buttons("buttons", "Buttons", parent = Home, { Buttons() }),
	Chips("chips", "Chips", parent = Home, { Chips() }),
	TextFields("fields", "Text fields", parent = Home, { TextFields() }),
	;

	@Composable
	override fun render() = Column {
		Row {
			// TODO: in the future, this will be replaced by a proper navigation component
			for (value in values()) {
				Button({ Navigator.forwards(value) }, enabled = Navigator.current != value) {
					Text(value.title)
				}
			}
		}

		render.invoke()
	}
}
