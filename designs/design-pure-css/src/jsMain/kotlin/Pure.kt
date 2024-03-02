package opensavvy.decouple.purecss

import androidx.compose.runtime.Composable
import opensavvy.decouple.components.DesignSystem
import opensavvy.decouple.purecss.components.actions.Buttons
import opensavvy.decouple.purecss.components.display.Texts
import opensavvy.decouple.purecss.components.layouts.LinearLayouts

/**
 * A single interface that contains all components provided by this module.
 */
interface PureCSS : DesignSystem,
	Buttons,
	LinearLayouts,
	Texts {

	override val name: String
		get() = "PureCSS"

	@Composable
	override fun initializeFor(content: @Composable () -> Unit) {
		content()
	}
}
