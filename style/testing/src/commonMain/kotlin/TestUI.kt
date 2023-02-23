package opensavvy.decouple.testing

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.UI
import opensavvy.decouple.core.UIMetadata
import opensavvy.decouple.core.atom.ProgressIndicators
import opensavvy.decouple.core.atom.actionable.Buttons
import opensavvy.decouple.core.atom.actionable.Chips
import opensavvy.decouple.core.atom.input.TextFields
import opensavvy.decouple.core.atom.text.Texts
import opensavvy.decouple.core.layout.FullscreenLayouts
import opensavvy.decouple.core.layout.LazyLayouts
import opensavvy.decouple.core.layout.LinearLayouts
import opensavvy.decouple.core.layout.Navigation
import opensavvy.decouple.core.theme.Theme
import opensavvy.decouple.testing.atom.TProgressIndicators
import opensavvy.decouple.testing.atom.actionable.TButtons
import opensavvy.decouple.testing.atom.actionable.TChips
import opensavvy.decouple.testing.atom.input.TTextFields
import opensavvy.decouple.testing.atom.text.TTexts
import opensavvy.decouple.testing.layout.TFullscreenLayouts
import opensavvy.decouple.testing.layout.TLazyLayouts
import opensavvy.decouple.testing.layout.TLinearLayouts
import opensavvy.decouple.testing.layout.TNavigation

object TestUI : UI,
                UIMetadata by TestUIMetadata,
                Buttons by TButtons,
                Chips by TChips,
                TextFields by TTextFields,
                Texts by TTexts,
                ProgressIndicators by TProgressIndicators,
                LinearLayouts by TLinearLayouts,
                LazyLayouts by TLazyLayouts,
                FullscreenLayouts by TFullscreenLayouts,
                Navigation by TNavigation

object TestUIMetadata : UIMetadata {
	override val name: String = "Test UI"

	override val recommendedThemes: List<Theme> = emptyList()

	@Composable
	override fun initializeFor(content: @Composable () -> Unit) {
		content()
	}

	@Composable
	override fun initializeThemeFor(theme: Theme, content: @Composable () -> Unit) {
		content()
	}

}
