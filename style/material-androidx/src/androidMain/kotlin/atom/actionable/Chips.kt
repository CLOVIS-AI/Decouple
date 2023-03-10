package opensavvy.decouple.material.androidx.atom.actionable

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import opensavvy.decouple.core.atom.actionable.Chips
import opensavvy.state.Progression
import androidx.compose.material3.AssistChip as M3AssistChip
import androidx.compose.material3.ElevatedAssistChip as M3ElevatedAssistChip
import androidx.compose.material3.ElevatedFilterChip as M3ElevatedFilterChip
import androidx.compose.material3.ElevatedSuggestionChip as M3ElevatedSuggestionChip
import androidx.compose.material3.FilterChip as M3FilterChip
import androidx.compose.material3.InputChip as M3InputChip
import androidx.compose.material3.SuggestionChip as M3SuggestionChip

@OptIn(ExperimentalMaterial3Api::class)
object MAChips : Chips {

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
		if (contrasted) {
			M3ElevatedAssistChip(
				onClick = onClick,
				label = { content(MAChipScope) },
				enabled = enabled && loading == Progression.done(),
				leadingIcon = icon,
				trailingIcon = action,
			)
		} else {
			M3AssistChip(
				onClick = onClick,
				label = { content(MAChipScope) },
				enabled = enabled && loading == Progression.done(),
				leadingIcon = icon,
				trailingIcon = action,
			)
		}
	}

	@Composable
	override fun FilterChip(
		activated: Boolean,
		onToggle: (Boolean) -> Unit,
		enabled: Boolean,
		loading: Progression,
		contrasted: Boolean,
		icon: (@Composable () -> Unit)?,
		content: @Composable Chips.ChipScope.() -> Unit,
	) {
		//TODO in #74: add the tick when the filter is activated

		if (contrasted) {
			M3ElevatedFilterChip(
				activated,
				onClick = { onToggle(!activated) },
				label = { content(MAChipScope) },
				enabled = enabled && loading == Progression.done(),
				leadingIcon = icon,
			)
		} else {
			M3FilterChip(
				activated,
				onClick = { onToggle(!activated) },
				label = { content(MAChipScope) },
				enabled = enabled && loading == Progression.done(),
				leadingIcon = icon,
			)
		}
	}

	@Composable
	override fun InputChip(
		onRemoval: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		contrasted: Boolean,
		icon: (@Composable () -> Unit)?,
		content: @Composable Chips.ChipScope.() -> Unit,
	) {
		//TODO in #76: add the cross to explicit that it can be removed

		M3InputChip(
			selected = true,
			onClick = onRemoval,
			label = { content(MAChipScope) },
			enabled = enabled && loading == Progression.done(),
			leadingIcon = icon,
		)
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
		if (contrasted) {
			M3ElevatedSuggestionChip(
				onClick = onClick,
				label = { content(MAChipScope) },
				enabled = enabled && loading == Progression.done(),
				icon = icon,
			)
		} else {
			M3SuggestionChip(
				onClick = onClick,
				label = { content(MAChipScope) },
				enabled = enabled && loading == Progression.done(),
				icon = icon,
			)
		}
	}

	@Composable
	override fun ChipGroup(
		multiline: Boolean,
		chips: @Composable Chips.ChipGroupScope.() -> Unit,
	) {
		//TODO in 75: implement the 'multiline' attribute
		val scroll = rememberScrollState()

		Row(
			Modifier
				.horizontalScroll(scroll),
		) {
			chips(MAChipGroupScope)
		}
	}

	private object MAChipScope : Chips.ChipScope
	private object MAChipGroupScope : Chips.ChipGroupScope
}
