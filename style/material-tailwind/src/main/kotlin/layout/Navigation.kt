package opensavvy.decouple.material.tailwind.layout

import androidx.compose.runtime.*
import opensavvy.decouple.core.atom.actionable.Button
import opensavvy.decouple.core.atom.text.Text
import opensavvy.decouple.core.layout.Navigation
import opensavvy.decouple.core.navigation.NavigationMenu
import opensavvy.decouple.core.theme.Theme
import opensavvy.decouple.material.tailwind.theme.css
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.dom.Div

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

		val backgroundColor = Theme.color.backgroundVariant

		Div(
			{
				onMouseLeave { open = null }
				classes("h-full", "flex", "flex-row", "px-2", "transition-colors")

				style {
					backgroundColor(backgroundColor.rgb.css)
					color(backgroundColor.on.rgb.css)
				}
			}
		) {
			// Always-open panel
			Rail(menu, onOpen = { open = it }, selected, onSelect)

			// Sub-panel
			open?.let {
				Div(
					{
						classes("relative")
					}
				) {
					Submenu(it, selected, onSelect)
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
				classes("h-full", "w-full", "flex", "flex-col", "flex-grow", "justify-center", "items-stretch", "gap-4")
			}
		) {
			for (child in menu.children) key(child) {
				Div(
					{
						classes("w-full", "flex", "flex-row", "justify-center")

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
	private fun <P> Submenu(
		open: NavigationMenu.Menu<P>,
		selected: NavigationMenu<P>,
		onSelect: (NavigationMenu.Page<P>) -> Unit,
		firstLevel: Boolean = true,
	) {
		val backgroundColor = Theme.color.backgroundVariant

		var openedChildMenu by remember(selected) { mutableStateOf<NavigationMenu.Menu<P>?>(null) }

		Div(
			{
				classes("flex", "flex-col", "justify-center", "items-start", "gap-4", "px-2")

				if (firstLevel) {
					classes("h-full", "absolute", "left-0")
				}

				style {
					backgroundColor(backgroundColor.rgb.css)
					color(backgroundColor.on.rgb.css)
				}
			}
		) {
			for (child in open.children) key(child) {
				Button(
					onClick = {
						when (child) {
							is NavigationMenu.Menu -> {
								openedChildMenu = child
							}

							is NavigationMenu.Page -> {
								onSelect(child)
							}
						}
					},
					enabled = selected != child,
				) { Text(child.title) }

				if (openedChildMenu == child) {
					Submenu(
						open = openedChildMenu!!, // we just checked that it's the current child
						selected,
						onSelect,
						firstLevel = false,
					)
				}
			}
		}
	}
}
