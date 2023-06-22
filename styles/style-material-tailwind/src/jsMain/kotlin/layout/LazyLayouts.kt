package opensavvy.decouple.material.tailwind.layout

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.Snapshot
import opensavvy.decouple.core.layout.*
import org.jetbrains.compose.web.dom.Div
import web.dom.document
import web.dom.observers.IntersectionObserver
import kotlin.math.max
import kotlin.random.Random
import kotlin.random.nextUInt

object MTLazyLayouts : LazyLayouts {

	@Composable
	override fun LazyColumn(
		vertical: Arrangement,
		alignment: Alignment,
		content: LazyLayouts.LazyColumnScope.() -> Unit,
	) = key(content) {
		LazyLinearLayout(
			generator = { concreteLazyColumn(content) },
		) { items ->
			Column(vertical, alignment) {
				items()
			}
		}
	}

	@Composable
	override fun LazyRow(
		horizontal: Arrangement,
		alignment: Alignment,
		content: LazyLayouts.LazyRowScope.() -> Unit,
	) = key(content) {
		LazyLinearLayout(
			generator = { concreteLazyRow(content) },
		) { items ->
			Row(horizontal, alignment) {
				items()
			}
		}
	}
}

private class LazyItem(
	val key: Any?,
	val block: @Composable () -> Unit,
)

private typealias LazyItemsGenerator = () -> List<LazyItem>

private class LazyAdapter(val elements: MutableList<LazyItemsGenerator>) : LazyLayouts.LazyColumnScope, LazyLayouts.LazyRowScope {
	override fun item(key: Any?, block: @Composable () -> Unit) {
		elements += { listOf(LazyItem(key) { block() }) }
	}

	override fun items(count: Int, key: (index: Int) -> Any, block: @Composable (index: Int) -> Unit) {
		elements += {
			List(count) {
				LazyItem(key(it)) { block(it) }
			}
		}
	}

	override fun <K : Any> items(items: Iterable<K>, key: (item: K) -> Any, block: @Composable (item: K) -> Unit) {
		elements += {
			items.map {
				LazyItem(key(it)) { block(it) }
			}
		}
	}
}

private fun concreteLazyColumn(content: LazyLayouts.LazyColumnScope.() -> Unit): List<LazyItemsGenerator> =
	ArrayList<LazyItemsGenerator>()
		.also { LazyAdapter(it).apply(content) }

private fun concreteLazyRow(content: LazyLayouts.LazyRowScope.() -> Unit): List<LazyItemsGenerator> =
	ArrayList<LazyItemsGenerator>()
		.also { LazyAdapter(it).apply(content) }

@Composable
private fun LazyLinearLayout(
	generator: () -> List<LazyItemsGenerator>,
	layout: @Composable (@Composable () -> Unit) -> Unit,
) {
	val elements = remember { generator() }

	var nextIndex by remember { mutableStateOf(0) }
	val items = remember { mutableStateListOf<LazyItem>() }

	val visibilityDetector = remember(nextIndex, elements) {
		{
			if (nextIndex <= elements.lastIndex) {
				Snapshot.withMutableSnapshot {
					items += elements[nextIndex]()
					nextIndex++
				}
			}
		}
	}

	layout {
		if (items.isEmpty()) {
			VisibilityDetector(visibilityDetector)
		}

		for ((i, item) in items.withIndex()) {
			Div {
				// Add the visibility detector as part of an element to avoid duplicate margins
				if (i == max(0, items.size - 5)) {
					VisibilityDetector(visibilityDetector)
				}

				key(item.key) {
					item.block()
				}
			}
		}
	}
}

@Composable
private fun VisibilityDetector(onVisible: () -> Unit) {
	val id = remember { "visibility-observer-" + Random.nextUInt() }

	DisposableEffect(onVisible) {
		val div = document.getElementById(id) ?: run {
			console.warn("Decouple: could not find the div with identifier $id, the lazy elements are broken")
			return@DisposableEffect onDispose { /* Nothing to do */ }
		}

		val observer = IntersectionObserver(
			callback = { entries, _ ->
				if (entries.any { it.isIntersecting }) {
					onVisible()
				}
			}
		)

		observer.observe(div)

		onDispose {
			observer.disconnect()
		}
	}

	Div({
		id(id)
	})
}
