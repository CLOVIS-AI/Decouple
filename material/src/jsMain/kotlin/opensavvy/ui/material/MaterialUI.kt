package opensavvy.ui.material

import opensavvy.ui.core.UI
import opensavvy.ui.core.theme.Theme
import opensavvy.ui.material.basic.MaterialButtons
import opensavvy.ui.material.basic.MaterialChips
import opensavvy.ui.material.basic.MaterialTextFields
import opensavvy.ui.material.basic.MaterialTexts
import opensavvy.ui.material.layout.MaterialLazyLayouts
import opensavvy.ui.material.layout.MaterialLinearLayouts
import opensavvy.ui.material.theme.MaterialTheme

actual interface MaterialUI : UI, MaterialButtons, MaterialLinearLayouts, MaterialLazyLayouts, MaterialTexts,
                              MaterialChips,
                              MaterialTextFields {

    override val recommendedThemes: List<Theme>
        get() = super.recommendedThemes + listOf(
            MaterialTheme(isLight = true),
            MaterialTheme(isLight = false),
        )

}
