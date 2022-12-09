package opensavvy.ui.demo

import androidx.compose.runtime.Composable
import opensavvy.ui.core.basic.Button
import opensavvy.ui.core.basic.Text
import opensavvy.ui.core.layout.Column
import opensavvy.ui.core.layout.Row
import opensavvy.ui.demo.components.Buttons
import opensavvy.ui.demo.components.Chips
import opensavvy.ui.navigation.Destination

enum class Screen(
	override val route: String,
	override val title: String,
	override val parent: Screen?,
	@JsName("renderSelf") val render: @Composable () -> Unit,
) : Destination {
	Home("", "OpenSavvy UI", parent = null, { Home() }),
	Buttons("buttons", "Buttons", parent = Home, { Buttons() }),
	Chips("chips", "Chips", parent = Home, { Chips() }),
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
