package opensavvy.ui.material.theme

import opensavvy.ui.core.theme.Color
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.rgba

val Color.css: CSSColorValue
	get() = rgba(rgb.red.toInt(), rgb.green.toInt(), rgb.blue.toInt(), rgb.alpha.toInt().toDouble() / 255)
