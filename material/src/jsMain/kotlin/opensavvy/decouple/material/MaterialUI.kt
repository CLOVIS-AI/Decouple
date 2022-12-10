package opensavvy.decouple.material

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.UIMetadata
import opensavvy.decouple.core.theme.Theme
import opensavvy.decouple.material.theme.MaterialTheme
import opensavvy.decouple.material.theme.css
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.dom.Div

actual object MaterialUIMetadata : UIMetadata {
	@Composable
	override fun initializeFor(content: @Composable () -> Unit) {
		content()
	}

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
			content()
		}
	}

	override val recommendedThemes: List<Theme>
		get() = listOf(
			MaterialTheme(isLight = true),
			MaterialTheme(isLight = false),
		)
}
