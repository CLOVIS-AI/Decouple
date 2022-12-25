package opensavvy.decouple.material.androidx.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import opensavvy.decouple.core.atom.actionable.SecondaryButton
import opensavvy.decouple.core.layout.Navigation
import opensavvy.decouple.core.layout.NavigationMenu

object MANavigation : Navigation {

	//TODO in #86: automatically select between the different kinds of navigation menus
	//TODO in #88: handle submenus

	@Composable
	override fun <P> GlobalNavigation(
		menu: NavigationMenu.Menu<P>,
		selected: NavigationMenu<P>,
		onSelect: (NavigationMenu.Page<P>) -> Unit,
		currentContents: @Composable () -> Unit,
	) = Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
		currentContents()

		var options by remember { mutableStateOf(emptyList<NavigationMenu<P>>()) }

		Column {
			for (option in options) {
				SecondaryButton(onClick = {
					when (option) {
						is NavigationMenu.Page -> {
							onSelect(option)
							options = emptyList()
						}

						is NavigationMenu.Menu -> options = option.children
					}
				}) {
					Text(option.title)
				}
			}

			NavigationBar {
				for (child in menu.children) {
					NavigationBarItem(
						icon = { Text(child.title.first().uppercaseChar().toString()) },
						label = { Text(child.title) },
						selected = selected == child,
						onClick = {
							when (child) {
								is NavigationMenu.Page -> {
									onSelect(child)
									options = emptyList()
								}

								is NavigationMenu.Menu -> options = child.children
							}
						},
					)
				}
			}
		}
	}

}
