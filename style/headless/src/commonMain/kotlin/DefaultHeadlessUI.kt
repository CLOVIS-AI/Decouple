package opensavvy.decouple.headless

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
import opensavvy.decouple.headless.atom.TProgressIndicators
import opensavvy.decouple.headless.atom.actionable.TButtons
import opensavvy.decouple.headless.atom.actionable.TChips
import opensavvy.decouple.headless.atom.input.TTextFields
import opensavvy.decouple.headless.atom.text.TTexts
import opensavvy.decouple.headless.layout.TFullscreenLayouts
import opensavvy.decouple.headless.layout.TLazyLayouts
import opensavvy.decouple.headless.layout.TLinearLayouts
import opensavvy.decouple.headless.layout.TNavigation

object DefaultHeadlessUI : UI,
    UIMetadata by HeadlessUIMetadata,
    Buttons by TButtons,
    Chips by TChips,
    TextFields by TTextFields,
    Texts by TTexts,
    ProgressIndicators by TProgressIndicators,
    LinearLayouts by TLinearLayouts,
    LazyLayouts by TLazyLayouts,
    FullscreenLayouts by TFullscreenLayouts,
    Navigation by TNavigation

object HeadlessUIMetadata : UIMetadata {
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
