package opensavvy.decouple.material3.androidx

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import opensavvy.decouple.components.DesignSystem
import opensavvy.decouple.material3.androidx.components.actions.Buttons
import opensavvy.decouple.material3.androidx.components.display.Texts
import opensavvy.decouple.material3.androidx.components.layouts.LinearLayouts

/**
 * A single interface that contains all components provided by this module.
 */
interface Material3 : DesignSystem,
	Buttons,
	LinearLayouts,
	Texts {

	override val name: String
		get() = "Material3 (AndroidX implementation)"

	@Composable
	override fun initializeFor(content: @Composable () -> Unit) {
		MaterialTheme {
			content()
		}
	}
}
