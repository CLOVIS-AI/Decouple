package opensavvy.decouple.headless.components.display

import androidx.compose.runtime.Composable
import opensavvy.decouple.components.display.Texts
import opensavvy.decouple.headless.Component
import opensavvy.decouple.headless.bind
import opensavvy.decouple.headless.compose
import opensavvy.decouple.headless.node.Node
import opensavvy.decouple.headless.node.getValue

/**
 * Headless implementation of the [Texts] interface.
 */
class Text(node: Node) : Component {
	val text: String by node.attributes

	companion object : Component.Meta<Text> {
		override val name = "Texts.Text"

		override fun buildFrom(node: Node) = Text(node)
	}
}

interface Texts : Texts {

	@Composable
	override fun TextSpec(text: String) {
		Text.compose(
			update = {
				bind(text, Text::text)
			}
		) {}
	}
}
