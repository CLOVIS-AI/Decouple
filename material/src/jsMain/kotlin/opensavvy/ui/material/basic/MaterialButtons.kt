package opensavvy.ui.material.basic

import androidx.compose.runtime.Composable
import opensavvy.state.Progression
import opensavvy.ui.core.basic.Buttons
import opensavvy.ui.core.theme.Theme
import opensavvy.ui.material.icons.Spinner
import opensavvy.ui.material.theme.css
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.dom.Div
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
		val primaryAccent = Theme.color.primary.accent.css
		val backgroundOn = Theme.color.background.on.css

		val classes = arrayOf(
			"focus-visible:outline-none",
			"text-materialColor1",
			"focus-visible:bg-materialColor1/focus",
			"enabled:hover:bg-materialColor1/hover",
		)

		val disabledClasses = arrayOf(
			"disabled:text-materialColor2",
			"disabled:opacity-disabled",
		)

		AbstractButton(
			onClick = onClick,
			enabled = enabled,
			loading = loading,
			icon = icon,
			classes = classes,
			disabledClasses = disabledClasses,
			style = {
				property("--material-color-1", primaryAccent)
				property("--material-color-2", backgroundOn)
			},
			layerClasses = null,
			content = content
		)
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

		val backgroundOn = Theme.color.background.on.css

		val classes = arrayOf(
			"focus-visible:outline-none",
			"text-materialColor1",
			"bg-materialColor2",
			"enabled:hover:shadow-elevation1",
		)

		val disabledClasses = arrayOf(
			"disabled:text-materialColor3/disabled",
			"disabled:bg-materialColor3/disabledBg",
		)

		var elevationClasses = arrayOf(
			"group-focus:bg-materialColor4/focus",
			"group-enabled:hover:bg-materialColor4/hover",
		)

		if (primary) {
			elevationClasses = arrayOf(
				"group-focus:bg-materialColor1/focus",
				"group-enabled:hover:bg-materialColor1/hover",
			)
		}

		AbstractButton(
			onClick = onClick,
			enabled = enabled,
			loading = loading,
			icon = icon,
			classes = classes,
			disabledClasses = disabledClasses,
			style = {
				property("--material-color-1", color.on.css)
				property("--material-color-2", color.css)
				property("--material-color-3", backgroundOn)
				property("--material-color-4", color.on.css)
			},
			layerClasses = arrayOf(elevationClasses),
			content = content
		)
	}

	@Composable
	override fun SecondaryButton(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		icon: (@Composable () -> Unit)?,
		content: @Composable () -> Unit,
	) {
		val outline = Theme.color.outline.css
		val color = Theme.color.primary.accent.css
		val backgroundOn = Theme.color.background.on.css

		val classes = arrayOf(
			"outline",
			"outline-materialColor3",
			"focus-visible:outline-materialColor1",
			"outline-1",
			"text-materialColor1",
			"focus-visible:bg-materialColor1/focus",
			"enabled:hover:bg-materialColor1/hover",
		)

		val disabledClasses = arrayOf(
			"disabled:text-materialColor2/disabled",
			"disabled:outline-materialColor2/disabledBg",
		)

		AbstractButton(
			onClick = onClick,
			enabled = enabled,
			loading = loading,
			icon = icon,
			classes = classes,
			disabledClasses = disabledClasses,
			style = {
				property("--material-color-1", color)
				property("--material-color-2", backgroundOn)
				property("--material-color-3", outline)
			},
			layerClasses = null,
			content = content
		)
	}

	@Composable
	override fun ContrastButton(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		icon: (@Composable () -> Unit)?,
		content: @Composable () -> Unit,
	) {
		val color = Theme.color.primary.accent.css
		val background = Theme.color.background.css
		val backgroundOn = Theme.color.background.on.css

		val classes = arrayOf(
			"focus-visible:outline-none",
			"text-materialColor1",
			"bg-materialColor2",
			"shadow-elevation1",
			"enabled:hover:shadow-elevation2",
		)

		val disabledClasses = arrayOf(
			"disabled:text-materialColor3/disabled",
			"disabled:bg-materialColor3/disabledBg",
			"disabled:shadow-none",
		)

		val firstLayerClasses = arrayOf(
			"bg-materialColor1/normal",
		)

		val secondLayerClasses = arrayOf(
			"group-focus:bg-materialColor1/focus",
			"group-enabled:hover:bg-materialColor1/hover",
		)

		AbstractButton(
			onClick = onClick,
			enabled = enabled,
			loading = loading,
			icon = icon,
			classes = classes,
			disabledClasses = disabledClasses,
			style = {
				property("--material-color-1", color)
				property("--material-color-2", background)
				property("--material-color-3", backgroundOn)
			},
			layerClasses = arrayOf(
				firstLayerClasses,
				secondLayerClasses
			),
			content = content
		)
	}

}

private val layerAgnosticClasses = arrayOf(
	"font-roboto",
	"font-medium",
	"text-sm",
	"leading-5",
	"inline-flex",
	"justify-center",
	"items-center",
	"gap-1",
	"py-2",
	"px-4",
	"transition",
	"duration-200",
	"ease-linear",
	"rounded-full"
)

private val buttonClasses = arrayOf(
	*layerAgnosticClasses,
	"group",
	"relative",
)

@Composable
private fun AbstractButton(
	onClick: () -> Unit,
	enabled: Boolean,
	loading: Progression,
	icon: (@Composable () -> Unit)?,
	classes: Array<String>?,
	disabledClasses: Array<String>?,
	style: (StyleScope.() -> Unit)?,
	layerClasses: Array<Array<String>>?,
	content: @Composable () -> Unit,
) {

	DomButton(
		{

			style {
				if (style != null) {
					style()
				}
			}

			classes(*buttonClasses)

			if (classes != null) {
				classes(*classes)
			}

			if (!enabled && disabledClasses != null) {
				classes(*disabledClasses)
			}

			if (!enabled || loading is Progression.Loading) {
				disabled()
				if (enabled) {
					classes("cursor-wait")
				} else {
					classes("disabled:cursor-not-allowed")
				}
			}
			onClick { onClick() }

		}
	) {
		if (icon != null)
			icon()

		Div({
			classes(
				"transition-all",
				"duration-300",
				"relative",
				"-mr-1"
			)

			if (loading is Progression.Loading) {
				classes("w-5", "h-5", "mr-1")
			} else {
				classes("w-0", "h-5")
			}
		}
		) {
			Spinner(loading)
		}

		Div {
			content()
		}

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

}
