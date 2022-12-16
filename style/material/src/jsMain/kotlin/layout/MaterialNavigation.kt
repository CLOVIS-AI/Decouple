package opensavvy.decouple.material.layout

import androidx.compose.runtime.*
import opensavvy.decouple.core.atom.actionable.Button
import opensavvy.decouple.core.atom.text.Text
import opensavvy.decouple.core.layout.*
import org.jetbrains.compose.web.dom.Div

//TODO in #54:
// - Better visual appearance
// - Accept any menu depth (currently the maximum is 2)

actual object MaterialNavigation : Navigation {

	@Composable
	override fun <P> GlobalNavigation(
		menu: NavigationMenu.Menu<P>,
		selected: NavigationMenu<P>,
		onSelect: (NavigationMenu.Page<P>) -> Unit,
		currentContents: @Composable () -> Unit,
	) = Row(Arrangement.Start) {
		var open by remember { mutableStateOf<NavigationMenu.Menu<P>?>(null) }

		Div(
			{
				onMouseLeave { open = null }
			}
		) {
			Row {
				// Always-open panel
				Column {
					for (child in menu.children) {
						Div(
							{
								onMouseEnter {
									open =
										if (child is NavigationMenu.Menu) child
										else null
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

				// Sub-panel
				Column {
					val open = open
					if (open != null) {
						for (child in open.children) {
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
		}

		currentContents()
	}
}
