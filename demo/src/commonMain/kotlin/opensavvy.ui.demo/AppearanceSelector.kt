package opensavvy.ui.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import opensavvy.ui.core.UI
import opensavvy.ui.core.UI.Companion.Install
import opensavvy.ui.core.basic.Button
import opensavvy.ui.core.basic.SecondaryButton
import opensavvy.ui.core.basic.Text
import opensavvy.ui.core.layout.Column
import opensavvy.ui.core.layout.Row
import opensavvy.ui.core.theme.Theme.Companion.Install

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
