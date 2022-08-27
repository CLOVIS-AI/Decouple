package opensavvy.ui.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import opensavvy.ui.core.basic.Buttons
import opensavvy.ui.core.basic.Texts
import opensavvy.ui.core.layout.LinearLayouts

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
interface UI : LinearLayouts, Buttons, Texts {

	/**
	 * Installs this UI implementation.
	 *
	 * All components declared in this module and called within [content] will delegate their implementation to this UI.
	 *
	 * To access the current UI implementation, see [current].
	 *
	 * **Note to implementors**: if you override this function, do not forget to call its original implementation (`super.Install { … }`).
	 * Otherwise, this UI will not be properly installed.
	 */
	@Composable
	fun Install(content: @Composable () -> Unit) {
		CompositionLocalProvider(LocalUI provides this) {
			content()
		}
	}

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

	}

}
