package opensavvy.decouple.demo.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import opensavvy.decouple.core.atom.ProgressIndicator
import opensavvy.decouple.core.atom.actionable.AssistChip
import opensavvy.decouple.core.atom.actionable.ChipGroup
import opensavvy.decouple.core.atom.text.Text
import opensavvy.decouple.core.layout.Row
import opensavvy.decouple.core.layout.SupportedScreen
import opensavvy.decouple.core.progression.launch
import opensavvy.decouple.core.progression.rememberProgress
import opensavvy.progress.Progress
import opensavvy.progress.coroutines.report
import opensavvy.progress.loading

@Composable
fun Progression() {
	val scope = rememberCoroutineScope()

	var unquantified by rememberProgress()
	var quantified by rememberProgress()

	SupportedScreen(
		"Progression",
		supportTitle = "States",
		support = {
			ChipGroup {
				AssistChip(
					onClick = {
						scope.launch(onProgress = { unquantified = it }) {
							repeat(100) {
								delay(100)
								report(loading())
							}
						}
						scope.launch(onProgress = { quantified = it }) {
							repeat(100) {
								delay(100)
								report(loading(it / 100.0))
							}
						}
					},
					enabled = unquantified is Progress.Done && quantified is Progress.Done
				) {
					Text("Start the operation")
				}
			}
		}
	) {
		Text("Progression indicators notify the user about on-going work.")

		Row {
			Text("Unquantified:")
			ProgressIndicator(unquantified)
		}

		Row {
			Text("Quantified:")
			ProgressIndicator(quantified)
		}
	}
}
