package opensavvy.ui.material.basic

import androidx.compose.runtime.Composable
import opensavvy.ui.core.basic.Buttons
import opensavvy.ui.core.progression.Progression
import opensavvy.ui.core.theme.Theme
import opensavvy.ui.material.theme.css
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Button as DomButton

actual interface MaterialButtons : Buttons {

	@Composable
	override fun Button(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		icon: (@Composable () -> Unit)?,
		content: @Composable () -> Unit,
	) {
		val color = Theme.color.primary.accent.css

		DomButton(
			{
				//TODO style in https://gitlab.com/opensavvy/opensavvy-ui/-/issues/10

				style {
					color(color)
				}

				onClick { onClick() }

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
	override fun PrimaryButton(
		onClick: () -> Unit,
		primary: Boolean,
		enabled: Boolean,
		loading: Progression,
		icon: (@Composable () -> Unit)?,
		content: @Composable () -> Unit,
	) {
		val color = when (primary) {
			true -> Theme.color.primary.accent
			false -> Theme.color.secondary.container
		}

		val background = Theme.color.primary.accent.css
		val foreground = Theme.color.primary.accent.on.css

		DomButton(
			{
				//TODO style in https://gitlab.com/opensavvy/opensavvy-ui/-/issues/10

				style {
					backgroundColor(color.css)
					color(color.on.css)
				}

				onClick { onClick() }

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
	override fun SecondaryButton(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		icon: (@Composable () -> Unit)?,
		content: @Composable () -> Unit,
	) {
		val outline = Theme.color.outline
		val color = Theme.color.primary.accent

		DomButton(
			{
				//TODO style in https://gitlab.com/opensavvy/opensavvy-ui/-/issues/10

				style {
					outlineColor(outline.css)
					outlineWidth(1.px)
					color(color.css)
				}

				onClick { onClick() }

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
	override fun ContrastButton(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		icon: (@Composable () -> Unit)?,
		content: @Composable () -> Unit,
	) {
		val color = Theme.color.primary.accent

		DomButton(
			{
				//TODO style in https://gitlab.com/opensavvy/opensavvy-ui/-/issues/10

				style {
					color(color.css)
				}

				onClick { onClick() }

				if (!enabled || loading is Progression.Loading)
					disabled()
			}
		) {
			if (icon != null)
				icon()

			content()
		}
	}

}
