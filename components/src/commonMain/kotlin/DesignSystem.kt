package opensavvy.decouple.components

import androidx.compose.runtime.Composable
import opensavvy.decouple.polymorphism.PolymorphicComponent

/**
 * Parent interface of all design systems.
 */
interface DesignSystem : PolymorphicComponent {

	/**
	 * Human-readable name of this implementation.
	 */
	val name: String

	/**
	 * Initializes the internal aspects of this design system such that is available
	 * inside [content].
	 *
	 * **Important.** Users should not call this function directly.
	 * Instead, call [Install].
	 */
	@Composable
	fun initializeFor(content: @Composable () -> Unit)

}

/**
 * Installs a [designSystem] such that all components called within [content] use it.
 */
@Composable
fun <D : DesignSystem> Install(designSystem: D, content: @Composable D.() -> Unit) {
	designSystem.initializeFor {
		designSystem.content()
	}
}
