package opensavvy.decouple.testing.atom.text

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.atom.text.Texts
import opensavvy.decouple.testing.Component
import opensavvy.decouple.testing.bind
import opensavvy.decouple.testing.compose
import opensavvy.decouple.testing.node.Node
import opensavvy.decouple.testing.node.getValue

class Text(node: Node) : Component {
	val text: String by node.attributes

	companion object : Component.Meta<Text> {
		override val name: String = "Texts.Text"

		override fun buildFrom(node: Node) = Text(node)
	}
}

object TTexts : Texts {
	@Composable
	override fun Text(text: String) {
		Text.compose(
			update = {
				bind(text, Text::text)
			}
		) {}
	}

}
