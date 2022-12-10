package opensavvy.decouple.core.layout

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.UI

interface LazyLayouts {

	@Composable
	fun LazyColumn(
		vertical: Arrangement,
		alignment: Alignment,
		content: LazyColumnScope.() -> Unit,
	)

	@Composable
	fun LazyRow(
		horizontal: Arrangement,
		alignment: Alignment,
		content: LazyRowScope.() -> Unit,
	)

	@LayoutScopeMarker
	interface LazyLayoutScope {

		fun item(content: @Composable LazyItemScope.() -> Unit)

		fun items(number: Int, content: @Composable LazyItemScope.(Int) -> Unit)

		fun <T> items(values: List<T>, content: @Composable LazyItemScope.(T) -> Unit)

	}

	interface LazyColumnScope : LazyLayoutScope

	interface LazyRowScope : LazyLayoutScope

	interface LazyItemScope
}

@Composable
fun LazyColumn(
	vertical: Arrangement = Arrangement.Start,
	alignment: Alignment = Alignment.Stretch,
	content: LazyLayouts.LazyColumnScope.() -> Unit,
) = UI.current.LazyColumn(vertical, alignment, content)

@Composable
fun LazyRow(
	horizontal: Arrangement = Arrangement.Start,
	alignment: Alignment = Alignment.Stretch,
	content: LazyLayouts.LazyRowScope.() -> Unit,
) = UI.current.LazyRow(horizontal, alignment, content)
