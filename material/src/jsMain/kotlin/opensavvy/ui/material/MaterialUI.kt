package opensavvy.ui.material

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import opensavvy.ui.core.UI
import opensavvy.ui.core.theme.Theme
import opensavvy.ui.material.basic.MaterialButtons
import opensavvy.ui.material.basic.MaterialChips
import opensavvy.ui.material.basic.MaterialTextFields
import opensavvy.ui.material.basic.MaterialTexts
import opensavvy.ui.material.layout.MaterialLazyLayouts
import opensavvy.ui.material.layout.MaterialLinearLayouts
import opensavvy.ui.material.theme.MaterialDefaultTheme

actual interface MaterialUI : UI, MaterialButtons, MaterialLinearLayouts, MaterialLazyLayouts, MaterialTexts,
                              MaterialChips,
                              MaterialTextFields {
	@Composable
	override fun initializeFor(content: @Composable () -> Unit) {
		CompositionLocalProvider(Theme.Local providesDefault MaterialDefaultTheme) {
			content()
		}
	}
}
