package opensavvy.ui.demo

import androidx.compose.runtime.Composable
import opensavvy.ui.core.UI

@Composable
fun Demo(implementations: List<UI>) {
	AppearanceSelector(implementations) {
		Navigator.render()
	}
}
