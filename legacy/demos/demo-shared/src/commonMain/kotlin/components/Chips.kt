package opensavvy.decouple.demo.components

import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import opensavvy.decouple.core.atom.*
import opensavvy.decouple.core.layout.SupportedScreen
import opensavvy.decouple.persist.persistentStateOf

@Composable
fun Chips() {
	var enabled by remember { persistentStateOf("chips.enabled") { true } }
	var contrasted by remember { persistentStateOf("chips.contrasted") { false } }

	SupportedScreen(
		"Chips",
		supportTitle = "States",
		support = {
			ChipGroup {
				FilterChip(enabled, onToggle = { enabled = it }) {
					Text("Enabled")
				}

				FilterChip(contrasted, onToggle = { contrasted = it }) {
					Text("Contrasted")
				}
			}
		}
	) {
		Text("Chips represent additional actions.")

		Text("There are multiple kinds of chips:")
		ChipGroup {
			AssistChip({ delay(1000) }, enabled = enabled, contrasted = contrasted) {
				Text("Assist chip")
			}

			var filterActivated by remember { mutableStateOf(false) }
			FilterChip(
				filterActivated,
				{ delay(1000); filterActivated = !filterActivated },
				enabled = enabled,
				contrasted = contrasted
			) {
				Text("Filter chip")
			}

			InputChip({ delay(1000) }, enabled = enabled, contrasted = contrasted) {
				Text("Input chip")
			}

			SuggestionChip({ delay(1000) }, enabled = enabled, contrasted = contrasted) {
				Text("Suggestion chip")
			}
		}
	}
}
