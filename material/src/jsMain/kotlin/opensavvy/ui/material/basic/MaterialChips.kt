package opensavvy.ui.material.basic

import androidx.compose.runtime.Composable
import opensavvy.ui.core.basic.Chips
import opensavvy.ui.core.layout.Row
import opensavvy.ui.core.progression.Progression
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.dom.Button as DomButton

actual interface MaterialChips : Chips {

	@Composable
	override fun AssistChip(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		contrasted: Boolean,
		icon: (@Composable () -> Unit)?,
		action: (@Composable () -> Unit)?,
		content: @Composable () -> Unit,
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

			content()

			if (action != null)
				action()
		}
	}

	@Composable
	override fun FilterChip(
		activated: Boolean,
		onToggle: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		contrasted: Boolean,
		icon: (@Composable () -> Unit)?,
		content: @Composable () -> Unit,
	) {
		DomButton(
			{
				//TODO style in https://gitlab.com/opensavvy/opensavvy-ui/-/issues/16

				onClick { onToggle() }

				if (!enabled || loading is Progression.Loading)
					disabled()
			}
		) {
			if (icon != null)
				icon()

			content()
		}
	}

	@Composable
	override fun InputChip(
		onRemoval: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		contrasted: Boolean,
		icon: (@Composable () -> Unit)?,
		content: @Composable () -> Unit,
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

			content()
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
		content: @Composable () -> Unit,
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

			content()

			if (action != null)
				action()
		}
	}

	@Composable
	override fun ChipGroup(
		multiline: Boolean,
		chips: @Composable () -> Unit,
	) {
		Row {
			chips()
		}
	}
}
