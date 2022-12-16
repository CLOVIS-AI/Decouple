package opensavvy.decouple.demo.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import opensavvy.decouple.core.atom.actionable.ChipGroup
import opensavvy.decouple.core.atom.actionable.FilterChip
import opensavvy.decouple.core.atom.input.*
import opensavvy.decouple.core.atom.text.Text
import opensavvy.decouple.core.layout.Column
import opensavvy.decouple.core.layout.Row
import opensavvy.decouple.persist.persistentStateOf

@Composable
fun TextFields() = Column {
	Text("Text fields let the user input data into the app.")

	Row {
		Text("Label:")
		FieldLabel("text")
	}

	var enabled by remember { persistentStateOf("fields.enabled") { true } }
	var required by remember { persistentStateOf("fields.required") { false } }
	var contrasted by remember { persistentStateOf("fields.contrasted") { false } }
	var allowReset by remember { persistentStateOf("fields.reset") { false } }
	var multiLine by remember { persistentStateOf("fields.multiline") { false } }

	var showSupportingText by remember { persistentStateOf("fields.showSupporting") { false } }
	var supportingText by remember { persistentStateOf("fields.supporting") { "" } }

	var showFailureText by remember { persistentStateOf("fields.showFailure") { false } }
	var failureText by remember { persistentStateOf("fields.failure") { "" } }

	var text by remember { persistentStateOf<String?>("fields.text") { null } }
	var instant by remember { persistentStateOf<Instant?>("fields.instant") { null } }
	var localDateTime by remember { persistentStateOf<LocalDateTime?>("fields.localDateTime") { null } }
	var localDate by remember { persistentStateOf<LocalDate?>("fields.localDate") { null } }
	var localTime by remember { persistentStateOf<LocalTime?>("fields.localTime") { null } }

	TextField(
		"My text field",
		value = text,
		onChange = { text = it },
		enabled = enabled,
		required = required,
		contrasted = contrasted,
		onReset = { text = null }.takeIf { allowReset },
		multiline = multiLine,
		supportingText = if (showSupportingText) {
			{ Text(supportingText) }
		} else null,
		failureText = if (showFailureText) {
			{ Text(failureText) }
		} else null,
	)

	TextField(
		"Password field",
		value = text,
		onChange = { text = it },
		enabled = enabled,
		required = required,
		contrasted = contrasted,
		onReset = { text = null }.takeIf { allowReset },
		multiline = multiLine,
		supportingText = if (showSupportingText) {
			{ Text(supportingText) }
		} else null,
		failureText = if (showFailureText) {
			{ Text(failureText) }
		} else null,
		hideValue = true,
	)

	InstantField(
		"Instant",
		value = instant,
		onChange = { instant = it },
		enabled = enabled,
		required = required,
		contrasted = contrasted,
		onReset = { text = null }.takeIf { allowReset },
		supportingText = if (showSupportingText) {
			{ Text(supportingText) }
		} else null,
		failureMessage = if (showFailureText) {
			{ Text(failureText) }
		} else null,
	)

	LocalDateTimeField(
		"Local date time",
		value = localDateTime,
		onChange = { localDateTime = it },
		enabled = enabled,
		required = required,
		contrasted = contrasted,
		onReset = { text = null }.takeIf { allowReset },
		supportingText = if (showSupportingText) {
			{ Text(supportingText) }
		} else null,
		failureMessage = if (showFailureText) {
			{ Text(failureText) }
		} else null,
	)

	LocalDateField(
		"Local date",
		value = localDate,
		onChange = { localDate = it },
		enabled = enabled,
		required = required,
		contrasted = contrasted,
		onReset = { text = null }.takeIf { allowReset },
		supportingText = if (showSupportingText) {
			{ Text(supportingText) }
		} else null,
		failureMessage = if (showFailureText) {
			{ Text(failureText) }
		} else null,
	)

	LocalTimeField(
		"Local time",
		value = localTime,
		onChange = { localTime = it },
		enabled = enabled,
		required = required,
		contrasted = contrasted,
		onReset = { text = null }.takeIf { allowReset },
		supportingText = if (showSupportingText) {
			{ Text(supportingText) }
		} else null,
		failureMessage = if (showFailureText) {
			{ Text(failureText) }
		} else null,
	)

	Text("States:")
	ChipGroup {
		FilterChip(enabled, onToggle = { enabled = it }) {
			Text("Enabled")
		}

		FilterChip(required, onToggle = { required = it }) {
			Text("Required")
		}

		FilterChip(contrasted, onToggle = { contrasted = it }) {
			Text("Contrasted")
		}

		FilterChip(allowReset, onToggle = { allowReset = it }) {
			Text("Allow reset")
		}

		FilterChip(multiLine, onToggle = { multiLine = it }) {
			Text("Multiline")
		}

		FilterChip(showSupportingText, onToggle = { showSupportingText = it }) {
			Text("Show supporting text")
		}

		FilterChip(showFailureText, onToggle = { showFailureText = it }) {
			Text("Show supporting text")
		}
	}

	TextField("Supporting text", supportingText, { supportingText = it }, enabled = showSupportingText)
	TextField("Failure text", failureText, { failureText = it }, enabled = showFailureText)
}
