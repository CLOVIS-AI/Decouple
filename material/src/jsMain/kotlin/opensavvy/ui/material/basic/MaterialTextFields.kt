package opensavvy.ui.material.basic

import androidx.compose.runtime.Composable
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import opensavvy.ui.core.basic.Text
import opensavvy.ui.core.basic.TextField
import opensavvy.ui.core.basic.TextFields

actual interface MaterialTextFields : TextFields {

	/**
	 * The label to a text field such as [TextField].
	 *
	 * When the same input is composed of multiple
	 */
	@Composable
	override fun FieldLabel(
		label: String,
	) {
		Text(label)
	}

	@Composable
	override fun TextField(
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
	override fun InstantField(
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
	override fun LocalDateTimeField(
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
	override fun LocalDateField(
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
	override fun LocalTimeField(
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
