package opensavvy.decouple.material

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.UI
import opensavvy.decouple.core.theme.Theme
import opensavvy.decouple.material.theme.MaterialTheme
import opensavvy.decouple.material.theme.css
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.dom.Div

actual interface MaterialUI : UI, opensavvy.decouple.material.basic.MaterialButtons,
                              opensavvy.decouple.material.layout.MaterialLinearLayouts,
                              opensavvy.decouple.material.layout.MaterialLazyLayouts,
                              opensavvy.decouple.material.basic.MaterialTexts,
                              opensavvy.decouple.material.basic.MaterialChips,
                              opensavvy.decouple.material.basic.MaterialTextFields {

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
