package opensavvy.decouple.demo

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.UI
import opensavvy.decouple.navigation.Navigation

internal lateinit var Navigator: Navigation
	private set

@Composable
fun Demo(implementations: List<UI>, navigator: Navigation) {
	Navigator = navigator
	AppearanceSelector(implementations) {
		navigator.render()
	}
}
