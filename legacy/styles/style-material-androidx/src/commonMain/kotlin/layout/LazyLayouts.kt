package opensavvy.decouple.material.androidx.layout

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import opensavvy.decouple.core.layout.Alignment
import opensavvy.decouple.core.layout.Arrangement
import opensavvy.decouple.core.layout.LazyLayouts
import opensavvy.decouple.core.paging.PagingScope
import androidx.compose.foundation.lazy.LazyColumn as M3LazyColumn
import androidx.compose.foundation.lazy.LazyRow as M3LazyRow

object MALazyLayouts : LazyLayouts {

	@Composable
	override fun LazyColumnSpec(
		vertical: Arrangement,
		alignment: Alignment,
		content: LazyLayouts.LazyColumnScope.() -> Unit,
	) {
		M3LazyColumn {
			LazyAdapter(this).apply(content)
		}
	}

	@Composable
	override fun LazyRowSpec(
		horizontal: Arrangement,
		alignment: Alignment,
		content: LazyLayouts.LazyRowScope.() -> Unit,
	) {
		val scope = rememberCoroutineScope()
		val scroll = rememberLazyListState()

		M3LazyRow(
			Modifier.draggable(
				orientation = Orientation.Horizontal,
				state = rememberDraggableState { delta ->
					scope.launch {
						scroll.scrollBy(-delta)
					}
				}),
			scroll,
			userScrollEnabled = true,
		) {
			LazyAdapter(this).apply(content)
		}
	}

}

private class LazyAdapter(private val native: LazyListScope) : PagingScope, LazyLayouts.LazyColumnScope, LazyLayouts.LazyRowScope {
	override fun item(key: Any?, block: @Composable () -> Unit) {
		native.item(key) { block() }
	}

	override fun items(count: Int, key: (index: Int) -> Any, block: @Composable (index: Int) -> Unit) {
		native.items(
			count,
			key = { key(it) },
		) {
			block(it)
		}
	}

	override fun <K : Any> items(items: Iterable<K>, key: (item: K) -> Any, block: @Composable (item: K) -> Unit) {
		native.items(
			items.toList(),
			key = { key(it) },
		) {
			block(it)
		}
	}
}
