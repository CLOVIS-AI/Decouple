package opensavvy.decouple.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import opensavvy.decouple.core.UI

/**
 * The visual identity of the application.
 *
 * UI implementations may provide one or multiple possible themes to the user.
 * This is a simple but efficient way of letting the user customize the application to their liking.
 */
interface Theme {

	/**
	 * Human-readable name of this theme.
	 *
	 * May be used in a theme switcher, for example.
	 */
	val name: String

	/**
	 * The color identity of the application.
	 */
	val color: ColorTheme

	companion object {

		/**
		 * The current theme.
		 */
		val Local = compositionLocalOf<Theme?> { null }

		/**
		 * Returns the current theme.
		 *
		 * @throws IllegalStateException No theme is available
		 */
		val current: Theme
			@Composable get() = Local.current
				?: error("No theme was enabled, you should call a component that provides one.")

		/**
		 * Returns [Theme.color] for the [current] theme.
		 *
		 * @throws IllegalStateException No theme is available
		 */
		val color: ColorTheme
			@Composable get() = current.color

		/**
		 * Installs [theme] to [content].
		 */
		@Composable
		fun Install(theme: Theme, content: @Composable () -> Unit) {
			CompositionLocalProvider(Local provides theme) {
				UI.current.initializeThemeFor(theme) {
					content()
				}
			}
		}
	}
}
