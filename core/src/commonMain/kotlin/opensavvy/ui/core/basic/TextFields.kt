package opensavvy.ui.core.basic

import androidx.compose.runtime.Composable
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import opensavvy.ui.core.UI

interface TextFields {

	/**
	 * The label to a text field such as [TextField].
	 *
	 * When the same input is composed of multiple
	 */
	@Composable
	fun FieldLabel(
		label: String,
	)

	@Composable
	fun TextField(
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
	)

	@Composable
	fun InstantField(
		label: String?,
		value: Instant?,
		onChange: (Instant) -> Unit,
		onReset: (() -> Unit)?,
		required: Boolean,
		enabled: Boolean,
		contrasted: Boolean,
		supportingText: (@Composable () -> Unit)?,
		failureMessage: (@Composable () -> Unit)?,
	)

	@Composable
	fun LocalDateTimeField(
		label: String?,
		value: LocalDateTime?,
		onChange: (LocalDateTime) -> Unit,
		onReset: (() -> Unit)?,
		required: Boolean,
		enabled: Boolean,
		contrasted: Boolean,
		supportingText: (@Composable () -> Unit)?,
		failureMessage: (@Composable () -> Unit)?,
	)

	@Composable
	fun LocalDateField(
		label: String?,
		value: LocalDate?,
		onChange: (LocalDate) -> Unit,
		onReset: (() -> Unit)?,
		required: Boolean,
		enabled: Boolean,
		contrasted: Boolean,
		supportingText: (@Composable () -> Unit)?,
		failureMessage: (@Composable () -> Unit)?,
	)

	@Composable
	fun LocalTimeField(
		label: String?,
		value: LocalTime?,
		onChange: (LocalTime) -> Unit,
		onReset: (() -> Unit)?,
		required: Boolean,
		enabled: Boolean,
		contrasted: Boolean,
		supportingText: (@Composable () -> Unit)?,
		failureMessage: (@Composable () -> Unit)?,
	)
}

@Composable
fun FieldLabel(
	label: String,
) {
	UI.current.FieldLabel(label)
}

@Composable
fun TextField(
	label: String?,
	value: String?,
	onChange: (String) -> Unit,
	onReset: (() -> Unit)? = null,
	required: Boolean = false,
	enabled: Boolean = true,
	hideValue: Boolean = false,
	contrasted: Boolean = false,
	allowedSize: IntRange? = null,
	multiline: Boolean = false,
	prefix: String? = null,
	suffix: String? = null,
	icon: (@Composable () -> Unit)? = null,
	actions: (@Composable () -> Unit)? = null,
	supportingText: (@Composable () -> Unit)? = null,
	failureText: (@Composable () -> Unit)? = null,
) {
	UI.current.TextField(
		label = label,
		value = value,
		onChange = onChange,
		onReset = onReset,
		required = required,
		enabled = enabled,
		hideValue = hideValue,
		contrasted = contrasted,
		allowedSize = allowedSize,
		multiline = multiline,
		prefix = prefix,
		suffix = suffix,
		icon = icon,
		actions = actions,
		supportingText = supportingText,
		failureMessage = failureText
	)
}

@Composable
fun InstantField(
	label: String?,
	value: Instant?,
	onChange: (Instant) -> Unit,
	onReset: (() -> Unit)? = null,
	required: Boolean = false,
	enabled: Boolean = true,
	contrasted: Boolean = false,
	supportingText: (@Composable () -> Unit)? = null,
	failureMessage: (@Composable () -> Unit)? = null,
) {
	UI.current.InstantField(
		label = label,
		value = value,
		onChange = onChange,
		onReset = onReset,
		required = required,
		enabled = enabled,
		contrasted = contrasted,
		supportingText = supportingText,
		failureMessage = failureMessage
	)
}

@Composable
fun LocalDateTimeField(
	label: String?,
	value: LocalDateTime?,
	onChange: (LocalDateTime) -> Unit,
	onReset: (() -> Unit)? = null,
	required: Boolean = false,
	enabled: Boolean = true,
	contrasted: Boolean = false,
	supportingText: (@Composable () -> Unit)? = null,
	failureMessage: (@Composable () -> Unit)? = null,
) {
	UI.current.LocalDateTimeField(
		label = label,
		value = value,
		onChange = onChange,
		onReset = onReset,
		required = required,
		enabled = enabled,
		contrasted = contrasted,
		supportingText = supportingText,
		failureMessage = failureMessage
	)
}

@Composable
fun LocalDateField(
	label: String?,
	value: LocalDate?,
	onChange: (LocalDate) -> Unit,
	onReset: (() -> Unit)? = null,
	required: Boolean = false,
	enabled: Boolean = true,
	contrasted: Boolean = false,
	supportingText: (@Composable () -> Unit)? = null,
	failureMessage: (@Composable () -> Unit)? = null,
) {
	UI.current.LocalDateField(
		label = label,
		value = value,
		onChange = onChange,
		onReset = onReset,
		required = required,
		enabled = enabled,
		contrasted = contrasted,
		supportingText = supportingText,
		failureMessage = failureMessage
	)
}

@Composable
fun LocalTimeField(
	label: String?,
	value: LocalTime?,
	onChange: (LocalTime) -> Unit,
	onReset: (() -> Unit)? = null,
	required: Boolean = false,
	enabled: Boolean = true,
	contrasted: Boolean = false,
	supportingText: (@Composable () -> Unit)? = null,
	failureMessage: (@Composable () -> Unit)? = null,
) {
	UI.current.LocalTimeField(
		label = label,
		value = value,
		onChange = onChange,
		onReset = onReset,
		required = required,
		enabled = enabled,
		contrasted = contrasted,
		supportingText = supportingText,
		failureMessage = failureMessage
	)
}
