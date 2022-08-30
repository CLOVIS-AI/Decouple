package opensavvy.ui.demo

import androidx.compose.runtime.Composable
import opensavvy.ui.material.MaterialUI
import org.jetbrains.compose.web.renderComposable

object MaterialUI : MaterialUI

actual fun start(app: @Composable () -> Unit) {
	renderComposable(rootElementId = "root") {
		val ui = MaterialUI

		ui.Install {
			app()
		}
	}
}
