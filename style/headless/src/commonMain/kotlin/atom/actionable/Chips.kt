package opensavvy.decouple.headless.atom.actionable

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.atom.actionable.Chips
import opensavvy.decouple.headless.Component
import opensavvy.decouple.headless.bind
import opensavvy.decouple.headless.compose
import opensavvy.decouple.headless.execution.Slot
import opensavvy.decouple.headless.node.Node
import opensavvy.decouple.headless.node.getValue
import opensavvy.state.Progression

class AssistChip(node: Node) : Component {
	val click: () -> Unit by node.attributes
	val enabled: Boolean by node.attributes
	val loading: Progression by node.attributes
	val contrasted: Boolean by node.attributes

	val icon by node.slots
	val action by node.slots

	val content by node.content

	companion object : Component.Meta<AssistChip> {
		override val name = "Chips.AssistChip"

		override fun buildFrom(node: Node) = AssistChip(node)
	}
}

class FilterChip(node: Node) : Component {
	val active: Boolean by node.attributes
	val toggle: () -> Unit by node.attributes
	val enabled: Boolean by node.attributes
	val loading: Progression by node.attributes
	val contrasted: Boolean by node.attributes

	val icon by node.slots

	val content by node.content

	companion object : Component.Meta<FilterChip> {
		override val name = "Chips.FilterChip"

		override fun buildFrom(node: Node) = FilterChip(node)
	}
}

class InputChip(node: Node) : Component {
	val remove: () -> Unit by node.attributes
	val enabled: Boolean by node.attributes
	val loading: Progression by node.attributes
	val contrasted: Boolean by node.attributes

	val icon by node.slots

	val content by node.content

	companion object : Component.Meta<InputChip> {
		override val name = "Chips.InputChip"

		override fun buildFrom(node: Node) = InputChip(node)
	}
}

class SuggestionChip(node: Node) : Component {
	val click: () -> Unit by node.attributes
	val enabled: Boolean by node.attributes
	val loading: Progression by node.attributes
	val contrasted: Boolean by node.attributes

	val icon by node.slots
	val action by node.slots

	val content by node.content

	companion object : Component.Meta<SuggestionChip> {
		override val name = "Chips.SuggestionChip"

		override fun buildFrom(node: Node) = SuggestionChip(node)
	}
}

class ChipGroup(node: Node) : Component {
	val multiline: Boolean by node.attributes

	val content by node.content

	companion object : Component.Meta<ChipGroup> {
		override val name = "Chips.ChipGroup"

		override fun buildFrom(node: Node) = ChipGroup(node)
	}
}

object TChips : Chips {

	@Composable
	override fun AssistChip(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		contrasted: Boolean,
		icon: (@Composable () -> Unit)?,
		action: (@Composable () -> Unit)?,
		content: @Composable Chips.ChipScope.() -> Unit,
	) {
		AssistChip.compose(
			update = {
				bind(onClick, AssistChip::click)
				bind(enabled, AssistChip::enabled)
				bind(loading, AssistChip::loading)
				bind(contrasted, AssistChip::contrasted)
			}
		) {
			if (icon != null) Slot(AssistChip::icon) {
				icon()
			}

			if (action != null) Slot(AssistChip::action) {
				action()
			}

			content(TChipScope)
		}
	}

	@Composable
	override fun FilterChip(
		active: Boolean,
		onToggle: (Boolean) -> Unit,
		enabled: Boolean,
		loading: Progression,
		contrasted: Boolean,
		icon: (@Composable () -> Unit)?,
		content: @Composable Chips.ChipScope.() -> Unit,
	) {
		FilterChip.compose(
			update = {
				bind(active, FilterChip::active)
				bind(onToggle, FilterChip::toggle)
				bind(enabled, FilterChip::enabled)
				bind(loading, FilterChip::loading)
				bind(contrasted, FilterChip::contrasted)
			}
		) {
			if (icon != null) Slot(FilterChip::icon) {
				icon()
			}

			content(TChipScope)
		}
	}

	@Composable
	override fun InputChip(
		onRemove: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		contrasted: Boolean,
		icon: (@Composable () -> Unit)?,
		content: @Composable Chips.ChipScope.() -> Unit,
	) {
		InputChip.compose(
			update = {
				bind(onRemove, InputChip::remove)
				bind(enabled, InputChip::enabled)
				bind(loading, InputChip::loading)
				bind(contrasted, InputChip::contrasted)
			}
		) {
			if (icon != null) Slot(InputChip::icon) {
				icon()
			}

			content(TChipScope)
		}
	}

	@Composable
	override fun SuggestionChip(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		contrasted: Boolean,
		icon: (@Composable () -> Unit)?,
		action: (@Composable () -> Unit)?,
		content: @Composable Chips.ChipScope.() -> Unit,
	) {
		SuggestionChip.compose(
			update = {
				bind(onClick, SuggestionChip::click)
				bind(enabled, SuggestionChip::enabled)
				bind(loading, SuggestionChip::loading)
				bind(contrasted, SuggestionChip::contrasted)
			}
		) {
			if (icon != null) Slot(SuggestionChip::icon) {
				icon()
			}

			if (action != null) Slot(SuggestionChip::action) {
				action()
			}

			content(TChipScope)
		}
	}

	@Composable
	override fun ChipGroup(multiline: Boolean, chips: @Composable Chips.ChipGroupScope.() -> Unit) {
		ChipGroup.compose(
			update = {
				bind(multiline, ChipGroup::multiline)
			}
		) {
			chips(TChipGroupScope)
		}
	}

	object TChipScope : Chips.ChipScope
	object TChipGroupScope : Chips.ChipGroupScope
}
