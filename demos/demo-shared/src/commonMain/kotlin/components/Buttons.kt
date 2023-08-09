package opensavvy.decouple.demo.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import opensavvy.decouple.core.atom.*
import opensavvy.decouple.core.layout.Row
import opensavvy.decouple.core.layout.SupportedScreen
import opensavvy.decouple.persist.persistentStateOf

@Composable
fun Buttons() {
	var enabled by remember { persistentStateOf("buttons.enabled") { true } }

	SupportedScreen(
		"Buttons",
		supportTitle = "States",
		support = {
			ChipGroup {
				FilterChip(enabled, onToggle = { enabled = it }) {
					Text("Enabled")
				}
			}
		},
	) {
		Text("Buttons are the primary interaction means with the library.")

		Text("There are multiple kinds of buttons:")
		Row {
			Button({ delay(1000) }, enabled = enabled) {
				Text("Button")
			}

			ActionButton({ delay(1000) }, enabled = enabled) {
				Text("Secondary button")
			}

			SecondaryButton({ delay(1000) }, enabled = enabled) {
				Text("Primary button")
			}

			PrimaryButton({ delay(1000) }, enabled = enabled) {
				Text("Highlighted primary button")
			}

			ContrastButton({ delay(1000) }, enabled = enabled) {
				Text("Contrast button")
			}
		}
	}
}
