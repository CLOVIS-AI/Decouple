package opensavvy.decouple.material.layout

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.layout.*

// this whole implementation is not lazy at all
// this is just a placeholder until a proper implementation is written
// TODO in https://gitlab.com/opensavvy/opensavvy-ui/-/work_items/117887682

actual interface MaterialLazyLayouts : LazyLayouts {

	@Composable
	override fun LazyColumn(
		vertical: Arrangement,
		alignment: Alignment,
		content: LazyLayouts.LazyColumnScope.() -> Unit,
	) {
		Column(vertical, alignment) {
			val scope = LazyLayoutScope()
			content(scope)
			scope.render()
		}
	}

	@Composable
	override fun LazyRow(
		horizontal: Arrangement,
		alignment: Alignment,
		content: LazyLayouts.LazyRowScope.() -> Unit,
	) {
		Row(horizontal, alignment) {
			val scope = LazyLayoutScope()
			content(scope)
			scope.render()
		}
	}
}

private class LazyLayoutScope : LazyLayouts.LazyLayoutScope, LazyLayouts.LazyColumnScope, LazyLayouts.LazyRowScope {
	private val items = ArrayList<@Composable LazyLayouts.LazyItemScope.() -> Unit>()

	override fun item(content: @Composable LazyLayouts.LazyItemScope.() -> Unit) {
		items += content
	}

	override fun items(number: Int, content: @Composable LazyLayouts.LazyItemScope.(Int) -> Unit) {
		repeat(number) {
			items += { content(it) }
		}
	}

	override fun <T> items(values: List<T>, content: @Composable LazyLayouts.LazyItemScope.(T) -> Unit) {
		for (value in values) {
			items += { content(value) }
		}
	}

	@Composable
	fun render() {
		for (item in items)
			item(LazyItemScope)
	}

	private object LazyItemScope : LazyLayouts.LazyItemScope
}
