package opensavvy.decouple.core

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.UI.Companion.Install
import opensavvy.decouple.core.theme.Theme

/**
 * Metadata and initialization code for [UI] instances.
 */
interface UIMetadata {

	/**
	 * Human-readable name of this implementation.
	 */
	val name: String

	/**
	 * Themes recommended by this [UI] instance.
	 *
	 * Although it is allowed to use any theme with any UI instance, it is possible that they do not follow the same
	 * style and visually clash.
	 *
	 * Themes returned by this property are chosen by this UI as meant for each other, and are more likely to lead to
	 * beautiful results.
	 *
	 * This list may be empty.
	 */
	val recommendedThemes: List<Theme>

	/**
	 * Prepares the interface so [content] can use all the components of this UI implementation.
	 *
	 * **Important.** This function is called internally by [Install].
	 * Calling this function directly will not correctly install this UI instance.
	 */
	@Composable
	fun initializeFor(content: @Composable () -> Unit)

	/**
	 * Applies [theme] to [content].
	 *
	 * **Important.** This function is called internally by [Install][Theme.Install].
	 * Calling this function directly will not correctly install this Theme instance.
	 */
	@Composable
	fun initializeThemeFor(theme: Theme, content: @Composable () -> Unit)

}
