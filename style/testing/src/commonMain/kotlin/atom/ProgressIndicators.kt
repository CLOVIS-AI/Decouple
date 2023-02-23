package opensavvy.decouple.testing.atom

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.atom.ProgressIndicators
import opensavvy.decouple.testing.Component
import opensavvy.decouple.testing.bind
import opensavvy.decouple.testing.compose
import opensavvy.decouple.testing.node.Node
import opensavvy.decouple.testing.node.getValue
import opensavvy.state.Progression

class ProgressIndicator(node: Node) : Component {
	val progress: Progression by node.attributes

	companion object : Component.Meta<ProgressIndicator> {
		override val name = "ProgressIndicators.ProgressIndicator"

		override fun buildFrom(node: Node) = ProgressIndicator(node)
	}
}

object TProgressIndicators : ProgressIndicators {
	@Composable
	override fun ProgressIndicator(progress: Progression) {
		ProgressIndicator.compose(
			update = {
				bind(progress, ProgressIndicator::progress)
			}
		) {}
	}

}
