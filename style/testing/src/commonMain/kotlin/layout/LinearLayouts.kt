package opensavvy.decouple.testing.layout

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.layout.Alignment
import opensavvy.decouple.core.layout.Arrangement
import opensavvy.decouple.core.layout.LinearLayouts
import opensavvy.decouple.testing.Component
import opensavvy.decouple.testing.bind
import opensavvy.decouple.testing.compose
import opensavvy.decouple.testing.node.Node
import opensavvy.decouple.testing.node.getValue

class Column(node: Node) : Component {
	val vertical: Arrangement by node.attributes
	val alignment: Alignment by node.attributes

	val content by node.content

	companion object : Component.Meta<Column> {
		override val name = "LinearLayouts.Column"

		override fun buildFrom(node: Node) = Column(node)
	}
}

class Row(node: Node) : Component {
	val horizontal: Arrangement by node.attributes
	val alignment: Alignment by node.attributes

	val content by node.content

	companion object : Component.Meta<Row> {
		override val name = "LinearLayouts.Row"

		override fun buildFrom(node: Node) = Row(node)
	}
}

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
	override fun Column(
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
	override fun Row(
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
	override fun Box(
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
