package opensavvy.ui.demo

import androidx.compose.runtime.Composable
import opensavvy.ui.core.UI
import opensavvy.ui.navigation.Navigation

internal lateinit var Navigator: Navigation
	private set

@Composable
fun Demo(implementations: List<UI>, navigator: Navigation) {
	Navigator = navigator
	AppearanceSelector(implementations) {
		navigator.render()
	}
}
