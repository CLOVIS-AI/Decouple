package opensavvy.decouple.extra.html.lazy

import androidx.compose.runtime.Composable

internal class LazyItem(
	val key: Any?,
	val block: @Composable () -> Unit,
)

internal typealias LazyItemsGenerator = () -> List<LazyItem>
