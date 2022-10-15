package opensavvy.ui.material.theme

import opensavvy.ui.core.theme.ColorTheme
import opensavvy.ui.core.theme.Theme

object MaterialDefaultTheme : Theme {
	override val color: ColorTheme
		get() = Palette.default.toLightTheme()
}
