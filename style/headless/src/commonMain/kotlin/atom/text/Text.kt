package opensavvy.decouple.headless.atom.text

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.atom.text.Texts
import opensavvy.decouple.headless.Component
import opensavvy.decouple.headless.bind
import opensavvy.decouple.headless.compose
import opensavvy.decouple.headless.node.Node
import opensavvy.decouple.headless.node.getValue

/**
 * Type-safe wrapper for [opensavvy.decouple.core.atom.text.Text].
 */
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
