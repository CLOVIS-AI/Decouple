package opensavvy.ui.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import opensavvy.ui.core.UI
import opensavvy.ui.core.UI.Companion.Install
import opensavvy.ui.core.basic.Button
import opensavvy.ui.core.basic.SecondaryButton
import opensavvy.ui.core.basic.Text
import opensavvy.ui.core.layout.Column
import opensavvy.ui.core.layout.Row
import opensavvy.ui.core.theme.Theme.Companion.Install

expect fun uiImplementations(): List<UI>

fun themes() = sequence {
	for (ui in uiImplementations())
		yieldAll(ui.recommendedThemes)
}

private var globalUi by mutableStateOf(uiImplementations().first())
private var globalTheme by mutableStateOf(globalUi.recommendedThemes.firstOrNull() ?: themes().first())

@Composable
fun Install(app: @Composable () -> Unit) {
	Install(globalUi) {
		Install(globalTheme) {
			Column {
				Row {
					Text("UI implementation :")
					for (ui in uiImplementations()) {
						Button({ globalUi = ui }, enabled = ui != globalUi) {
							Text(ui.toString())
						}
					}
				}
				Row {
					Text("Themes :")
					for (theme in themes()) {
						if (theme in globalUi.recommendedThemes)
							SecondaryButton({ globalTheme = theme }, enabled = theme != globalTheme) {
								Text(theme.toString())
							}
						else
							Button({ globalTheme = theme }, enabled = theme != globalTheme) {
								Text(theme.toString())
							}
					}
				}
				app()
			}
		}
	}
}
