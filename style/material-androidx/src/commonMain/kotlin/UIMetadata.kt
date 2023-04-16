package opensavvy.decouple.material.androidx

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.UIMetadata
import opensavvy.decouple.core.theme.Theme
import opensavvy.decouple.material.common.theme.MaterialTheme
import androidx.compose.material3.MaterialTheme as M3MaterialTheme

@Suppress("ComposableNaming")
object MAUIMetadata : UIMetadata {
	override val name: String = "Material You"

	override val recommendedThemes: List<Theme>
		get() = MaterialTheme.default

	@Composable
	override fun initializeFor(
		content: @Composable () -> Unit,
	) = content()

	@Composable
	override fun initializeThemeFor(
		theme: Theme,
		content: @Composable () -> Unit,
	) {
		//TODO in #85: respect the theme requested by the user
		M3MaterialTheme(content = content)
	}

	override fun toString() = name

}
