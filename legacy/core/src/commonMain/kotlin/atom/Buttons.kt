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

interface ButtonAttrs {
	val onClick: () -> Unit

	val role: Buttons.Role
	val enabled: Boolean
	val loading: Progress
	val contrasted: Boolean

	val icon: (@Composable () -> Unit)?
	val content: (@Composable Buttons.ButtonScope.() -> Unit)
}

interface Buttons {

	enum class Role {
		Primary,
		Secondary,
		Action,
		Normal,

		@Suppress("unused")
		@Deprecated("We reserve the right to add more elements in the future.", level = DeprecationLevel.ERROR)
		Reserved,
	}

	/**
	 * The most common kind of button.
	 *
	 * This should be used for low priority actions, especially when there are multiple of them.
	 */
	@Composable
	fun ButtonSpec(attrs: ButtonAttrs)

	interface ButtonScope
}

private data class ButtonAttrsImpl(
	override val onClick: () -> Unit,
	override val role: Buttons.Role,
	override val enabled: Boolean,
	override val loading: Progress,
	override val icon: (@Composable () -> Unit)?,
	override val content: @Composable Buttons.ButtonScope.() -> Unit,
	override val contrasted: Boolean,
) : ButtonAttrs

@Composable
fun AbstractButton(
	onClick: suspend () -> Unit,
	enabled: Boolean = true,
	scope: CoroutineScope,
	icon: (@Composable () -> Unit)? = null,
	content: @Composable Buttons.ButtonScope.() -> Unit,
	role: Buttons.Role = Buttons.Role.Normal,
	contrasted: Boolean = false,
) {
	var loading by rememberProgress()

	UI.current.ButtonSpec(
		ButtonAttrsImpl(
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
			role = role,
			contrasted = contrasted,
		)
	)
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
) = AbstractButton(
	onClick = onClick,
	enabled = enabled,
	scope = scope,
	icon = icon,
	content = content,
)

/**
 * Important buttons.
 */
@Composable
fun PrimaryButton(
	onClick: suspend () -> Unit,
	enabled: Boolean = true,
	scope: CoroutineScope = rememberCoroutineScope(),
	icon: (@Composable () -> Unit)? = null,
	content: @Composable Buttons.ButtonScope.() -> Unit,
) = AbstractButton(
	onClick = onClick,
	enabled = enabled,
	scope = scope,
	icon = icon,
	content = content,
	role = Buttons.Role.Primary,
)

@Composable
fun SecondaryButton(
	onClick: suspend () -> Unit,
	enabled: Boolean = true,
	scope: CoroutineScope = rememberCoroutineScope(),
	icon: (@Composable () -> Unit)? = null,
	content: @Composable Buttons.ButtonScope.() -> Unit,
) = AbstractButton(
	onClick = onClick,
	enabled = enabled,
	scope = scope,
	icon = icon,
	content = content,
	role = Buttons.Role.Secondary,
)

/**
 * Medium-emphasis button.
 */
@Composable
fun ActionButton(
	onClick: suspend () -> Unit,
	enabled: Boolean = true,
	icon: (@Composable () -> Unit)? = null,
	scope: CoroutineScope = rememberCoroutineScope(),
	content: @Composable Buttons.ButtonScope.() -> Unit,
) = AbstractButton(
	onClick = onClick,
	enabled = enabled,
	scope = scope,
	icon = icon,
	content = content,
	role = Buttons.Role.Action,
)

/**
 * Button variant used where high contrast is necessary.
 */
@Composable
fun ContrastButton(
	onClick: suspend () -> Unit,
	enabled: Boolean = true,
	scope: CoroutineScope = rememberCoroutineScope(),
	icon: (@Composable () -> Unit)? = null,
	content: @Composable Buttons.ButtonScope.() -> Unit,
) = AbstractButton(
	onClick = onClick,
	enabled = enabled,
	scope = scope,
	icon = icon,
	content = content,
	contrasted = true,
)
