package opensavvy.decouple.material.tailwind.layout

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.Snapshot
import opensavvy.decouple.core.layout.*
import org.jetbrains.compose.web.dom.Div
import web.dom.document
import web.dom.observers.IntersectionObserver
import kotlin.random.Random
import kotlin.random.nextUInt

object MTLazyLayouts : LazyLayouts {

	@Composable
	override fun LazyColumn(
		vertical: Arrangement,
		alignment: Alignment,
		content: LazyLayouts.LazyColumnScope.() -> Unit,
	) {
		val elements: List<() -> List<LazyItem>> = remember(content) {
			val elements = ArrayList<() -> List<LazyItem>>()

			object : LazyLayouts.LazyColumnScope {
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
			}.apply(content)

			elements
		}

		var nextIndex by remember(content) { mutableStateOf(0) }

		val items = remember(content) { mutableStateListOf<LazyItem>() }

		Column(vertical, alignment) {
			for (item in items) {
				key(item.key) {
					item.block()
				}
			}

			key(content, nextIndex) {
				VisibilityDetector(
					onVisible = {
						if (nextIndex <= elements.lastIndex) {
							Snapshot.withMutableSnapshot {
								items += elements[nextIndex]()
								nextIndex++
							}
						}
					}
				)
			}
		}
	}

	@Composable
	override fun LazyRow(
		horizontal: Arrangement,
		alignment: Alignment,
		content: LazyLayouts.LazyRowScope.() -> Unit,
	) {
		Row(horizontal, alignment) {
			// TODO
		}
	}
}

private class LazyItem(
	val key: Any?,
	val block: @Composable () -> Unit,
)

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
