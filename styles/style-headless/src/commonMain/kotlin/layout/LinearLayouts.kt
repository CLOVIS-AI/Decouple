package opensavvy.decouple.headless.layout

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.layout.Alignment
import opensavvy.decouple.core.layout.Arrangement
import opensavvy.decouple.core.layout.LinearLayouts
import opensavvy.decouple.headless.Component
import opensavvy.decouple.headless.bind
import opensavvy.decouple.headless.compose
import opensavvy.decouple.headless.node.Node
import opensavvy.decouple.headless.node.getValue

/**
 * Type-safe wrapper for [opensavvy.decouple.core.layout.Column].
 */
class Column(node: Node) : Component {
	val vertical: Arrangement by node.attributes
	val alignment: Alignment by node.attributes

	val content by node.content

	companion object : Component.Meta<Column> {
		override val name = "LinearLayouts.Column"

		override fun buildFrom(node: Node) = Column(node)
	}
}

/**
 * Type-safe wrapper for [opensavvy.decouple.core.layout.Row].
 */
class Row(node: Node) : Component {
	val horizontal: Arrangement by node.attributes
	val alignment: Alignment by node.attributes

	val content by node.content

	companion object : Component.Meta<Row> {
		override val name = "LinearLayouts.Row"

		override fun buildFrom(node: Node) = Row(node)
	}
}

/**
 * Type-safe wrapper for [opensavvy.decouple.core.layout.Box].
 */
class Box(node: Node) : Component {
	val alignment: Alignment by node.attributes

	val content by node.content

	companion object : Component.Meta<Box> {
		override val name = "LinearLayouts.Box"

		override fun buildFrom(node: Node) = Box(node)
	}
}

object TLinearLayouts : LinearLayouts {
	@Composable
	override fun ColumnSpec(
		vertical: Arrangement,
		alignment: Alignment,
		content: @Composable LinearLayouts.ColumnScope.() -> Unit,
	) {
		Column.compose(
			update = {
				bind(vertical, Column::vertical)
				bind(alignment, Column::alignment)
			}
		) {
			content(TColumnScope)
		}
	}

	@Composable
	override fun RowSpec(
		horizontal: Arrangement,
		alignment: Alignment,
		content: @Composable LinearLayouts.RowScope.() -> Unit,
	) {
		Row.compose(
			update = {
				bind(horizontal, Row::horizontal)
				bind(alignment, Row::alignment)
			}
		) {
			content(TRowScope)
		}
	}

	@Composable
	override fun BoxSpec(
		alignment: Alignment,
		content: @Composable LinearLayouts.BoxScope.() -> Unit,
	) {
		Box.compose(
			update = {
				bind(alignment, Box::alignment)
			}
		) {
			content(TBoxScope)
		}
	}

	object TColumnScope : LinearLayouts.ColumnScope
	object TRowScope : LinearLayouts.RowScope
	object TBoxScope : LinearLayouts.BoxScope
}
