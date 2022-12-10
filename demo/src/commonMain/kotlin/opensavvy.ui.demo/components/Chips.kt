package opensavvy.ui.demo.components

import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import opensavvy.ui.core.basic.*
import opensavvy.ui.core.layout.Column
import opensavvy.ui.utils.persist

@Composable
fun Chips() = Column {
	Text("Chips represent additional actions.")

	var enabled by persist("chips.enabled") { true }
	var contrasted by persist("chips.contrasted") { false }

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

	Text("States:")
	ChipGroup {
		FilterChip(enabled, onToggle = { enabled = it }) {
			Text("Enabled")
		}

		FilterChip(contrasted, onToggle = { contrasted = it }) {
			Text("Contrasted")
		}
	}
}
