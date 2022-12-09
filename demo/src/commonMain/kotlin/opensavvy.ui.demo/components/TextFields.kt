package opensavvy.ui.demo.components

import androidx.compose.runtime.*
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import opensavvy.ui.core.basic.*
import opensavvy.ui.core.layout.Column
import opensavvy.ui.core.layout.Row

@Composable
fun TextFields() = Column {
	Text("Text fields let the user input data into the app.")

	Row {
		Text("Label:")
		FieldLabel("text")
	}

	var enabled by remember { mutableStateOf(true) }
	var required by remember { mutableStateOf(false) }
	var contrasted by remember { mutableStateOf(false) }
	var allowReset by remember { mutableStateOf(false) }
	var multiLine by remember { mutableStateOf(false) }

	var showSupportingText by remember { mutableStateOf(false) }
	var supportingText by remember { mutableStateOf("") }

	var showFailureText by remember { mutableStateOf(false) }
	var failureText by remember { mutableStateOf("") }

	var text by remember { mutableStateOf<String?>(null) }
	var instant by remember { mutableStateOf<Instant?>(null) }
	var localDateTime by remember { mutableStateOf<LocalDateTime?>(null) }
	var localDate by remember { mutableStateOf<LocalDate?>(null) }
	var localTime by remember { mutableStateOf<LocalTime?>(null) }

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
		FilterChip(enabled, onToggle = { enabled = !enabled }) {
			Text("Enabled")
		}

		FilterChip(required, onToggle = { required = !required }) {
			Text("Required")
		}

		FilterChip(contrasted, onToggle = { contrasted = !contrasted }) {
			Text("Contrasted")
		}

		FilterChip(allowReset, onToggle = { allowReset = !allowReset }) {
			Text("Allow reset")
		}

		FilterChip(multiLine, onToggle = { multiLine = !multiLine }) {
			Text("Multiline")
		}

		FilterChip(showSupportingText, onToggle = { showSupportingText = !showSupportingText }) {
			Text("Show supporting text")
		}

		FilterChip(showFailureText, onToggle = { showFailureText = !showFailureText }) {
			Text("Show supporting text")
		}
	}

	TextField("Supporting text", supportingText, { supportingText = it }, enabled = showSupportingText)
	TextField("Failure text", failureText, { failureText = it }, enabled = showFailureText)
}
