package opensavvy.decouple.demo.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import opensavvy.decouple.core.atom.actionable.*
import opensavvy.decouple.core.atom.text.Text
import opensavvy.decouple.core.layout.Column
import opensavvy.decouple.core.layout.Row
import opensavvy.decouple.persist.persistentStateOf

@Composable
fun Buttons() = Column {
	Text("Buttons are the primary interaction means with the library.")

	var enabled by remember { persistentStateOf("buttons.enabled") { true } }

	Text("There are multiple kinds of buttons:")
	Row {
		Button({ delay(1000) }, enabled = enabled) {
			Text("Button")
		}

		SecondaryButton({ delay(1000) }, enabled = enabled) {
			Text("Secondary button")
		}

		PrimaryButton({ delay(1000) }, enabled = enabled) {
			Text("Primary button")
		}

		PrimaryButton({ delay(1000) }, enabled = enabled, primary = true) {
			Text("Highlighted primary button")
		}

		ContrastButton({ delay(1000) }, enabled = enabled) {
			Text("Contrast button")
		}
	}

	Text("States:")
	ChipGroup {
		FilterChip(enabled, onToggle = { enabled = it }) {
			Text("Enabled")
		}
	}
}
