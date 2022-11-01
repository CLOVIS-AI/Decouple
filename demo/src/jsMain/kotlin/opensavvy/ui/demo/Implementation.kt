package opensavvy.ui.demo

import opensavvy.ui.core.UI
import opensavvy.ui.material.MaterialUI

object MaterialUI : MaterialUI {
	override fun toString() = "Material You"
}

actual fun uiImplementations(): List<UI> = listOf(
	MaterialUI
)
