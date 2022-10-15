package opensavvy.ui.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

/**
 * The visual identity of the application.
 *
 * UI implementations may provide one or multiple possible themes to the user.
 * This is a simple but efficient way of letting the user customize the application to their liking.
 */
interface Theme {

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
		 * Installs [theme] to [block].
		 */
		@Composable
		fun Install(theme: Theme, block: @Composable () -> Unit) {
			CompositionLocalProvider(Local provides theme) {
				block()
			}
		}
	}
}
