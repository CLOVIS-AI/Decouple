package opensavvy.decouple.material

import opensavvy.decouple.core.UI
import opensavvy.decouple.core.UIMetadata
import opensavvy.decouple.core.basic.*
import opensavvy.decouple.core.layout.LazyLayouts
import opensavvy.decouple.core.layout.LinearLayouts
import opensavvy.decouple.core.layout.Navigation
import opensavvy.decouple.material.basic.*
import opensavvy.decouple.material.layout.MaterialLazyLayouts
import opensavvy.decouple.material.layout.MaterialLinearLayouts
import opensavvy.decouple.material.layout.MaterialNavigation

expect object MaterialUIMetadata : UIMetadata

object MaterialUI : UI,
                    UIMetadata by MaterialUIMetadata,
                    Buttons by MaterialButtons,
                    Chips by MaterialChips,
                    TextFields by MaterialTextFields,
                    Texts by MaterialTexts,
                    ProgressIndicators by MaterialProgressIndicators,
                    LinearLayouts by MaterialLinearLayouts,
                    LazyLayouts by MaterialLazyLayouts,
                    Navigation by MaterialNavigation {

    override fun toString() = "Material You"
}
