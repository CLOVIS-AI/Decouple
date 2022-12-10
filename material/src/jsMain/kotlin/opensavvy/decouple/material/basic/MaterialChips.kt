package opensavvy.decouple.material.basic

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.basic.Chips
import opensavvy.decouple.core.layout.Row
import opensavvy.state.Progression
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.dom.Button as DomButton

actual object MaterialChips : Chips {

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
		DomButton(
			{
				//TODO style in https://gitlab.com/opensavvy/opensavvy-ui/-/issues/16

				onClick { onClick() }

				if (!enabled || loading is Progression.Loading)
					disabled()
			}
		) {
			if (icon != null)
				icon()

			content(ChipScope)

			if (action != null)
				action()
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
		DomButton(
			{
				//TODO style in https://gitlab.com/opensavvy/opensavvy-ui/-/issues/16

				onClick { onToggle(!activated) }

				if (!enabled || loading is Progression.Loading)
					disabled()
			}
		) {
			if (icon != null)
				icon()

			content(ChipScope)
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
		DomButton(
			{
				//TODO style in https://gitlab.com/opensavvy/opensavvy-ui/-/issues/16

				onClick { onRemoval() }

				if (!enabled || loading is Progression.Loading)
					disabled()
			}
		) {
			if (icon != null)
				icon()

			content(ChipScope)
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
		DomButton(
			{
				//TODO style in https://gitlab.com/opensavvy/opensavvy-ui/-/issues/16

				onClick { onClick() }

				if (!enabled || loading is Progression.Loading)
					disabled()
			}
		) {
			if (icon != null)
				icon()

			content(ChipScope)

			if (action != null)
				action()
		}
	}

	@Composable
	override fun ChipGroup(
		multiline: Boolean,
		chips: @Composable Chips.ChipGroupScope.() -> Unit,
	) {
		Row {
			chips(ChipGroupScope)
		}
	}

	private object ChipScope : Chips.ChipScope
	private object ChipGroupScope : Chips.ChipGroupScope
}
