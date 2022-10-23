package opensavvy.ui.material.theme

import opensavvy.ui.core.theme.Color

internal val Color.css: String
	get() = "${rgb.red} ${rgb.green} ${rgb.blue}" //TODO : Process alpha value and combine it with state layers opacity (https://gitlab.com/opensavvy/opensavvy-ui/-/issues/23)
