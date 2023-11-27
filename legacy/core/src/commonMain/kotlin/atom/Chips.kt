package opensavvy.decouple.core.atom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import opensavvy.decouple.core.UI
import opensavvy.decouple.core.layout.LinearLayouts
import opensavvy.decouple.core.progression.launch
import opensavvy.decouple.core.progression.rememberProgress
import opensavvy.progress.Progress

/**
 * Actions and filters provided to the user.
 *
 * [Buttons] represent major actions that branch the flow of the application to somewhere else (confirmation, cancellation, starting a new screen).
 * Chips have an effect on the current screen (filters…).
 *
 * Chips have three main slots:
 * - a `content` slot, which should most often be the text of the chip.
 * - an `icon` slot, which is a used to visually distinguish multiple chips from each other.
 * - an `action` slot, which is an icon used to identify what happens when the user interacts with the chip.
 *
 * It is recommended to only use one of `icon` and `action` to avoid information overload.
 *
 * The `contrasted` property can be set to `true` to increase the contrast of the chip.
 * This is useful if the cheap appears on an image or another pattern.
 * When appearing on a plain background, it should be set to `false`.
 *
 * Chips generally appear in [groups][ChipGroupSpec] of three or more elements.
 */
interface Chips {

	/**
	 * Bonus action available for the user.
	 *
	 * An assist chip should have an effect localized to the current screen.
	 * To represent actions that impact the navigation, use buttons instead.
	 *
	 * Assist chips should be mostly static (they should be the same every time the user accesses the page).
	 */
	@Composable
	fun AssistChipSpec(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progress,
		contrasted: Boolean,
		icon: (@Composable () -> Unit)?,
		action: (@Composable () -> Unit)?,
		content: @Composable ChipScope.() -> Unit,
	)

	/**
	 * Filter provided by the application to the user.
	 *
	 * Clicking on a filter chip should only let the user toggle the [active] attribute.
	 * When [active] is `true`, the chip should hide some information on the current page.
	 *
	 * Filter chips should be mostly static (they should be the same every time the user accesses the page).
	 *
	 * When a [ChipGroupSpec] consists only a filter chips, they can act as checkboxes (any combination is valid) or as
	 * radio buttons (activating a chip disables the others).
	 *
	 * Filter chips have no action except being activated/unactivated, they therefore do not need an `action` slot.
	 */
	@Composable
	fun FilterChipSpec(
		active: Boolean,
		onToggle: (Boolean) -> Unit,
		enabled: Boolean,
		loading: Progress,
		contrasted: Boolean,
		icon: (@Composable () -> Unit)?,
		content: @Composable ChipScope.() -> Unit,
	)

	/**
	 * Filter provided by the user to the application.
	 *
	 * The `action` slot on this composable is used to delete the chip.
	 * It cannot be customized by the caller.
	 * When the user interacts with it, [onRemove] is called.
	 */
	@Composable
	fun InputChipSpec(
		onRemove: () -> Unit,
		enabled: Boolean,
		loading: Progress,
		contrasted: Boolean,
		icon: (@Composable () -> Unit)?,
		content: @Composable ChipScope.() -> Unit,
	)

	/**
	 * Suggestion chips are dynamically generated for the current interaction.
	 *
	 * They may or may not appear when the user next sees the component.
	 * For example, they can be used to copy text detected in a picture.
	 * For this reason, they are generally more visible than the other types of chips.
	 *
	 * Suggestion chips are also the only chips that can appear by themselves, outside a [ChipGroupSpec].
	 * Unlike other chips, they can also have an impact outside the current screen (e.g. opening another app).
	 */
	@Composable
	fun SuggestionChipSpec(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progress,
		contrasted: Boolean,
		icon: (@Composable () -> Unit)?,
		action: (@Composable () -> Unit)?,
		content: @Composable ChipScope.() -> Unit,
	)

	/**
	 * A group of [Chips].
	 *
	 * This component places the various chips horizontally with the correct spacing.
	 */
	@Composable
	fun ChipGroupSpec(
		multiline: Boolean,
		chips: @Composable ChipGroupScope.() -> Unit,
	)

	interface ChipScope

	interface ChipGroupScope : LinearLayouts.RowScope
}

/**
 * Bonus action available to the user.
 *
 * For more information, see [Chips.AssistChipSpec].
 */
@Composable
fun AssistChip(
	onClick: suspend () -> Unit,
	enabled: Boolean = true,
	contrasted: Boolean = false,
	scope: CoroutineScope = rememberCoroutineScope(),
	icon: (@Composable () -> Unit)? = null,
	action: (@Composable () -> Unit)? = null,
	content: @Composable Chips.ChipScope.() -> Unit,
) {
	var loading by rememberProgress()

	UI.current.AssistChipSpec(
		onClick = {
			scope.launch(
				onProgress = { loading = it },
				block = onClick,
			)
		},
		enabled = enabled,
		loading = loading,
		contrasted = contrasted,
		icon = icon,
		action = action,
		content = content,
	)
}

/**
 * Filter provided by the application to the user.
 *
 * For more information, see [Chips.FilterChipSpec].
 */
@Composable
fun FilterChip(
	active: Boolean,
	onToggle: suspend (Boolean) -> Unit,
	enabled: Boolean = true,
	contrasted: Boolean = false,
	scope: CoroutineScope = rememberCoroutineScope(),
	icon: (@Composable () -> Unit)? = null,
	content: @Composable Chips.ChipScope.() -> Unit,
) {
	var loading by rememberProgress()

	UI.current.FilterChipSpec(
		active = active,
		onToggle = { bool ->
			scope.launch(
				onProgress = { loading = it },
			) { onToggle(bool) }
		},
		enabled = enabled,
		loading = loading,
		contrasted = contrasted,
		icon = icon,
		content = content,
	)
}

/**
 * Filter provided by the user to the application.
 *
 * For more information, see [Chips.InputChipSpec].
 */
@Composable
fun InputChip(
	onRemove: suspend () -> Unit,
	enabled: Boolean = true,
	contrasted: Boolean = false,
	scope: CoroutineScope = rememberCoroutineScope(),
	icon: (@Composable () -> Unit)? = null,
	content: @Composable Chips.ChipScope.() -> Unit,
) {
	var loading by rememberProgress()

	UI.current.InputChipSpec(
		onRemove = {
			scope.launch(
				onProgress = { loading = it },
				block = onRemove,
			)
		},
		enabled = enabled,
		loading = loading,
		contrasted = contrasted,
		icon = icon,
		content = content,
	)
}

/**
 * Highlighted context-dependent action.
 *
 * For more information, see [Chips.SuggestionChipSpec].
 */
@Composable
fun SuggestionChip(
	onClick: suspend () -> Unit,
	enabled: Boolean = true,
	contrasted: Boolean = false,
	scope: CoroutineScope = rememberCoroutineScope(),
	icon: (@Composable () -> Unit)? = null,
	action: (@Composable () -> Unit)? = null,
	content: @Composable Chips.ChipScope.() -> Unit,
) {
	var loading by rememberProgress()

	UI.current.SuggestionChipSpec(
		onClick = {
			scope.launch(
				onProgress = { loading = it },
				block = onClick,
			)
		},
		enabled = enabled,
		loading = loading,
		contrasted = contrasted,
		icon = icon,
		action = action,
		content = content,
	)
}

/**
 * A group of [Chips].
 *
 * For more information, see [Chips.ChipGroupSpec].
 */
@Composable
fun ChipGroup(
	multiline: Boolean = false,
	chips: @Composable Chips.ChipGroupScope.() -> Unit,
) {
	UI.current.ChipGroupSpec(multiline, chips)
}
