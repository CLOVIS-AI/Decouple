package opensavvy.decouple.demo

import androidx.compose.runtime.*
import opensavvy.decouple.core.UI
import opensavvy.decouple.core.UI.Companion.Install
import opensavvy.decouple.core.atom.actionable.Button
import opensavvy.decouple.core.atom.actionable.SecondaryButton
import opensavvy.decouple.core.atom.text.Text
import opensavvy.decouple.core.layout.Column
import opensavvy.decouple.core.layout.Row
import opensavvy.decouple.core.theme.Theme.Companion.Install

@Composable
fun AppearanceSelector(implementations: List<UI>, content: @Composable () -> Unit) {
	val themes = remember(implementations) { implementations.flatMap { it.recommendedThemes } }

	var currentUi by remember { mutableStateOf(implementations.first()) }
	var currentTheme by remember {
		mutableStateOf(
			currentUi.recommendedThemes.firstOrNull() ?: themes.first()
		)
	}

	Install(currentUi) {
		Install(currentTheme) {
			Column {
				Row {
					Text("UI implementation :")
					for (ui in implementations) {
						Button({ currentUi = ui }, enabled = ui != currentUi) {
							Text(ui.toString())
						}
					}
				}
				Row {
					Text("Themes :")
					for (theme in themes) {
						if (theme in currentUi.recommendedThemes)
							SecondaryButton(
								{ currentTheme = theme },
								enabled = theme != currentTheme
							) {
								Text(theme.toString())
							}
						else
							Button({ currentTheme = theme }, enabled = theme != currentTheme) {
								Text(theme.toString())
							}
					}
				}

				content()
			}
		}
	}
}
