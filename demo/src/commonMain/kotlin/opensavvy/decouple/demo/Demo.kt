package opensavvy.decouple.demo

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.UI
import opensavvy.decouple.core.layout.NavigationMenu.Companion.navigationMenu
import opensavvy.decouple.core.layout.NavigationMenu.Menu
import opensavvy.decouple.core.layout.NavigationMenu.Page
import opensavvy.decouple.navigation.GlobalNavigation
import opensavvy.decouple.navigation.Navigation
import opensavvy.decouple.navigation.Page

internal lateinit var Navigator: Navigation
	private set

@Composable
fun Demo(implementations: List<UI>, navigator: Navigation) {
	val menu = navigationMenu(
		Page(Screen.Home),
		Page(Screen.GettingStarted),
		Menu(
			"Components",
			Page("Overview", Screen.Components),
			Page(Screen.Buttons),
			Page(Screen.Chips),
			Page(Screen.TextFields),
			Page(Screen.Progression),
		),
	)

	Navigator = navigator

	AppearanceSelector(implementations) {
		GlobalNavigation(navigator, menu)
	}
}
