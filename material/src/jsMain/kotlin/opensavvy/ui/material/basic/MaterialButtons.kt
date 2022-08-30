package opensavvy.ui.material.basic

import androidx.compose.runtime.Composable
import opensavvy.ui.core.basic.Buttons
import opensavvy.ui.core.progression.Progression
import org.jetbrains.compose.web.attributes.disabled
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
		DomButton(
			{
				//TODO style in https://gitlab.com/opensavvy/opensavvy-ui/-/issues/10

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
		DomButton(
			{
				//TODO style in https://gitlab.com/opensavvy/opensavvy-ui/-/issues/10

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
		DomButton(
			{
				//TODO style in https://gitlab.com/opensavvy/opensavvy-ui/-/issues/10

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
		DomButton(
			{
				//TODO style in https://gitlab.com/opensavvy/opensavvy-ui/-/issues/10

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
