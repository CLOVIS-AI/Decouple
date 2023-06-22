package opensavvy.decouple.extra.html.lazy

import androidx.compose.runtime.Composable

class LazyDsl {
	internal val elements = ArrayList<LazyItemsGenerator>()

	fun item(
		key: Any? = null,
		block: @Composable () -> Unit,
	) {
		elements += {
			listOf(LazyItem(key) { block() })
		}
	}

	fun items(
		count: Int,
		key: (index: Int) -> Any,
		block: @Composable (index: Int) -> Unit,
	) {
		elements += {
			List(count) {
				LazyItem(key(it)) { block(it) }
			}
		}
	}

	fun <K : Any> items(
		items: Iterable<K>,
		key: (item: K) -> Any = { it },
		block: @Composable (item: K) -> Unit,
	) {
		elements += {
			items.map {
				LazyItem(key(it)) { block(it) }
			}
		}
	}
}
