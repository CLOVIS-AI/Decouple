package opensavvy.decouple.material.tailwind

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.UIMetadata
import opensavvy.decouple.core.theme.Theme
import opensavvy.decouple.material.common.theme.MaterialTheme
import opensavvy.decouple.material.tailwind.theme.css
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.dom.Div

object MTUIMetadata : UIMetadata {
	override val recommendedThemes: List<Theme>
		get() = MaterialTheme.default

	@Composable
	override fun initializeFor(content: @Composable () -> Unit) = content()

	@Composable
	override fun initializeThemeFor(theme: Theme, content: @Composable () -> Unit) {
		Div(
			{
				classes("transition-colors", "h-full")

				style {
					backgroundColor(theme.color.background.rgb.css)
					color(theme.color.background.on.rgb.css)
				}
			}
		) {
			content()
		}
	}

	override fun toString() = "Material You"
}
