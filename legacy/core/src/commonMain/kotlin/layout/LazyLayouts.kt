package opensavvy.decouple.core.layout

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.UI
import opensavvy.decouple.core.paging.PagingScope

interface LazyLayouts {

	@Composable
	fun LazyColumnSpec(
		vertical: Arrangement,
		alignment: Alignment,
		content: LazyColumnScope.() -> Unit,
	)

	@Composable
	fun LazyRowSpec(
		horizontal: Arrangement,
		alignment: Alignment,
		content: LazyRowScope.() -> Unit,
	)

	@LayoutScopeMarker
	interface LazyColumnScope : PagingScope

	@LayoutScopeMarker
	interface LazyRowScope : PagingScope

	interface LazyItemScope
}

@Composable
fun LazyColumn(
	vertical: Arrangement = Arrangement.Start,
	alignment: Alignment = Alignment.Stretch,
	content: LazyLayouts.LazyColumnScope.() -> Unit,
) = UI.current.LazyColumnSpec(vertical, alignment, content)

@Composable
fun LazyRow(
	horizontal: Arrangement = Arrangement.Start,
	alignment: Alignment = Alignment.Stretch,
	content: LazyLayouts.LazyRowScope.() -> Unit,
) = UI.current.LazyRowSpec(horizontal, alignment, content)
