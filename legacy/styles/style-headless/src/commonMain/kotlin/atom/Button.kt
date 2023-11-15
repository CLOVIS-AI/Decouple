package opensavvy.decouple.headless.atom

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.atom.ButtonAttrs
import opensavvy.decouple.core.atom.Buttons
import opensavvy.decouple.headless.Component
import opensavvy.decouple.headless.bind
import opensavvy.decouple.headless.compose
import opensavvy.decouple.headless.execution.Slot
import opensavvy.decouple.headless.node.Node
import opensavvy.decouple.headless.node.getValue
import opensavvy.progress.Progress

class Button(node: Node) : Component {
	val click: () -> Unit by node.attributes

	val role: Buttons.Role by node.attributes
	val enabled: Boolean by node.attributes
	val loading: Progress by node.attributes
	val contrasted: Boolean by node.attributes

	val icon by node.slots

	val content by node.content

	companion object : Component.Meta<Button> {
		override val name = "Buttons.Button"

		override fun buildFrom(node: Node) = Button(node)
	}
}

object TButtons : Buttons {

	@Composable
	override fun ButtonSpec(attrs: ButtonAttrs) {
		Button.compose(
			update = {
				bind(attrs.onClick, Button::click)
				bind(attrs.role, Button::role)
				bind(attrs.enabled, Button::enabled)
				bind(attrs.loading, Button::loading)
				bind(attrs.contrasted, Button::contrasted)
			}
		) {
			attrs.icon?.let {
				Slot(Button::icon, it)
			}

			attrs.content(TButtonScope)
		}
	}

	object TButtonScope : Buttons.ButtonScope
}
