package opensavvy.decouple.core.layout

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.UI
import opensavvy.decouple.core.navigation.NavigationMenu

interface Navigation {

	/**
	 * Global navigation element.
	 *
	 * @param currentContents Visual appearance of the [selected] menu item.
	 */
	@Composable
	fun <P> GlobalNavigationSpec(
		menu: NavigationMenu.Menu<P>,
		selected: NavigationMenu<P>,
		onSelect: (NavigationMenu.Page<P>) -> Unit,
		currentContents: @Composable () -> Unit,
	)

}

@Composable
fun <P> GlobalNavigation(
	menu: NavigationMenu.Menu<P>,
	selected: NavigationMenu<P>,
	onSelect: (NavigationMenu.Page<P>) -> Unit,
	currentContents: @Composable () -> Unit,
) {
	UI.current.GlobalNavigationSpec(menu, selected, onSelect, currentContents)
}
