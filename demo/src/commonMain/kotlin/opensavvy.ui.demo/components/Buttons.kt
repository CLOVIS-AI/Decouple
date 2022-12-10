package opensavvy.ui.demo.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import opensavvy.ui.core.basic.*
import opensavvy.ui.core.layout.Column
import opensavvy.ui.core.layout.Row
import opensavvy.ui.utils.persist

@Composable
fun Buttons() = Column {
	Text("Buttons are the primary interaction means with the library.")

	var enabled by persist("buttons.enabled") { true }

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
		FilterChip(enabled, onToggle = { enabled = !enabled }) {
			Text("Enabled")
		}
	}
}
