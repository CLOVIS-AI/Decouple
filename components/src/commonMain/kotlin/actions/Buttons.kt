package opensavvy.decouple.components.actions

import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope
import opensavvy.decouple.components.ExperimentalComponent
import opensavvy.decouple.components.RestrictedStabilityArgument
import opensavvy.decouple.components.concepts.ActionResponsibility
import opensavvy.decouple.components.launch
import opensavvy.decouple.components.rememberProgress
import opensavvy.progress.Progress

/**
 * Specification for buttons.
 *
 * Buttons are small components that have the purpose of letting a user perform an action.
 *
 * Buttons represent familiar calls to action: when a user navigates to a page, the same buttons are always visible
 * in the same place and perform the same action.
 */
interface Buttons {

	enum class Role {
		/** See [PrimaryButton]. */
		Primary,
		/** See [SecondaryButton]. */
		Secondary,
		/** See [ActionButton]. */
		Action,
		/** See [Button]. */
		Normal,

		@Suppress("unused")
		@Deprecated("We reserve the right to add more elements in the future.", level = DeprecationLevel.ERROR)
		Reserved,
	}

	/**
	 * See [Buttons].
	 */
	@Composable
	fun ButtonSpec(args: ButtonArgs)

	@Immutable
	@RestrictedStabilityArgument
	data class ButtonArgs(
		val onClick: () -> Unit,

		val role: Role,
		val enabled: Boolean,
		val progress: Progress,

		val icon: (@Composable () -> Unit)?,
		val content: @Composable () -> Unit,
	)
}

@ExperimentalComponent
@Composable
fun Buttons.AbstractButton(
	onClick: suspend () -> Unit,
	enabled: Boolean,
	scope: CoroutineScope,
	role: Buttons.Role,
	progress: Progress?,
	icon: (@Composable () -> Unit)?,
	content: @Composable () -> Unit,
) {
	var onClickProgress by rememberProgress()

	ButtonSpec(
		Buttons.ButtonArgs(
			onClick = {
				scope.launch(
					onProgress = { onClickProgress = it },
					block = onClick,
				)
			},
			role = role,
			enabled = enabled,
			progress = progress ?: onClickProgress,
			icon = icon,
			content = content,
		)
	)
}

/**
 * Basic buttons represents the most basic interactions users can have with a system.
 *
 * Unlike other kinds of buttons which are emphasized to catch the user's attention, basic buttons are subtle contextual actions.
 *
 * This component is [responsible][ActionResponsibility] for the [onClick] operation.
 */
@ExperimentalComponent
@Composable
fun Buttons.Button(
	onClick: suspend () -> Unit,
	enabled: Boolean = true,
	scope: CoroutineScope = rememberCoroutineScope(),
	progress: Progress? = null,
	icon: (@Composable () -> Unit)? = null,
	content: @Composable () -> Unit,
) = AbstractButton(
	onClick = onClick,
	enabled = enabled,
	scope = scope,
	role = Buttons.Role.Normal,
	progress = progress,
	icon = icon,
	content = content,
)

/**
 * The most important action on a page.
 *
 * Primary buttons are used to represent important, final actions that complete a flow, like "Save", "Join now" or "Confirm".
 *
 * A single page should never contain multiple primary buttons.
 * If you are tempted to, decide which one is the most important, and use [SecondaryButton] for the others.
 *
 * This component is [responsible][ActionResponsibility] for the [onClick] operation.
 */
@ExperimentalComponent
@Composable
fun Buttons.PrimaryButton(
	onClick: suspend () -> Unit,
	enabled: Boolean = true,
	scope: CoroutineScope = rememberCoroutineScope(),
	progress: Progress? = null,
	icon: (@Composable () -> Unit)? = null,
	content: @Composable () -> Unit,
) = AbstractButton(
	onClick = onClick,
	enabled = enabled,
	scope = scope,
	role = Buttons.Role.Primary,
	progress = progress,
	icon = icon,
	content = content,
)

/**
 * Important actions on a page.
 *
 * Secondary buttons represent the most important actions visible on screen, that are not important enough to warrant
 * using [PrimaryButton].
 *
 * For example, in a multistep onboarding flow, we recommend using a secondary button for the "next step" button,
 * but only use a [PrimaryButton] for the very last step (the one that ends the onboarding flow).
 *
 * This component is [responsible][ActionResponsibility] for the [onClick] operation.
 */
@ExperimentalComponent
@Composable
fun Buttons.SecondaryButton(
	onClick: suspend () -> Unit,
	enabled: Boolean = true,
	scope: CoroutineScope = rememberCoroutineScope(),
	progress: Progress? = null,
	icon: (@Composable () -> Unit)? = null,
	content: @Composable () -> Unit,
) = AbstractButton(
	onClick = onClick,
	enabled = enabled,
	scope = scope,
	role = Buttons.Role.Secondary,
	progress = progress,
	icon = icon,
	content = content,
)

/**
 * Alternative actions on a page.
 *
 * [PrimaryButton] and [SecondaryButton] represent the most important actions on a screen: the ones the user should be attracted to.
 * We sometimes need buttons to represent alternative paths through the experience—this is what action buttons are for.
 *
 * For example, in an onboarding flow, the [SecondaryButton] would be used to go to the next step.
 * [ActionButton] could be used to go back to a previous step, or to skip a step altogether.
 *
 * This component is [responsible][ActionResponsibility] for the [onClick] operation.
 */
@ExperimentalComponent
@Composable
fun Buttons.ActionButton(
	onClick: suspend () -> Unit,
	enabled: Boolean = true,
	scope: CoroutineScope = rememberCoroutineScope(),
	progress: Progress? = null,
	icon: (@Composable () -> Unit)? = null,
	content: @Composable () -> Unit,
) = AbstractButton(
	onClick = onClick,
	enabled = enabled,
	scope = scope,
	role = Buttons.Role.Action,
	progress = progress,
	icon = icon,
	content = content,
)
