package opensavvy.decouple.demo

import androidx.compose.runtime.*
import opensavvy.decouple.core.UI
import opensavvy.decouple.core.UI.Companion.Install
import opensavvy.decouple.core.layout.NavigationMenu.Companion.navigationMenu
import opensavvy.decouple.core.layout.NavigationMenu.Menu
import opensavvy.decouple.core.layout.NavigationMenu.Page
import opensavvy.decouple.core.theme.Theme.Companion.Install
import opensavvy.decouple.demo.theming.StyleSelector
import opensavvy.decouple.demo.theming.ThemeSelector
import opensavvy.decouple.navigation.GlobalNavigation
import opensavvy.decouple.navigation.Navigation
import opensavvy.decouple.navigation.Page

internal lateinit var Navigator: Navigation
	private set

@Composable
fun Demo(implementations: List<UI>, navigator: Navigation) {
	val themes = remember(implementations) { implementations.flatMap { it.recommendedThemes } }

	var currentUI by remember { mutableStateOf(implementations.first()) }
	var currentTheme by remember { mutableStateOf(currentUI.recommendedThemes.firstOrNull() ?: themes.first()) }

	val menu = navigationMenu(
		Page(Screen.Home),
		Page(Screen.GettingStarted),
		Menu(
			"Design",
			Page("Overview", Screen.Design),
			Page("Styles", StyleSelector(implementations, currentUI, setCurrent = { currentUI = it })),
			Page(
				"Themes",
				ThemeSelector(themes, currentUI.recommendedThemes, currentTheme, setCurrent = { currentTheme = it })
			),
		),
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

	Install(currentUI) {
		Install(currentTheme) {
			GlobalNavigation(navigator, menu)
		}
	}
}
