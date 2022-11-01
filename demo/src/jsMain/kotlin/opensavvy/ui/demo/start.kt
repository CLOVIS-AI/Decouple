package opensavvy.ui.demo

import androidx.compose.runtime.Composable
import opensavvy.ui.core.UI.Companion.Install
import opensavvy.ui.core.theme.Theme.Companion.Install
import opensavvy.ui.material.MaterialUI
import opensavvy.ui.material.theme.MaterialDefaultTheme
import org.jetbrains.compose.web.renderComposable

object MaterialUI : MaterialUI

actual fun start(app: @Composable () -> Unit) {
	renderComposable(rootElementId = "root") {
		val ui = MaterialUI

		Install(ui) {
			Install(MaterialDefaultTheme) {
				app()
			}
		}
	}
}
