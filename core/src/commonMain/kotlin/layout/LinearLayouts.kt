package opensavvy.decouple.core.layout

import androidx.compose.runtime.Composable
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

	@LayoutScopeMarker
	interface LinearLayoutScope

	interface ColumnScope : LinearLayoutScope

	interface RowScope : LinearLayoutScope

	interface BoxScope : LinearLayoutScope
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
