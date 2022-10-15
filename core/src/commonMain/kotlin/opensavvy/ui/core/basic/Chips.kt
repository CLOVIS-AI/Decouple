package opensavvy.ui.core.basic

import androidx.compose.runtime.*
import opensavvy.ui.core.UI
import opensavvy.ui.core.progression.Progression
import opensavvy.ui.core.progression.ReportProgression
import opensavvy.ui.core.progression.launch

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
 * Chips generally appear in [groups][ChipGroup] of three or more elements.
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
	fun AssistChip(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		contrasted: Boolean,
		icon: (@Composable () -> Unit)?,
		action: (@Composable () -> Unit)?,
		content: @Composable () -> Unit,
	)

	/**
	 * Filter provided by the application to the user.
	 *
	 * Clicking on a filter chip should only let the user toggle the [activated] attribute.
	 * When [activated] is `true`, the chip should hide some information on the current page.
	 *
	 * Filter chips should be mostly static (they should be the same every time the user accesses the page).
	 *
	 * When a [ChipGroup] consists only a filter chips, they can act as checkboxes (any combination is valid) or as
	 * radio buttons (activating a chip disables the others).
	 *
	 * Filter chips have no action except being activated/unactivated, they therefore do not need an `action` slot.
	 */
	@Composable
	fun FilterChip(
		activated: Boolean,
		onToggle: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		contrasted: Boolean,
		icon: (@Composable () -> Unit)?,
		content: @Composable () -> Unit,
	)

	/**
	 * Filter provided by the user to the application.
	 *
	 * The `action` slot on this composable is used to delete the chip.
	 * It cannot be customized by the caller.
	 * When the user interacts with it, [onRemoval] is called.
	 */
	@Composable
	fun InputChip(
		onRemoval: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		contrasted: Boolean,
		icon: (@Composable () -> Unit)?,
		content: @Composable () -> Unit,
	)

	/**
	 * Suggestion chips are dynamically generated for the current interaction.
	 *
	 * They may or may not appear when the user next sees the component.
	 * For example, they can be used to copy text detected in a picture.
	 * For this reason, they are generally more visible than the other types of chips.
	 *
	 * Suggestion chips are also the only chips that can appear by themselves, outside a [ChipGroup].
	 * Unlike other chips, they can also have an impact outside the current screen (e.g. opening another app).
	 */
	@Composable
	fun SuggestionChip(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		contrasted: Boolean,
		icon: (@Composable () -> Unit)?,
		action: (@Composable () -> Unit)?,
		content: @Composable () -> Unit,
	)

	/**
	 * A group of [Chips].
	 *
	 * This component places the various chips horizontally with the correct spacing.
	 */
	@Composable
	fun ChipGroup(
		multiline: Boolean,
		chips: @Composable () -> Unit,
	)
}

/**
 * Bonus action available to the user.
 *
 * For more information, see [Chips.AssistChip].
 */
@Composable
fun AssistChip(
	onClick: suspend ReportProgression.() -> Unit,
	enabled: Boolean = true,
	contrasted: Boolean = false,
	icon: (@Composable () -> Unit)? = null,
	action: (@Composable () -> Unit)? = null,
	content: @Composable () -> Unit,
) {
	val scope = rememberCoroutineScope()
	var loading by remember { mutableStateOf<Progression>(Progression.Done) }

	UI.current.AssistChip(
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
 * For more information, see [Chips.FilterChip].
 */
@Composable
fun FilterChip(
	activated: Boolean,
	onToggle: suspend ReportProgression.() -> Unit,
	enabled: Boolean = true,
	contrasted: Boolean = false,
	icon: (@Composable () -> Unit)? = null,
	content: @Composable () -> Unit,
) {
	val scope = rememberCoroutineScope()
	var loading by remember { mutableStateOf<Progression>(Progression.Done) }

	UI.current.FilterChip(
		activated = activated,
		onToggle = {
			scope.launch(
				onProgress = { loading = it },
				block = onToggle,
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
 * Filter provided by the user to the application.
 *
 * For more information, see [Chips.InputChip].
 */
@Composable
fun InputChip(
	onRemoval: suspend ReportProgression.() -> Unit,
	enabled: Boolean = true,
	contrasted: Boolean = false,
	icon: (@Composable () -> Unit)? = null,
	content: @Composable () -> Unit,
) {
	val scope = rememberCoroutineScope()
	var loading by remember { mutableStateOf<Progression>(Progression.Done) }

	UI.current.InputChip(
		onRemoval = {
			scope.launch(
				onProgress = { loading = it },
				block = onRemoval,
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
 * For more information, see [Chips.SuggestionChip].
 */
@Composable
fun SuggestionChip(
	onClick: suspend ReportProgression.() -> Unit,
	enabled: Boolean = true,
	contrasted: Boolean = false,
	icon: (@Composable () -> Unit)? = null,
	action: (@Composable () -> Unit)? = null,
	content: @Composable () -> Unit,
) {
	val scope = rememberCoroutineScope()
	var loading by remember { mutableStateOf<Progression>(Progression.Done) }

	UI.current.SuggestionChip(
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
 * For more information, see [Chips.ChipGroup].
 */
@Composable
fun ChipGroup(
	multiline: Boolean = false,
	chips: @Composable () -> Unit,
) {
	UI.current.ChipGroup(multiline, chips)
}
