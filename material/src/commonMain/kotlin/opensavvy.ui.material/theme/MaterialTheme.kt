package opensavvy.ui.material.theme

import opensavvy.ui.core.theme.Theme

@Suppress("unused", "MemberVisibilityCanBePrivate")
data class MaterialTheme(
	val palette: Palette = Palette.default,
	val isLight: Boolean = true,
) : Theme {

	override val color = if (isLight) palette.toLightTheme() else palette.toDarkTheme()

	override fun toString() = buildString {
		append(palette.name)
		append(' ')

		if (isLight)
			append("(light)")
		else
			append("(dark)")
	}
}
