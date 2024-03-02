package opensavvy.decouple.components.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import opensavvy.decouple.components.ExperimentalComponent
import opensavvy.decouple.components.RestrictedStabilityArgument
import opensavvy.decouple.polymorphism.PolymorphicComponent

/**
 * Simple layouts that place elements next to each other.
 */
@ExperimentalComponent
interface LinearLayouts : PolymorphicComponent {

	/**
	 * Places each element in [LinearLayoutArgs.content] horizontally after the previous ones.
	 *
	 * Elements follow the reading order:
	 * - In LTR layouts, elements are placed to the right of previous elements.
	 * - In RTL layouts, elements are placed to the left of the previous elements.
	 */
	@Composable
	fun RowSpec(args: LinearLayoutArgs)

	/**
	 * Places each element in [LinearLayoutArgs.content] below the previous ones.
	 */
	@Composable
	fun ColumnSpec(args: LinearLayoutArgs)

	@Immutable
	@RestrictedStabilityArgument
	data class LinearLayoutArgs(
		/**
		 * Relative positioning along the main axis.
		 */
		val arrangement: Arrangement,
		/**
		 * Relative positioning along the secondary axis.
		 */
		val alignment: Alignment,
		/**
		 * If `true`, reverses the direction of the layout.
		 *
		 * For example, a vertical layout, which usually goes from top to bottom,
		 * goes from bottom to top.
		 */
		val reverse: Boolean,
		/**
		 * The children components of this layout.
		 */
		val content: @Composable () -> Unit,
	)
}

/**
 * Places each element of [content] one after the other, horizontally.
 *
 * Elements follow the reading order:
 * - In LTR layouts, elements are placed to the right of previous elements.
 * - In RTL layouts, elements are placed to the left of the previous elements.
 *
 * ### Example
 *
 * ```kotlin
 * Row {
 *     Text("Hello")
 *     Text("World")
 * }
 * ```
 *
 * Places `Hello` and `World` next to each other on the same line.
 *
 * @param reverse If set to `true`, elements are laid in the opposite order to reading order.
 */
@ExperimentalComponent
@Composable
fun LinearLayouts.Row(
	horizontal: Arrangement = Arrangement.Start,
	alignment: Alignment = Alignment.Stretch,
	reverse: Boolean = false,
	content: @Composable () -> Unit,
) {
	RowSpec(
		LinearLayouts.LinearLayoutArgs(
			arrangement = horizontal,
			alignment = alignment,
			reverse = reverse,
			content = content,
		)
	)
}

/**
 * Places each element of [content] one below the other, vertically.
 *
 * ### Example
 *
 * ```kotlin
 * Column {
 *     Text("Welcome!")
 *     Button({}) { Text("Enter") }
 * }
 * ```
 *
 * Places the button below the welcome message.
 *
 * @param reverse If set to `true`, elements are laid in the opposite order to reading order.
 */
@ExperimentalComponent
@Composable
fun LinearLayouts.Column(
	vertical: Arrangement = Arrangement.Start,
	alignment: Alignment = Alignment.Stretch,
	reverse: Boolean = false,
	content: @Composable () -> Unit,
) {
	ColumnSpec(
		LinearLayouts.LinearLayoutArgs(
			arrangement = vertical,
			alignment = alignment,
			reverse = reverse,
			content = content,
		)
	)
}
