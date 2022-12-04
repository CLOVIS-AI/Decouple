package opensavvy.ui.demo

import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import opensavvy.ui.core.basic.*
import opensavvy.ui.core.layout.Column

@Composable
fun Chips() = Column {
	Text("Chips represent additional actions.")

	var enabled by remember { mutableStateOf(true) }
	var contrasted by remember { mutableStateOf(false) }

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
		FilterChip(enabled, onToggle = { enabled = !enabled }) {
			Text("Enabled")
		}

		FilterChip(contrasted, onToggle = { contrasted = !contrasted }) {
			Text("Contrasted")
		}
	}
}
