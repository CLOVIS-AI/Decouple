package opensavvy.decouple.material.tailwind.atom.input

import androidx.compose.runtime.Composable
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import opensavvy.decouple.core.atom.input.TextFields
import opensavvy.decouple.core.atom.text.Text

object MTTextFields : TextFields {

	@Composable
	override fun FieldLabelSpec(
		label: String,
	) {
		Text(label)
	}

	@Composable
	override fun TextFieldSpec(
		label: String?,
		value: String?,
		onChange: (String) -> Unit,
		onReset: (() -> Unit)?,
		required: Boolean,
		enabled: Boolean,
		hideValue: Boolean,
		contrasted: Boolean,
		allowedSize: IntRange?,
		multiline: Boolean,
		prefix: String?,
		suffix: String?,
		icon: (@Composable () -> Unit)?,
		actions: (@Composable () -> Unit)?,
		supportingText: (@Composable () -> Unit)?,
		failureMessage: (@Composable () -> Unit)?,
	) {
		Text("Not implemented")
	}

	@Composable
	override fun InstantFieldSpec(
		label: String?,
		value: Instant?,
		onChange: (Instant) -> Unit,
		onReset: (() -> Unit)?,
		required: Boolean,
		enabled: Boolean,
		contrasted: Boolean,
		supportingText: (@Composable () -> Unit)?,
		failureMessage: (@Composable () -> Unit)?,
	) {
		Text("Not implemented")
	}

	@Composable
	override fun LocalDateTimeFieldSpec(
		label: String?,
		value: LocalDateTime?,
		onChange: (LocalDateTime) -> Unit,
		onReset: (() -> Unit)?,
		required: Boolean,
		enabled: Boolean,
		contrasted: Boolean,
		supportingText: (@Composable () -> Unit)?,
		failureMessage: (@Composable () -> Unit)?,
	) {
		Text("Not implemented")
	}

	@Composable
	override fun LocalDateFieldSpec(
		label: String?,
		value: LocalDate?,
		onChange: (LocalDate) -> Unit,
		onReset: (() -> Unit)?,
		required: Boolean,
		enabled: Boolean,
		contrasted: Boolean,
		supportingText: (@Composable () -> Unit)?,
		failureMessage: (@Composable () -> Unit)?,
	) {
		Text("Not implemented")
	}

	@Composable
	override fun LocalTimeFieldSpec(
		label: String?,
		value: LocalTime?,
		onChange: (LocalTime) -> Unit,
		onReset: (() -> Unit)?,
		required: Boolean,
		enabled: Boolean,
		contrasted: Boolean,
		supportingText: (@Composable () -> Unit)?,
		failureMessage: (@Composable () -> Unit)?,
	) {
		Text("Not implemented")
	}

}
