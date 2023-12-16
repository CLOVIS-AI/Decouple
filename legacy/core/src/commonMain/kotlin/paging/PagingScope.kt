package opensavvy.decouple.core.paging

import androidx.compose.runtime.Composable

/**
 * Lazy item declaration for potentially-infinite content in components.
 *
 * Items are registered using the methods of this interface. They are always displayed in the same order as they are
 * displayed.
 */
@PagingDslMarker
interface PagingScope {

	/**
	 * Registers a single item.
	 *
	 * @param key A value to identify this element, which will be passed to [key][androidx.compose.runtime.key].
	 * If not specified, `̀null` is used.
	 */
	@PagingDslMarker
	fun item(
		key: Any? = null,
		block: @Composable () -> Unit,
	)

	/**
	 * Registers [count] items.
	 *
	 * @param key A function which accepts the index of one of the items declared by this function, and returns a value
	 * which will be passed to [key][androidx.compose.runtime.key].
	 * If not specified, the index itself is used, which may cause performance problems if the items are reordered.
	 */
	@PagingDslMarker
	fun items(
		count: Int,
		key: (index: Int) -> Any = { it },
		block: @Composable (index: Int) -> Unit,
	)

	/**
	 * Registers [items].
	 *
	 * @param key A function which accepts one of the values of [items], and returns a value which will be passed to
	 * [key][androidx.compose.runtime.key].
	 * If not specified, the item itself is used, which is safe for reordering.
	 */
	@PagingDslMarker
	fun <K : Any> items(
		items: Iterable<K>,
		key: (item: K) -> Any = { it },
		block: @Composable (item: K) -> Unit,
	)

}
