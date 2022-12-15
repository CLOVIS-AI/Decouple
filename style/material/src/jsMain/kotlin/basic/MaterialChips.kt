package opensavvy.decouple.material.basic

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.basic.Chips
import opensavvy.decouple.core.basic.ProgressIndicator
import opensavvy.decouple.core.layout.Row
import opensavvy.decouple.core.theme.Theme
import opensavvy.decouple.material.icons.Tick
import opensavvy.decouple.material.theme.css
import opensavvy.state.Progression
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
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
		val backgroundVariantOn = Theme.color.backgroundVariant.on.css
		val outline = Theme.color.outline.css
		val background = Theme.color.background.css
		val backgroundOn = Theme.color.background.on.css
		val secondaryContainer = Theme.color.secondary.container.css
		val secondaryContainerOn = Theme.color.secondary.container.on.css
		val primary = Theme.color.primary.accent.css

		var layerClasses = arrayOf(
			arrayOf(
				"-m-px",
				"group-enabled:hover:bg-materialColor5/hover",
				"group-focus-visible:bg-materialColor5/focus"
			)
		)

		if (contrasted) {
			layerClasses = arrayOf(
				arrayOf(
					"-m-px",
				    "group-enabled:bg-materialColor7/normal"
				),
				arrayOf(
					"-m-px",
					"group-enabled:hover:bg-materialColor5/hover",
					"group-focus-visible:bg-materialColor5/focus"
				)
			)
		}

		val classes = buildList {
			add("border")
			add("focus-visible:outline-none")
			if (contrasted) {
				add("border-transparent")
				add("focus-visible:shadow-elevation1")
				add("enabled:hover:shadow-elevation2")
			}
		}


		val nonActivatedClasses = buildList {
			add("enabled:hover:bg-materialColor1/hover")
			add("enabled:hover:text-materialColor3")
			add("focus-visible:text-materialColor3")
			add("focus-visible:bg-materialColor1/focus")
			if (contrasted) {
				add("text-materialColor1")
				add("bg-materialColor6")
				add("shadow-elevation1")
			} else {
				add("text-materialColor1")
				add("border-materialColor2")
				add("focus-visible:border-materialColor3")
			}
		}

		val activatedClasses = buildList {
			add("bg-materialColor4")
			add("text-materialColor5")
			add("border-transparent")
			add("enabled:hover:shadow-elevation1")
			if (contrasted) {
				add("shadow-elevation1")
			}
		}

		val disabledClasses = buildList {
			add("disabled:text-materialColor3/disabled")
			if (contrasted) {
				add("disabled:shadow-none")
				add("disabled:bg-materialColor3/disabledBg")
			} else {
				if (!activated) {
					add("disabled:border-materialColor3/disabledBg")
				} else {
					add("disabled:bg-materialColor3/disabledBg")
				}
			}
		}

		AbstractChip(
			onClick = onToggle,
			enabled = enabled,
			activated = activated,
			loading = loading,
			icon = icon,
			classes = classes,
			disabledClasses = disabledClasses,
			activatedClasses = activatedClasses,
			nonActivatedClasses = nonActivatedClasses,
			style = {
				property("--material-color-1", backgroundVariantOn)
				property("--material-color-2", outline)
				property("--material-color-3", backgroundOn)
				property("--material-color-4", secondaryContainer)
				property("--material-color-5", secondaryContainerOn)
				property("--material-color-6", background)
				property("--material-color-7", primary)
			},
			layerClasses = layerClasses,
			content = content
		)
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

	private val layerAgnosticClasses = arrayOf(
		"font-roboto",
		"font-medium",
		"text-sm",
		"leading-5",
		"inline-flex",
		"justify-center",
		"items-center",
		"gap-1",
		"py-1",
		"px-2",
		"transition",
		"duration-200",
		"ease-linear",
		"rounded-lg"
	)

	private val chipClasses = arrayOf(
		*layerAgnosticClasses,
		"group",
		"relative",
	)

	@Composable
	private fun AbstractChip(
		onClick: (Boolean) -> Unit,
		enabled: Boolean,
		activated: Boolean,
		loading: Progression,
		icon: (@Composable () -> Unit)?,
		classes: List<String>,
		disabledClasses: List<String>,
		activatedClasses: List<String>,
		nonActivatedClasses: List<String>,
		style: (StyleScope.() -> Unit) = {},
		layerClasses: Array<Array<String>>?,
		content: @Composable Chips.ChipScope.() -> Unit,
	) {

		Button(
			{
				attr("role", "checkbox")
				style(style)

				classes(*chipClasses)
				classes(classes)

				setDisabledState(enabled, disabledClasses, loading)

				if (activated) {
					classes(activatedClasses)
				} else {
					classes(nonActivatedClasses)
				}

				onClick { onClick(!activated) }

			}
		) {
			if (icon != null)
				icon()

			AnimatedLeadingIcon(activated || loading is Progression.Loading) {
				ProgressIndicator(loading)
				Tick(activated, loading)
			}

			Div {
				content(ChipScope)
			}

			StateLayers(layerClasses, layerAgnosticClasses)
		}
	}
}
