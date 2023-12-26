package opensavvy.decouple.headless.components.actions

import androidx.compose.runtime.Composable
import opensavvy.decouple.components.actions.Buttons
import opensavvy.decouple.headless.Component
import opensavvy.decouple.headless.bind
import opensavvy.decouple.headless.compose
import opensavvy.decouple.headless.execution.Slot
import opensavvy.decouple.headless.node.Node
import opensavvy.decouple.headless.node.getValue
import opensavvy.progress.Progress

/**
 * Headless implementation of the [Buttons] interface.
 */
class Button(node: Node) : Component {
	val click: () -> Unit by node.attributes

	val role: Buttons.Role by node.attributes
	val enabled: Boolean by node.attributes
	val progress: Progress by node.attributes

	val icon by node.slots

	val content by node.content

	companion object : Component.Meta<Button> {
		override val name = "Buttons.Button"

		override fun buildFrom(node: Node) = Button(node)
	}
}

interface Buttons : Buttons {

	@Composable
	override fun ButtonSpec(args: Buttons.ButtonArgs) {
		Button.compose(
			update = {
				bind(args.onClick, Button::click)
				bind(args.role, Button::role)
				bind(args.enabled, Button::enabled)
				bind(args.progress, Button::progress)
			}
		) {
			args.icon?.let {
				Slot(Button::icon, it)
			}

			args.content()
		}
	}
}
