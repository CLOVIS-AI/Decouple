package opensavvy.ui.demo

import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import opensavvy.state.Progression.Companion.loading
import opensavvy.state.ProgressionReporter.Companion.report
import opensavvy.ui.core.basic.*
import opensavvy.ui.core.layout.*
import kotlin.random.Random

expect fun start(app: @Composable () -> Unit)

suspend fun action() {
	// Loading for 5 seconds
	for (i in 0..100) {
		report(loading(i / 100.0))
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

			Row(alignment = Alignment.Center) {
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

			Row(horizontal = Arrangement.SpaceBetween, alignment = Alignment.End) {
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

			Row {
				Text("Fields (regular) :")

				var userName by remember { mutableStateOf("") }
				TextField(
					"Username",
					userName,
					onChange = { userName = it },
					required = true,
					enabled = enabled,
					allowedSize = 6..20,
				)

				var instant by remember { mutableStateOf(Clock.System.now()) }
				InstantField(
					"Instant",
					instant,
					onChange = { instant = it },
					enabled = enabled,
				)
			}

			Row {
				Text("Fields (contrast) :")

				var userName by remember { mutableStateOf("") }
				TextField(
					"Username",
					userName,
					onChange = { userName = it },
					required = true,
					enabled = enabled,
					contrasted = true,
					allowedSize = 6..20,
				)

				var instant by remember { mutableStateOf(Clock.System.now()) }
				InstantField(
					"Instant",
					instant,
					onChange = { instant = it },
					contrasted = true,
					enabled = enabled,
				)
			}

			Row(horizontal = Arrangement.Center, alignment = Alignment.Center) {
				Box {
					Text("Level 1 text")
					Text("Level 2 text")
				}
			}

			UserList()
		}
	}
}
