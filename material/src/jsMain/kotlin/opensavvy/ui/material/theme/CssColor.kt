package opensavvy.ui.material.theme

import opensavvy.ui.core.theme.Color
import opensavvy.ui.core.theme.RGB
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.rgba

internal val Color.css: String
	get() = "${rgb.red} ${rgb.green} ${rgb.blue}" //TODO : Process alpha value and combine it with state layers opacity (https://gitlab.com/opensavvy/opensavvy-ui/-/issues/23)

internal val RGB.css: CSSColorValue
	get() = rgba(red.toFloat(), green.toFloat(), blue.toFloat(), alpha.toFloat())
