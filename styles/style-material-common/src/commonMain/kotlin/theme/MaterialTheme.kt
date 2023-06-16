package opensavvy.decouple.material.common.theme

import opensavvy.decouple.core.theme.Theme

@Suppress("unused", "MemberVisibilityCanBePrivate")
data class MaterialTheme(
	val palette: Palette = Palette.default,
	val isLight: Boolean = true,
) : Theme {

	override val name: String by lazy {
		buildString {
			append(palette.name)

			if (isLight)
				append(" Light")
			else
				append(" Dark")
		}
	}

	override val color = if (isLight) palette.toLightTheme() else palette.toDarkTheme()

	override fun toString() = name

	companion object {
		val default get() = listOf(MaterialTheme(isLight = true), MaterialTheme(isLight = false))
	}
}
