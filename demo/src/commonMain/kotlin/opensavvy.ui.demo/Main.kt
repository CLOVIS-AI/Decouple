package opensavvy.ui.demo

import androidx.compose.runtime.Composable
import kotlinx.coroutines.delay
import opensavvy.ui.core.basic.Button
import opensavvy.ui.core.basic.Text

expect fun start(app: @Composable () -> Unit)

fun main() {
	start {
		Button(onClick = {
			delay(1000)
			println("Clicked")
		}) { Text("Button") }
	}
}
