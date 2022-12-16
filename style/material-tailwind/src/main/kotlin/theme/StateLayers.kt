package opensavvy.decouple.material.tailwind.theme

import androidx.compose.runtime.Composable
import opensavvy.state.Progression
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLButtonElement

@Composable
internal fun StateLayers(layerClasses: Array<Array<String>>?, layerAgnosticClasses: Array<String>) {
	layerClasses?.forEach {
		Div(
			{
				classes(
					"absolute",
					"inset-0",
					*layerAgnosticClasses,
				)
				classes(*it)
			}
		)
	}
}

@Composable
internal fun AnimatedLeadingIcon(
	show: Boolean,
	content: @Composable () -> Unit,
) {
	Div(
		{
			classes(
				"transition-all",
				"duration-300",
				"relative",
				"-mr-1"
			)

			if (show) {
				classes("w-5", "h-5", "mr-1")
			} else {
				classes("w-0", "h-5")
			}
		}
	) {
		content()
	}
}


internal fun AttrsScope<HTMLButtonElement>.setDisabledState(
	enabled: Boolean,
	disabledClasses: List<String>,
	loading: Progression,
) {
	if (!enabled) {
		classes(disabledClasses)
	}

	if (!enabled || loading is Progression.Loading) {
		disabled()
		if (enabled) {
			classes("cursor-wait")
		} else {
			classes("disabled:cursor-not-allowed")
		}
	}
}
