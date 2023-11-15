package opensavvy.decouple.material

import opensavvy.decouple.core.UI
import opensavvy.decouple.core.UIMetadata
import opensavvy.decouple.core.atom.*
import opensavvy.decouple.core.layout.FullscreenLayouts
import opensavvy.decouple.core.layout.LazyLayouts
import opensavvy.decouple.core.layout.LinearLayouts
import opensavvy.decouple.core.layout.Navigation
import opensavvy.decouple.material.tailwind.MTUIMetadata
import opensavvy.decouple.material.tailwind.atom.*
import opensavvy.decouple.material.tailwind.layout.MTFullscreenLayouts
import opensavvy.decouple.material.tailwind.layout.MTLazyLayouts
import opensavvy.decouple.material.tailwind.layout.MTLinearLayouts
import opensavvy.decouple.material.tailwind.layout.MTNavigation

actual object MaterialUI : UI,
                           UIMetadata by MTUIMetadata,
                           Buttons by MTButtons,
                           Chips by MTChips,
                           LinearLayouts by MTLinearLayouts,
                           LazyLayouts by MTLazyLayouts,
                           TextFields by MTTextFields,
                           Texts by MTTexts,
                           Navigation by MTNavigation,
                           FullscreenLayouts by MTFullscreenLayouts,
                           ProgressIndicators by MTProgressIndicators {

    override fun toString() = "Material You (Tailwind)"
}
