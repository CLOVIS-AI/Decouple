package opensavvy.decouple.core.atom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import opensavvy.decouple.core.UI
import opensavvy.decouple.core.progression.launch
import opensavvy.decouple.core.progression.rememberProgress
import opensavvy.progress.Progress

interface Buttons {

	/**
	 * The most common kind of button.
	 *
	 * This should be used for low priority actions, especially when there are multiple of them.
	 */
	@Composable
	fun ButtonSpec(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progress,
		icon: (@Composable () -> Unit)?,
		content: @Composable ButtonScope.() -> Unit,
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
	fun PrimaryButtonSpec(
		onClick: () -> Unit,
		primary: Boolean,
		enabled: Boolean,
		loading: Progress,
		icon: (@Composable () -> Unit)?,
		content: @Composable ButtonScope.() -> Unit,
	)

	/**
	 * Medium-emphasis button.
	 *
	 * These buttons are used for actions that are important, but are not the primary action of the page, unlike [PrimaryButtonSpec].
	 * For example, if [PrimaryButtonSpec] represents the page confirmation, [SecondaryButtonSpec] could be the cancellation option.
	 */
	@Composable
	fun SecondaryButtonSpec(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progress,
		icon: (@Composable () -> Unit)?,
		content: @Composable ButtonScope.() -> Unit,
	)

	/**
	 * Button variant used where high contrast is necessary.
	 *
	 * For example, this ma happen when displaying on top of an image.
	 * Otherwise, they are used similarly to [PrimaryButtonSpec].
	 *
	 * Because they require heavier decoration to separate them from the page, they should be used sparingly.
	 */
	@Composable
	fun ContrastButtonSpec(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progress,
		icon: (@Composable () -> Unit)?,
		content: @Composable ButtonScope.() -> Unit,
	)

	interface ButtonScope
}

/**
 * The most common kind of button.
 *
 * For more information, see [Buttons.ButtonSpec].
 */
@Composable
fun Button(
	onClick: suspend () -> Unit,
	enabled: Boolean = true,
	scope: CoroutineScope = rememberCoroutineScope(),
	icon: (@Composable () -> Unit)? = null,
	content: @Composable Buttons.ButtonScope.() -> Unit,
) {
	var loading by rememberProgress()

	UI.current.ButtonSpec(
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
 * For more information, see [Buttons.PrimaryButtonSpec].
 */
@Composable
fun PrimaryButton(
	onClick: suspend () -> Unit,
	primary: Boolean = false,
	enabled: Boolean = true,
	scope: CoroutineScope = rememberCoroutineScope(),
	icon: (@Composable () -> Unit)? = null,
	content: @Composable Buttons.ButtonScope.() -> Unit,
) {
	var loading by rememberProgress()

	UI.current.PrimaryButtonSpec(
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
 * For more information, see [Buttons.SecondaryButtonSpec].
 */
@Composable
fun SecondaryButton(
	onClick: suspend () -> Unit,
	enabled: Boolean = true,
	icon: (@Composable () -> Unit)? = null,
	scope: CoroutineScope = rememberCoroutineScope(),
	content: @Composable Buttons.ButtonScope.() -> Unit,
) {
	var loading by rememberProgress()

	UI.current.SecondaryButtonSpec(
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
 * For more information, see [Buttons.SecondaryButtonSpec].
 */
@Composable
fun ContrastButton(
	onClick: suspend () -> Unit,
	enabled: Boolean = true,
	scope: CoroutineScope = rememberCoroutineScope(),
	icon: (@Composable () -> Unit)? = null,
	content: @Composable Buttons.ButtonScope.() -> Unit,
) {
	var loading by rememberProgress()

	UI.current.ContrastButtonSpec(
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
