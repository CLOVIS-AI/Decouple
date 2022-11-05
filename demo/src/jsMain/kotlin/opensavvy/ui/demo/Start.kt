package opensavvy.ui.demo

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.renderComposable

actual fun start(app: @Composable () -> Unit) {
	renderComposable(rootElementId = "root") {
		app()
	}
}
