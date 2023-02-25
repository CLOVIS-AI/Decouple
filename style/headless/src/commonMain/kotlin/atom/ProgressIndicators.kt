package opensavvy.decouple.headless.atom

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.atom.ProgressIndicators
import opensavvy.decouple.headless.Component
import opensavvy.decouple.headless.bind
import opensavvy.decouple.headless.compose
import opensavvy.decouple.headless.node.Node
import opensavvy.decouple.headless.node.getValue
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
