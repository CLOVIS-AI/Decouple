package opensavvy.decouple.material

import opensavvy.decouple.core.UI
import opensavvy.decouple.core.UIMetadata
import opensavvy.decouple.core.basic.Buttons
import opensavvy.decouple.core.basic.Chips
import opensavvy.decouple.core.basic.TextFields
import opensavvy.decouple.core.basic.Texts
import opensavvy.decouple.core.layout.LazyLayouts
import opensavvy.decouple.core.layout.LinearLayouts
import opensavvy.decouple.material.basic.MaterialButtons
import opensavvy.decouple.material.basic.MaterialChips
import opensavvy.decouple.material.basic.MaterialTextFields
import opensavvy.decouple.material.basic.MaterialTexts
import opensavvy.decouple.material.layout.MaterialLazyLayouts
import opensavvy.decouple.material.layout.MaterialLinearLayouts

expect object MaterialUIMetadata : UIMetadata

object MaterialUI : UI,
                    UIMetadata by MaterialUIMetadata,
                    Buttons by MaterialButtons,
                    Chips by MaterialChips,
                    TextFields by MaterialTextFields,
                    Texts by MaterialTexts,
                    LinearLayouts by MaterialLinearLayouts,
                    LazyLayouts by MaterialLazyLayouts {

    override fun toString() = "Material You"
}
