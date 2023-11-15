package opensavvy.decouple.headless.atom

import androidx.compose.runtime.Composable
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import opensavvy.decouple.core.atom.TextFields
import opensavvy.decouple.headless.Component
import opensavvy.decouple.headless.bind
import opensavvy.decouple.headless.compose
import opensavvy.decouple.headless.execution.Slot
import opensavvy.decouple.headless.node.Node
import opensavvy.decouple.headless.node.NodeTree
import opensavvy.decouple.headless.node.getValue

class FieldLabel(node: Node) : Component {
	val label: String by node.attributes

	companion object : Component.Meta<FieldLabel> {
		override val name = "TextFields.FieldLabel"

		override fun buildFrom(node: Node) = FieldLabel(node)
	}
}

/**
 * Type-safe wrapper for [opensavvy.decouple.core.atom.TextField].
 */
class TextField(node: Node) : Component {
	val label: String? by node.attributes
	val value: String? by node.attributes
	val change: (String) -> Unit by node.attributes
	val reset: (() -> Unit)? by node.attributes
	val required: Boolean by node.attributes
	val enabled: Boolean by node.attributes
	val hideValue: Boolean by node.attributes
	val contrasted: Boolean by node.attributes
	val allowedSize: IntRange? by node.attributes
	val multiline: Boolean by node.attributes
	val prefix: String? by node.attributes
	val suffix: String? by node.attributes

	val icon by node.slots
	val actions by node.slots
	val supportingText by node.slots
	val failureMessage by node.slots

	companion object : Component.Meta<TextField> {
		override val name = "TextFields.TextField"

		override fun buildFrom(node: Node) = TextField(node)
	}
}

interface TemporalField<T> {
	val label: String?
	val value: T?
	val change: (T) -> Unit
	val reset: (() -> Unit)?
	val required: Boolean
	val enabled: Boolean
	val contrasted: Boolean

	val supportingText: NodeTree
	val failureMessage: NodeTree
}

/**
 * Type-safe wrapper for [opensavvy.decouple.core.atom.InstantField].
 */
class InstantField(node: Node) : TemporalField<Instant>, Component {
	override val label: String? by node.attributes
	override val value: Instant? by node.attributes
	override val change: (Instant) -> Unit by node.attributes
	override val reset: (() -> Unit)? by node.attributes
	override val required: Boolean by node.attributes
	override val enabled: Boolean by node.attributes
	override val contrasted: Boolean by node.attributes

	override val supportingText: NodeTree by node.attributes
	override val failureMessage: NodeTree by node.attributes

	companion object : Component.Meta<InstantField> {
		override val name = "TextFields.InstantField"

		override fun buildFrom(node: Node) = InstantField(node)
	}
}

/**
 * Type-safe wrapper for [opensavvy.decouple.core.atom.LocalDateTimeField].
 */
class LocalDateTimeField(node: Node) : TemporalField<LocalDateTime>, Component {
	override val label: String? by node.attributes
	override val value: LocalDateTime? by node.attributes
	override val change: (LocalDateTime) -> Unit by node.attributes
	override val reset: (() -> Unit)? by node.attributes
	override val required: Boolean by node.attributes
	override val enabled: Boolean by node.attributes
	override val contrasted: Boolean by node.attributes

	override val supportingText: NodeTree by node.attributes
	override val failureMessage: NodeTree by node.attributes

	companion object : Component.Meta<LocalDateTimeField> {
		override val name = "TextFields.LocalDateTimeField"

		override fun buildFrom(node: Node) = LocalDateTimeField(node)
	}
}

/**
 * Type-safe wrapper for [opensavvy.decouple.core.atom.LocalDateField].
 */
class LocalDateField(node: Node) : TemporalField<LocalDate>, Component {
	override val label: String? by node.attributes
	override val value: LocalDate? by node.attributes
	override val change: (LocalDate) -> Unit by node.attributes
	override val reset: (() -> Unit)? by node.attributes
	override val required: Boolean by node.attributes
	override val enabled: Boolean by node.attributes
	override val contrasted: Boolean by node.attributes

	override val supportingText: NodeTree by node.attributes
	override val failureMessage: NodeTree by node.attributes

	companion object : Component.Meta<LocalDateField> {
		override val name = "TextFields.LocalDateField"

		override fun buildFrom(node: Node) = LocalDateField(node)
	}
}

