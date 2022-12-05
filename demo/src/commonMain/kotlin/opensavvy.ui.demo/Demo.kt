package opensavvy.ui.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import opensavvy.state.Progression.Companion.loading
import opensavvy.state.ProgressionReporter.Companion.report
import opensavvy.ui.core.UI
import opensavvy.ui.core.basic.AssistChip
import opensavvy.ui.core.basic.Button
import opensavvy.ui.core.basic.ChipGroup
import opensavvy.ui.core.basic.ContrastButton
import opensavvy.ui.core.basic.FilterChip
import opensavvy.ui.core.basic.InputChip
import opensavvy.ui.core.basic.InstantField
import opensavvy.ui.core.basic.PrimaryButton
import opensavvy.ui.core.basic.SecondaryButton
import opensavvy.ui.core.basic.SuggestionChip
import opensavvy.ui.core.basic.Text
import opensavvy.ui.core.basic.TextField
import opensavvy.ui.core.layout.Alignment
import opensavvy.ui.core.layout.Arrangement
import opensavvy.ui.core.layout.Box
import opensavvy.ui.core.layout.Column
import opensavvy.ui.core.layout.Row
import kotlin.random.Random

suspend fun action() {
	// Loading for 5 seconds
	for (i in 0..100) {
		report(loading(i / 100.0))
		delay(50)
	}
	println("Clicked")
}

@Composable
fun Demo(implementations: List<UI>) {
	AppearanceSelector(implementations) {
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
					FilterChip(
						active,
						onToggle = { action(); active = !active },
						enabled = enabled
					) {
						Text("FilterChip")
					}

					val numbers = remember { mutableStateListOf<Int>() }
					for (number in numbers)
						InputChip(onRemoval = { action(); numbers -= number }, enabled = enabled) {
							Text("InputChip $number")
						}

					SuggestionChip(
						onClick = { action(); numbers += Random.nextInt() },
						enabled = enabled
					) {
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
						InputChip(
							onRemoval = { action(); numbers -= number },
							contrasted = true,
							enabled = enabled
						) {
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
		}

		UserList()
	}
}
