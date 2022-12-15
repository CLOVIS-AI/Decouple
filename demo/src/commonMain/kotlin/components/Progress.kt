package opensavvy.decouple.demo.components

import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import opensavvy.decouple.core.atom.ProgressIndicator
import opensavvy.decouple.core.atom.actionable.AssistChip
import opensavvy.decouple.core.atom.actionable.ChipGroup
import opensavvy.decouple.core.atom.text.Text
import opensavvy.decouple.core.layout.Column
import opensavvy.decouple.core.layout.Row
import opensavvy.decouple.core.progression.launch
import opensavvy.state.Progression
import opensavvy.state.ProgressionReporter.Companion.report

@Composable
fun Progression() = Column {
	Text("Progression indicators notify the user about on-going work.")

	val scope = rememberCoroutineScope()

	var unquantified: Progression by remember { mutableStateOf(Progression.Done) }
	var quantified: Progression by remember { mutableStateOf(Progression.Done) }

	Row {
		Text("Unquantified:")
		ProgressIndicator(unquantified)
	}

	Row {
		Text("Quantified:")
		ProgressIndicator(quantified)
	}

	Text("States:")
	ChipGroup {
		AssistChip(
			onClick = {
				scope.launch(onProgress = { unquantified = it }) {
					repeat(100) {
						delay(100)
						report(Progression.loading())
					}
				}
				scope.launch(onProgress = { quantified = it }) {
					repeat(100) {
						delay(100)
						report(Progression.loading(it / 100.0))
					}
				}
			},
			enabled = unquantified is Progression.Done && quantified is Progression.Done
		) {
			Text("Start the operation")
		}
	}
}
