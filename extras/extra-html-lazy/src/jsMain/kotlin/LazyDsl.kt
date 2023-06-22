package opensavvy.decouple.extra.html.lazy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key as composeKey

/**
 * DSL to declare items in a lazy container.
 *
 * Items will be loaded in the same order as functions are called on this object.
 */
class LazyDsl {
	internal val elements = ArrayList<LazyItemsGenerator>()

	/**
	 * Adds [block] as an item of this lazy container.
	 *
	 * @param key A locally-unique identifier for this item.
	 * For more information, see [key][composeKey].
	 */
	fun item(
		key: Any? = null,
		block: @Composable () -> Unit,
	) {
		elements += {
			listOf(LazyItem(key) { block() })
		}
	}

	/**
	 * Adds [count] elements to this lazy container.
	 *
	 * Each item is displayed by calling [block] and passing the item index (from `0` inclusive to `count` exclusive).
	 *
	 * @param key A function which generates a locally-unique identifier for an item from its index.
	 * For more information, see [key][composeKey].
	 */
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

	/**
	 * Adds all the items of [items] to this lazy container.
	 *
	 * Each item is displayed by calling [block] and passing the item.
	 *
	 * @param key A function which generates a locally-unique identifier for an item.
	 * By default, uses the item itself as its own key.
	 * For more information, see [key][composeKey].
	 */
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
