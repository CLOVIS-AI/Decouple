package opensavvy.decouple.material.tailwind.layout

import androidx.compose.runtime.*
import opensavvy.decouple.core.atom.actionable.Button
import opensavvy.decouple.core.atom.text.Text
import opensavvy.decouple.core.layout.Navigation
import opensavvy.decouple.core.navigation.NavigationMenu
import org.jetbrains.compose.web.dom.Div

//TODO in #54:
// - Better visual appearance
// - Accept any menu depth (currently the maximum is 2)

object MTNavigation : Navigation {

	@Composable
	override fun <P> GlobalNavigation(
		menu: NavigationMenu.Menu<P>,
		selected: NavigationMenu<P>,
		onSelect: (NavigationMenu.Page<P>) -> Unit,
		currentContents: @Composable () -> Unit,
	) = Div(
		{
			classes("flex", "flex-row", "gap-2", "justify-start", "items-stretch", "h-full", "w-full")
		}
	) {
		var open by remember { mutableStateOf<NavigationMenu.Menu<P>?>(null) }

		Div(
			{
				onMouseLeave { open = null }
				classes("h-full")
			}
		) {
			Div(
				{
					classes("h-full", "flex", "flex-row")
				}
			) {
				// Always-open panel
				Rail(menu, onOpen = { open = it }, selected, onSelect)

				// Sub-panel
				open?.let {
					Submenu1(it, selected, onSelect)
				}
			}
		}

		Div(
			{
				classes("overflow-auto", "grow")
			}
		) {
			currentContents()
		}
	}

	@Composable
	private fun <P> Rail(
		menu: NavigationMenu.Menu<P>,
		onOpen: (NavigationMenu.Menu<P>?) -> Unit,
		selected: NavigationMenu<P>,
		onSelect: (NavigationMenu.Page<P>) -> Unit,
	) {
		Div(
			{
				classes("h-full", "flex", "flex-col", "justify-center", "items-stretch", "gap-4")
			}
		) {
			for (child in menu.children) key(child) {
				Div(
					{
						onMouseEnter {
							onOpen(
								if (child is NavigationMenu.Menu) child
								else null
							)
						}
					}
				) {
					Button(
						onClick = {
							child.firstPage()?.also(onSelect)
						},
						enabled = selected != child,
					) { Text(child.title) }
				}
			}
		}
	}

	@Composable
	private fun <P> Submenu1(
		open: NavigationMenu.Menu<P>,
		selected: NavigationMenu<P>,
		onSelect: (NavigationMenu.Page<P>) -> Unit,
	) {
		Div(
			{
				classes("h-full", "flex", "flex-col", "justify-center", "items-stretch", "gap-4")
			}
		) {
			for (child in open.children) key(child) {
				Button(
					onClick = {
						child.firstPage()?.also(onSelect)
					},
					enabled = selected != child,
				) { Text(child.title) }
			}
		}
	}
}
