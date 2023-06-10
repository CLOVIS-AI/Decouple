package opensavvy.decouple.demo

import androidx.compose.runtime.*
import opensavvy.decouple.core.UI
import opensavvy.decouple.core.UI.Companion.Install
import opensavvy.decouple.core.navigation.navigationMenu
import opensavvy.decouple.core.theme.Theme.Companion.Install
import opensavvy.decouple.demo.theming.StyleSelector
import opensavvy.decouple.demo.theming.ThemeSelector
import opensavvy.decouple.navigation.GlobalNavigation
import opensavvy.decouple.navigation.Navigation
import opensavvy.decouple.navigation.page

internal lateinit var Navigator: Navigation
	private set

@Composable
fun Demo(implementations: List<UI>, navigator: Navigation) {
	val themes = remember(implementations) { implementations.flatMap { it.recommendedThemes } }

	var currentUI by remember { mutableStateOf(implementations.first()) }
	var currentTheme by remember { mutableStateOf(currentUI.recommendedThemes.firstOrNull() ?: themes.first()) }

	val menu = navigationMenu {
		page(Screen.Home)
		page(Screen.GettingStarted)
		menu("Design") {
			page("Overview", Screen.Design)
			page("style", "Styling", parent = Screen.Design) {
				StyleSelector(implementations, currentUI, setCurrent = { currentUI = it })
			}
			page("theme", "Theme", parent = Screen.Design) {
				ThemeSelector(themes, currentUI.recommendedThemes, currentTheme, setCurrent = { currentTheme = it })
			}
		}
		menu("Components") {
			page("Overview", Screen.Components)
			page(Screen.Buttons)
			page(Screen.Chips)
			page(Screen.TextFields)
			page(Screen.Progression)
			page(Screen.LazyLayouts)
		}
		menu("Demos") {
			page("Overview", Screen.Demos)
			page(Screen.Calculator)
		}
	}

	Navigator = navigator

	Install(currentUI) {
		Install(currentTheme) {
			GlobalNavigation(navigator, menu)
		}
	}
}
