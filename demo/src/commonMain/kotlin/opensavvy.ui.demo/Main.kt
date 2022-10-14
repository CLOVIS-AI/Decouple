package opensavvy.ui.demo

import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import opensavvy.ui.core.basic.*
import opensavvy.ui.core.layout.Column
import opensavvy.ui.core.layout.Row
import opensavvy.ui.core.progression.ReportProgression
import opensavvy.ui.core.progression.loading
import kotlin.random.Random

expect fun start(app: @Composable () -> Unit)

suspend fun ReportProgression.action() {
	// Loading for 5 seconds
	for (i in 0..100) {
		loading(i)
		delay(50)
	}
	println("Clicked")
}

fun main() {
	start {
		Column {
			var enabled by remember { mutableStateOf(true) }
			ChipGroup {
				FilterChip(enabled, onToggle = { action(); enabled = !enabled }) {
					Text("Enabled")
				}
			}

			Row {
				Text("Common Buttons :")

				Button(onClick = { action() }, enabled = enabled) {
					Text("Button")
				}

				PrimaryButton(onClick = { action() }, primary = true, enabled = enabled) {
					Text("PrimaryButton highlighted")
				}

				PrimaryButton(onClick = { action() }, enabled = enabled) {
					Text("PrimaryButton")
				}

				SecondaryButton(onClick = { action() }, enabled = enabled) {
					Text("SecondaryButton")
				}

				ContrastButton(onClick = { action() }, enabled = enabled) {
					Text("ContrastButton")
				}
			}

			Row {
				Text("Regular chips :")

				ChipGroup {
					AssistChip(onClick = { action() }, enabled = enabled) {
						Text("AssistChip")
					}

					var active by remember { mutableStateOf(false) }
					FilterChip(active, onToggle = { action(); active = !active }, enabled = enabled) {
						Text("FilterChip")
					}

					val numbers = remember { mutableStateListOf<Int>() }
					for (number in numbers)
						InputChip(onRemoval = { action(); numbers -= number }, enabled = enabled) {
							Text("InputChip $number")
						}

					SuggestionChip(onClick = { action(); numbers += Random.nextInt() }, enabled = enabled) {
						Text("SuggestionChip")
					}
				}
			}

			Row {
				Text("Contrast chips :")

				ChipGroup {
					AssistChip(onClick = { action() }, contrasted = true, enabled = enabled) {
						Text("AssistChip")
					}

					var active by remember { mutableStateOf(false) }
					FilterChip(
						active,
						onToggle = { action(); active = !active },
						contrasted = true,
						enabled = enabled
					) {
						Text("FilterChip")
					}

					val numbers = remember { mutableStateListOf<Int>() }
					for (number in numbers)
						InputChip(onRemoval = { action(); numbers -= number }, contrasted = true, enabled = enabled) {
							Text("InputChip $number")
						}

					SuggestionChip(
						onClick = { action(); numbers += Random.nextInt() },
						contrasted = true,
						enabled = enabled
					) {
						Text("SuggestionChip")
					}
				}
			}
		}
	}
}
