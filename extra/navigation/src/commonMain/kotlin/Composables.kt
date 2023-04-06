package opensavvy.decouple.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import opensavvy.decouple.core.navigation.NavigationMenu

@Composable
fun GlobalNavigation(
	navigator: Navigation,
	menu: NavigationMenu.Menu<Destination>,
) {
	val selected: NavigationMenu.Page<Destination> = remember(navigator.current) {
		val current = navigator.current
		menu
			.asPrefixSequence()
			.filterIsInstance<NavigationMenu.Page<Destination>>()
			.find { it.payload == current }
			?: menu.firstPage()
			?: error("The passed menu contains no pages, it's not possible to display it! $menu")
	}

	opensavvy.decouple.core.layout.GlobalNavigation(
		menu,
		selected,
		onSelect = {
			navigator.lateral(it.payload)
		},
		currentContents = { selected.payload.render() }
	)
}

fun Page(dest: Destination) = NavigationMenu.Page(dest.title, dest)
