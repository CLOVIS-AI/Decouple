package opensavvy.decouple.headless.components.layouts

import androidx.compose.runtime.Composable
import opensavvy.decouple.components.layout.Arrangement
import opensavvy.decouple.components.layout.LinearLayouts
import opensavvy.decouple.headless.Component
import opensavvy.decouple.headless.bind
import opensavvy.decouple.headless.compose
import opensavvy.decouple.headless.node.Node
import opensavvy.decouple.headless.node.getValue

class LinearLayout(node: Node) : Component {
	val direction: Direction by node.attributes
	val arrangement: Arrangement by node.attributes
	val alignment: Arrangement by node.attributes
	val reverse: Boolean by node.attributes

	val content by node.content

	companion object : Component.Meta<LinearLayout> {
		override val name = "LinearLayouts.LinearLayout"

		override fun buildFrom(node: Node) = LinearLayout(node)
	}

	enum class Direction {
		Vertical,
		Horizontal,
	}
}

typealias Row = LinearLayout
typealias Column = LinearLayout

interface LinearLayouts : LinearLayouts {

	@Composable
	override fun RowSpec(args: LinearLayouts.LinearLayoutArgs) {
		LinearLayout.compose(
			update = {
				bind(args.arrangement, LinearLayout::arrangement)
				bind(args.alignment, LinearLayout::alignment)
				bind(args.reverse, LinearLayout::reverse)
				set(Unit) { attributes[LinearLayout::direction] = LinearLayout.Direction.Horizontal }
			}
		) {
			args.content()
		}
	}

	@Composable
	override fun ColumnSpec(args: LinearLayouts.LinearLayoutArgs) {
		LinearLayout.compose(
			update = {
				bind(args.arrangement, LinearLayout::arrangement)
				bind(args.alignment, LinearLayout::alignment)
				bind(args.reverse, LinearLayout::reverse)
				set(Unit) { attributes[LinearLayout::direction] = LinearLayout.Direction.Vertical }
			}
		) {
			args.content()
		}
	}
}
