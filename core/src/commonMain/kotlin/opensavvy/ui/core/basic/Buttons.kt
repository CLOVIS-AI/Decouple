package opensavvy.ui.core.basic

import androidx.compose.runtime.*
import opensavvy.ui.core.UI
import opensavvy.ui.core.progression.Progression
import opensavvy.ui.core.progression.ReportProgression
import opensavvy.ui.core.progression.launch

interface Buttons {

	/**
	 * The most common kind of button.
	 *
	 * This should be used for low priority actions, especially when there are multiple of them.
	 */
	@Composable
	fun Button(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		icon: (@Composable () -> Unit)?,
		content: @Composable () -> Unit,
	)

	/**
	 * Important buttons.
	 *
	 * These buttons are used to bring the user's attention to their actions.
	 *
	 * To mark this button as even more important, set [primary] to `true`.
	 * Only one or two buttons should be marked as [primary] per page.
	 */
	@Composable
	fun PrimaryButton(
		onClick: () -> Unit,
		primary: Boolean,
		enabled: Boolean,
		loading: Progression,
		icon: (@Composable () -> Unit)?,
		content: @Composable () -> Unit,
	)

	/**
	 * Medium-emphasis button.
	 *
	 * These buttons are used for actions that are important, but are not the primary action of the page, unlike [PrimaryButton].
	 * For example, if [PrimaryButton] represents the page confirmation, [SecondaryButton] could be the cancellation option.
	 */
	@Composable
	fun SecondaryButton(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		icon: (@Composable () -> Unit)?,
		content: @Composable () -> Unit,
	)

	/**
	 * Button variant used where high contrast is necessary.
	 *
	 * For example, this ma happen when displaying on top of an image.
	 * Otherwise, they are used similarly to [PrimaryButton].
	 *
	 * Because they require heavier decoration to separate them from the page, they should be used sparingly.
	 */
	@Composable
	fun ContrastButton(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		icon: (@Composable () -> Unit)?,
		content: @Composable () -> Unit,
	)

}

/**
 * The most common kind of button.
 *
 * For more information, see [Buttons.Button].
 */
@Composable
fun Button(
	onClick: suspend ReportProgression.() -> Unit,
	enabled: Boolean = true,
	icon: (@Composable () -> Unit)? = null,
	content: @Composable () -> Unit,
) {
	val scope = rememberCoroutineScope()
	var loading by remember { mutableStateOf<Progression>(Progression.Done) }

	UI.current.Button(
		onClick = {
			scope.launch(
				onProgress = { loading = it },
				block = onClick,
			)
		},
		enabled = enabled,
		loading = loading,
		icon = icon,
		content = content,
	)
}

/**
 * Important buttons.
 *
 * For more information, see [Buttons.PrimaryButton].
 */
@Composable
fun PrimaryButton(
	onClick: suspend ReportProgression.() -> Unit,
	primary: Boolean = false,
	enabled: Boolean = true,
	icon: (@Composable () -> Unit)? = null,
	content: @Composable () -> Unit,
) {
	val scope = rememberCoroutineScope()
	var loading by remember { mutableStateOf<Progression>(Progression.Done) }

	UI.current.PrimaryButton(
		onClick = {
			scope.launch(
				onProgress = { loading = it },
				block = onClick,
			)
		},
		primary = primary,
		enabled = enabled,
		loading = loading,
		icon = icon,
		content = content,
	)
}

/**
 * Medium-emphasis button.
 *
 * For more information, see [Buttons.SecondaryButton].
 */
@Composable
fun SecondaryButton(
	onClick: suspend ReportProgression.() -> Unit,
	enabled: Boolean = true,
	icon: (@Composable () -> Unit)? = null,
	content: @Composable () -> Unit,
) {
	val scope = rememberCoroutineScope()
	var loading by remember { mutableStateOf<Progression>(Progression.Done) }

	UI.current.SecondaryButton(
		onClick = {
			scope.launch(
				onProgress = { loading = it },
				block = onClick,
			)
		},
		enabled = enabled,
		loading = loading,
		icon = icon,
		content = content,
	)
}

/**
 * Button variant used where high contrast is necessary.
 *
 * For more information, see [Buttons.SecondaryButton].
 */
@Composable
fun ContrastButton(
	onClick: suspend ReportProgression.() -> Unit,
	enabled: Boolean = true,
	icon: (@Composable () -> Unit)? = null,
	content: @Composable () -> Unit,
) {
	val scope = rememberCoroutineScope()
	var loading by remember { mutableStateOf<Progression>(Progression.Done) }

	UI.current.ContrastButton(
		onClick = {
			scope.launch(
				onProgress = { loading = it },
				block = onClick,
			)
		},
		enabled = enabled,
		loading = loading,
		icon = icon,
		content = content,
	)
}
