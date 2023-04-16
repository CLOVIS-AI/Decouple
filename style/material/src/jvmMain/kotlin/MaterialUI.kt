package opensavvy.decouple.material

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
import opensavvy.decouple.material.androidx.MAUIMetadata
import opensavvy.decouple.material.androidx.atom.MAProgressIndicators
import opensavvy.decouple.material.androidx.atom.actionable.MAButtons
import opensavvy.decouple.material.androidx.atom.actionable.MAChips
import opensavvy.decouple.material.androidx.atom.input.MATextFields
import opensavvy.decouple.material.androidx.atom.text.MATexts
import opensavvy.decouple.material.androidx.layout.MAFullscreenLayouts
import opensavvy.decouple.material.androidx.layout.MALazyLayouts
import opensavvy.decouple.material.androidx.layout.MALinearLayouts
import opensavvy.decouple.material.androidx.layout.MANavigation

actual object MaterialUI : UI,
    UIMetadata by MAUIMetadata,
    Buttons by MAButtons,
    Chips by MAChips,
    LinearLayouts by MALinearLayouts,
    LazyLayouts by MALazyLayouts,
    TextFields by MATextFields,
    Texts by MATexts,
    Navigation by MANavigation,
    FullscreenLayouts by MAFullscreenLayouts,
    ProgressIndicators by MAProgressIndicators {

    override fun toString() = "Material You"
}
