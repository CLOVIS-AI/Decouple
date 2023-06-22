package opensavvy.decouple.material.tailwind.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import opensavvy.decouple.core.layout.Alignment
import opensavvy.decouple.core.layout.Arrangement
import opensavvy.decouple.core.layout.LazyLayouts
import opensavvy.decouple.extra.html.lazy.LazyDsl
import opensavvy.decouple.extra.html.lazy.LazyColumn as ExtraHtmlLazyColumn
import opensavvy.decouple.extra.html.lazy.LazyRow as ExtraHtmlLazyRow

object MTLazyLayouts : LazyLayouts {

	@Composable
	override fun LazyColumn(
		vertical: Arrangement,
		alignment: Alignment,
		content: LazyLayouts.LazyColumnScope.() -> Unit,
	) {
		ExtraHtmlLazyColumn({
			classes(linearClasses(vertical, alignment))
		}) {
			LazyAdapter(this).also(content)
		}
	}

	@Composable
	override fun LazyRow(
		horizontal: Arrangement,
		alignment: Alignment,
		content: LazyLayouts.LazyRowScope.() -> Unit,
	) = key(content) {
		ExtraHtmlLazyRow({
			classes(linearClasses(horizontal, alignment))
		}) {
			LazyAdapter(this).also(content)
		}
	}
}

private class LazyAdapter(val dsl: LazyDsl) : LazyLayouts.LazyColumnScope, LazyLayouts.LazyRowScope {
	override fun item(key: Any?, block: @Composable () -> Unit) {
		dsl.item(key, block)
	}

	override fun items(count: Int, key: (index: Int) -> Any, block: @Composable (index: Int) -> Unit) {
		dsl.items(count, key, block)
	}

	override fun <K : Any> items(items: Iterable<K>, key: (item: K) -> Any, block: @Composable (item: K) -> Unit) {
		dsl.items(items, key, block)
	}
}