/**
 * Type-safe wrapper for [opensavvy.decouple.core.atom.LocalTimeField].
 */
class LocalTimeField(node: Node) : TemporalField<LocalTime>, Component {
	override val label: String? by node.attributes
	override val value: LocalTime? by node.attributes
	override val change: (LocalTime) -> Unit by node.attributes
	override val reset: (() -> Unit)? by node.attributes
	override val required: Boolean by node.attributes
	override val enabled: Boolean by node.attributes
	override val contrasted: Boolean by node.attributes

	override val supportingText: NodeTree by node.attributes
	override val failureMessage: NodeTree by node.attributes

	companion object : Component.Meta<LocalTimeField> {
		override val name = "TextFields.LocalTimeField"

		override fun buildFrom(node: Node) = LocalTimeField(node)
	}
}

object TTextFields : TextFields {

	@Composable
	override fun FieldLabelSpec(label: String) {
		FieldLabel.compose(
			update = {
				bind(label, FieldLabel::label)
			}
		) {}
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
		TextField.compose(
			update = {
				bind(label, TextField::label)
				bind(value, TextField::value)
				bind(onChange, TextField::change)
				bind(onReset, TextField::reset)
				bind(required, TextField::required)
				bind(enabled, TextField::enabled)
				bind(hideValue, TextField::hideValue)
				bind(contrasted, TextField::contrasted)
				bind(allowedSize, TextField::allowedSize)
				bind(multiline, TextField::multiline)
				bind(prefix, TextField::prefix)
				bind(suffix, TextField::suffix)
			}
		) {
			if (icon != null) Slot(TextField::icon) {
				icon()
			}

			if (actions != null) Slot(TextField::actions) {
				actions()
			}

			if (supportingText != null) Slot(TextField::supportingText) {
				supportingText()
			}

			if (failureMessage != null) Slot(TextField::failureMessage) {
				failureMessage()
			}
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
		InstantField.compose(
			update = {
				bind(label, InstantField::label)
				bind(value, InstantField::value)
				bind(onChange, InstantField::change)
				bind(onReset, InstantField::reset)
				bind(required, InstantField::required)
				bind(enabled, InstantField::enabled)
				bind(contrasted, InstantField::contrasted)
			}
		) {
			if (supportingText != null) Slot(InstantField::supportingText) {
				supportingText()
			}

			if (failureMessage != null) Slot(InstantField::failureMessage) {
				failureMessage()
			}
		}
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
		LocalDateTimeField.compose(
			update = {
				bind(label, LocalDateTimeField::label)
				bind(value, LocalDateTimeField::value)
				bind(onChange, LocalDateTimeField::change)
				bind(onReset, LocalDateTimeField::reset)
				bind(required, LocalDateTimeField::required)
				bind(enabled, LocalDateTimeField::enabled)
				bind(contrasted, LocalDateTimeField::contrasted)
			}
		) {
			if (supportingText != null) Slot(LocalDateTimeField::supportingText) {
				supportingText()
			}

			if (failureMessage != null) Slot(LocalDateTimeField::failureMessage) {
				failureMessage()
			}
		}
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
		LocalDateField.compose(
			update = {
				bind(label, LocalDateField::label)
				bind(value, LocalDateField::value)
				bind(onChange, LocalDateField::change)
				bind(onReset, LocalDateField::reset)
				bind(required, LocalDateField::required)
				bind(enabled, LocalDateField::enabled)
				bind(contrasted, LocalDateField::contrasted)
			}
		) {
			if (supportingText != null) Slot(LocalDateField::supportingText) {
				supportingText()
			}

			if (failureMessage != null) Slot(LocalDateField::failureMessage) {
				failureMessage()
			}
		}
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
		LocalTimeField.compose(
			update = {
				bind(label, LocalTimeField::label)
				bind(value, LocalTimeField::value)
				bind(onChange, LocalTimeField::change)
				bind(onReset, LocalTimeField::reset)
				bind(required, LocalTimeField::required)
				bind(enabled, LocalTimeField::enabled)
				bind(contrasted, LocalTimeField::contrasted)
			}
		) {
			if (supportingText != null) Slot(LocalTimeField::supportingText) {
				supportingText()
			}

			if (failureMessage != null) Slot(LocalTimeField::failureMessage) {
				failureMessage()
			}
		}
	}

}
