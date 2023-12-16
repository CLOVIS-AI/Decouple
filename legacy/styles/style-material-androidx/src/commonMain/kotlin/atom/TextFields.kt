package opensavvy.decouple.material.androidx.atom

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import opensavvy.decouple.core.atom.TextFields
import androidx.compose.material3.OutlinedTextField as M3OutlinedTextField
import androidx.compose.material3.TextField as M3TextField

@OptIn(ExperimentalMaterial3Api::class)
object MATextFields : TextFields {
	@Composable
	override fun FieldLabelSpec(label: String) {
		Text(label)
	}

	private fun leadingIcon(
		prefix: String?,
		icon: (@Composable () -> Unit)?,
	): (@Composable () -> Unit)? =
		if (prefix == null && icon == null) {
			null
		} else {
			{
				if (icon != null)
					icon()

				if (prefix != null)
					Text(prefix)
			}
		}

	private fun trailingIcon(
		suffix: String?,
		icon: (@Composable () -> Unit)?,
	): (@Composable () -> Unit)? =
		if (suffix == null && icon == null) {
			null
		} else {
			{
				if (suffix != null)
					Text(suffix)

				if (icon != null)
					icon()
			}
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
		//TODO in #76: add the 'onReset' action
		//TODO in #77: check allowedSize

		if (contrasted) {
			M3TextField(
				value = value ?: "",
				onValueChange = { onChange(it) },
				enabled = enabled,
				label = label?.let { { Text(label) } },
//				supportingText = failureMessage ?: supportingText, //TODO in #120
				isError = failureMessage != null,
				visualTransformation = if (hideValue) PasswordVisualTransformation() else VisualTransformation.None,
				singleLine = !multiline,
				leadingIcon = leadingIcon(prefix, icon),
				trailingIcon = trailingIcon(suffix, actions),
			)
		} else {
			M3OutlinedTextField(
				value = value ?: "",
				onValueChange = { onChange(it) },
				enabled = enabled,
				label = label?.let { { Text(label) } },
//				supportingText = failureMessage ?: supportingText, //TODO in #120
				isError = failureMessage != null,
				visualTransformation = if (hideValue) PasswordVisualTransformation() else VisualTransformation.None,
				singleLine = !multiline,
				leadingIcon = leadingIcon(prefix, icon),
				trailingIcon = trailingIcon(suffix, actions),
			)
		}
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
		//TODO in #78
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
		//TODO in #78
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
		//TODO in #78
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
		//TODO in #78
	}

}
