package opensavvy.ui.material

import androidx.compose.runtime.Composable
import opensavvy.ui.core.UI
import opensavvy.ui.core.theme.Theme
import opensavvy.ui.material.basic.MaterialButtons
import opensavvy.ui.material.basic.MaterialChips
import opensavvy.ui.material.basic.MaterialTextFields
import opensavvy.ui.material.basic.MaterialTexts
import opensavvy.ui.material.layout.MaterialLazyLayouts
import opensavvy.ui.material.layout.MaterialLinearLayouts
import opensavvy.ui.material.theme.MaterialTheme
import opensavvy.ui.material.theme.css
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.dom.Div

actual interface MaterialUI : UI, MaterialButtons, MaterialLinearLayouts, MaterialLazyLayouts, MaterialTexts,
                              MaterialChips,
                              MaterialTextFields {

    @Composable
    override fun initializeThemeFor(theme: Theme, content: @Composable () -> Unit) {
        Div(
            {
                classes("transition-colors")

                style {
                    backgroundColor(theme.color.background.rgb.css)
                    color(theme.color.background.on.rgb.css)
                }
            }
        ) {
            super.initializeThemeFor(theme, content)
        }
    }

    override val recommendedThemes: List<Theme>
        get() = super.recommendedThemes + listOf(
            MaterialTheme(isLight = true),
            MaterialTheme(isLight = false),
        )

}
