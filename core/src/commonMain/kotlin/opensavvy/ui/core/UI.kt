package opensavvy.ui.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import opensavvy.ui.core.UI.Companion.Install
import opensavvy.ui.core.basic.Buttons
import opensavvy.ui.core.basic.Chips
import opensavvy.ui.core.basic.TextFields
import opensavvy.ui.core.basic.Texts
import opensavvy.ui.core.layout.LazyLayouts
import opensavvy.ui.core.layout.LinearLayouts
import opensavvy.ui.core.theme.Theme

/**
 * A fully-featured UI implementation.
 *
 * This interface is the single entrypoint to the entire API.
 * All components are declared in superinterfaces of this one.
 *
 * ### Usage
 *
 * First, you should get an instance of this interface.
 * Each implementation may do this differently, so read their documentation.
 *
 * Then, call its [Install] method:
 * ```kotlin
 * val ui: UI = // Your instance here
 * ui.Install {
 *     // you can use OpenSavvy UI components here
 * }
 * ```
 *
 * Most components expect to be called within an `Install` call.
 * If this is not the case, they will throw an [IllegalStateException].
 *
 * ### Implementing your own design system
 *
 * This interface is a regular Kotlin interface, implementing it is the same as with any other interface.
 * However, we recommend that you:
 * - implement each component kind in its own interface using default methods,
 * - implement UI as an object or class that delegates to the various interfaces.
 *
 * For example, for a UI implementation called `Foo`, this could look like:
 * ```kotlin
 * interface FooButtons : Buttons {
 *     // implement your own buttons
 * }
 *
 * interface FooUI : UI, FooButtons {
 *     // customize Install… if needed
 * }
 * ```
 *
 * The rationale behind this pattern is:
 * - component kinds should not have state and interfaces cannot have state,
 * - it is easy for end users to override a single component,
 * - it is easy for end users to replace the implementation of a component kind by another one,
 * - you may provide multiple implementations of entire component kinds.
 */
interface UI : LinearLayouts, LazyLayouts, Buttons, Texts, Chips, TextFields {

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
	val recommendedThemes get() = emptyList<Theme>()

	/**
	 * Prepares the interface so [content] can use all the components of this UI implementation.
	 *
	 * **Important.** This function is called internally by [Install].
	 * Calling this function directly will not correctly install this UI instance.
	 */
	@Composable
	fun initializeFor(content: @Composable () -> Unit) = content()

	/**
	 * Applies [theme] to [content].
	 *
	 * **Important.** This function is called internally by [Install][Theme.Install].
	 * Calling this function directly will not correctly install this Theme instance.
	 */
	@Composable
	fun initializeThemeFor(theme: Theme, content: @Composable () -> Unit) = content()

	companion object {

		private val LocalUI = staticCompositionLocalOf<UI?> { null }

		/**
		 * Access to the current [UI] instance.
		 *
		 * This property may only be accessed if a [UI] instance has been installed.
		 * For more information, see [Install].
		 */
		val current: UI
			@Composable get() = LocalUI.current ?: error("No UI instance was installed. See clovis.ui.Install().")

		/**
		 * Installs a [ui] implementation.
		 *
		 * All OS UI components declared within [content] will delegate their implementation [ui].
		 * To access the current UI implementation, see [current].
		 */
		@Composable
		fun Install(ui: UI, content: @Composable () -> Unit) {
			CompositionLocalProvider(LocalUI provides ui) {
				ui.initializeFor {
					content()
				}
			}
		}
	}

}
