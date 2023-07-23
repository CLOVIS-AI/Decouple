package opensavvy.decouple.core.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import opensavvy.decouple.core.UI

interface LinearLayouts {

	/**
	 * Places each element in [content] below the previous ones.
	 */
	@Composable
	fun ColumnSpec(
		vertical: Arrangement,
		alignment: Alignment,
		content: @Composable ColumnScope.() -> Unit,
	)

	/**
	 * Places each element in [content] horizontally after the previous ones.
	 *
	 * Elements follow the reading order:
	 * - In LTR layouts, all elements are to the right of the previous ones.
	 * - In RTL layouts, all elements are to the left of the previous ones.
	 */
	@Composable
	fun RowSpec(
		horizontal: Arrangement,
		alignment: Alignment,
		content: @Composable RowScope.() -> Unit,
	)

	/**
	 * Places each element in [content] on top of the previous ones.
	 *
	 * Because the main axis is towards the screen, there is no [Arrangement].
	 * Instead, [alignment] is used for both the horizontal and vertical axis.
	 */
	@Composable
	fun BoxSpec(
		alignment: Alignment,
		content: @Composable BoxScope.() -> Unit,
	)

	// TODO: Doc
	@Composable
	fun GridSpec(
		horizontal: IntRange,
		vertical: IntRange,
		content: @Composable GridScope.(Int, Int) -> Unit,
	)

	@LayoutScopeMarker
	interface LinearLayoutScope

	interface ColumnScope : LinearLayoutScope

	interface RowScope : LinearLayoutScope

	interface BoxScope : LinearLayoutScope

	interface GridScope : LinearLayoutScope
}

/**
 * Places each element in [content] below the previous ones.
 *
 * For more information, see [LinearLayouts.ColumnSpec].
 */
@Composable
fun Column(
	vertical: Arrangement = Arrangement.Start,
	alignment: Alignment = Alignment.Stretch,
	content: @Composable LinearLayouts.ColumnScope.() -> Unit,
) = UI.current.ColumnSpec(vertical, alignment, content)

/**
 * Places each element in [content] above the previous ones.
 *
 * For more information, see [LinearLayouts.RowSpec].
 */
@Composable
fun Row(
	horizontal: Arrangement = Arrangement.Start,
	alignment: Alignment = Alignment.Stretch,
	content: @Composable LinearLayouts.RowScope.() -> Unit,
) = UI.current.RowSpec(horizontal, alignment, content)

/**
 * Places each element in [content] on top of the previous ones.
 *
 * For more information, see [LinearLayouts.BoxSpec].
 */
@Composable
fun Box(
	alignment: Alignment = Alignment.Stretch,
	content: @Composable LinearLayouts.BoxScope.() -> Unit,
) = UI.current.BoxSpec(alignment, content)

@Composable
fun Grid(
	horizontal: IntRange,
	vertical: IntRange,
	content: @Composable LinearLayouts.GridScope.(Int, Int) -> Unit,
) = UI.current.GridSpec(horizontal, vertical, content)

@Composable
fun <T> Grid(
	data: List<List<T>>,
	content: @Composable LinearLayouts.GridScope.(T) -> Unit,
) {
	val maxHorizontal = remember(data) { data.maxOf { it.size } }

	Grid(
		horizontal = data.indices,
		vertical = 0 until maxHorizontal,
	) { x, y ->
		val item = data[y].getOrNull(x)
		if (item != null) {
			content(item)
		}
	}
}
