package opensavvy.decouple.headless

import androidx.compose.runtime.Composable
import opensavvy.decouple.components.DesignSystem
import opensavvy.decouple.headless.components.actions.Buttons
import opensavvy.decouple.headless.components.display.Texts
import opensavvy.decouple.headless.components.layouts.LinearLayouts
import opensavvy.decouple.headless.debug.Spies
import opensavvy.decouple.headless.execution.runHeadlessUI

/**
 * The collection of all Decouple components that possess a headless implementation.
 *
 * @see runHeadlessUI Execute headless components
 * @see HeadlessDesignSystem Ready-made design system for these components.
 */
interface HeadlessDesignComponents :
	Buttons,
	Texts,
	LinearLayouts,
	Spies

/**
 * Ready-to-use implementation of [HeadlessDesignComponents] used as the default value when executing headless tests.
 */
internal object HeadlessDesignSystem : DesignSystem, HeadlessDesignComponents {

	override val name: String
		get() = "HeadlessUI"

	@Composable
	override fun initializeFor(content: @Composable () -> Unit) {
		content()
	}
}
