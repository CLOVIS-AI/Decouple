package opensavvy.decouple.material.tailwind.atom.actionable

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.atom.ProgressIndicator
import opensavvy.decouple.core.atom.actionable.Buttons
import opensavvy.decouple.core.theme.Theme
import opensavvy.decouple.material.tailwind.theme.AnimatedLeadingIcon
import opensavvy.decouple.material.tailwind.theme.StateLayers
import opensavvy.decouple.material.tailwind.theme.css
import opensavvy.decouple.material.tailwind.theme.setDisabledState
import opensavvy.progress.Progress
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div

object MTButtons : Buttons {

	@Composable
	override fun ButtonSpec(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progress,
		icon: (@Composable () -> Unit)?,
		content: @Composable Buttons.ButtonScope.() -> Unit,
	) {
		val primaryAccent = Theme.color.primary.accent.css
		val backgroundOn = Theme.color.background.on.css

		val classes = arrayOf(
			"focus-visible:outline-none",
			"text-materialColor1",
			"focus-visible:bg-materialColor1/focus",
			"enabled:hover:bg-materialColor1/hover",
		)

		val disabledClasses = listOf(
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
	override fun PrimaryButtonSpec(
		onClick: () -> Unit,
		primary: Boolean,
		enabled: Boolean,
		loading: Progress,
		icon: (@Composable () -> Unit)?,
		content: @Composable Buttons.ButtonScope.() -> Unit,
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

		val disabledClasses = listOf(
			"disabled:text-materialColor3/disabled",
			"disabled:bg-materialColor3/disabledBg",
		)

		val elevationClasses = buildList {
			if (primary) {
				add("group-focus:bg-materialColor1/focus")
				add("group-enabled:hover:bg-materialColor1/hover")
			} else {
				add("group-focus:bg-materialColor4/focus")
				add("group-enabled:hover:bg-materialColor4/hover")
			}
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
			layerClasses = listOf(elevationClasses),
			content = content
		)
	}

	@Composable
	override fun SecondaryButtonSpec(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progress,
		icon: (@Composable () -> Unit)?,
		content: @Composable Buttons.ButtonScope.() -> Unit,
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

		val disabledClasses = listOf(
			"disabled:text-materialColor2/disabled", "disabled:outline-materialColor2/disabledBg"
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
	override fun ContrastButtonSpec(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progress,
		icon: (@Composable () -> Unit)?,
		content: @Composable Buttons.ButtonScope.() -> Unit,
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

		val disabledClasses = listOf(
			"disabled:text-materialColor3/disabled",
			"disabled:bg-materialColor3/disabledBg",
			"disabled:shadow-none",
		)

		val firstLayerClasses = listOf("bg-materialColor1/normal")

		val secondLayerClasses = listOf(
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
			layerClasses = listOf(
				firstLayerClasses,
				secondLayerClasses,
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

private object ButtonScope : Buttons.ButtonScope

@Composable
private fun AbstractButton(
	onClick: () -> Unit,
	enabled: Boolean,
	loading: Progress,
	icon: (@Composable () -> Unit)?,
	classes: Array<String>?,
	disabledClasses: List<String>,
	style: (StyleScope.() -> Unit) = {},
	layerClasses: List<List<String>>?,
	content: @Composable Buttons.ButtonScope.() -> Unit,
) {

	Button({
		style(style)

		classes(*buttonClasses)

		if (classes != null) {
			classes(*classes)
		}

		setDisabledState(enabled, disabledClasses, loading)

		onClick { onClick() }

	}) {
		if (icon != null) icon()

		AnimatedLeadingIcon(loading is Progress.Loading) {
			ProgressIndicator(loading)
		}

		Div {
			content(ButtonScope)
		}

		StateLayers(layerClasses, layerAgnosticClasses)
	}

}
