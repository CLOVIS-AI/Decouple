package opensavvy.decouple.material.tailwind.atom.actionable

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.atom.ProgressIndicator
import opensavvy.decouple.core.atom.actionable.Chips
import opensavvy.decouple.core.theme.Theme
import opensavvy.decouple.material.tailwind.atom.icon.Close
import opensavvy.decouple.material.tailwind.atom.icon.Tick
import opensavvy.decouple.material.tailwind.theme.AnimatedLeadingIcon
import opensavvy.decouple.material.tailwind.theme.StateLayers
import opensavvy.decouple.material.tailwind.theme.css
import opensavvy.decouple.material.tailwind.theme.setDisabledState
import opensavvy.state.Progression
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div

object MTChips : Chips {

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
		val outline = Theme.color.outline.css
		val backgroundOn = Theme.color.background.on.css
		val background = Theme.color.background.css
		val primary = Theme.color.primary.accent.css

		BasicChip(
			onClick = onClick,
			enabled = enabled,
			loading = loading,
			contrasted = contrasted,
			icon = icon,
			action = action,
			content = content,
		) {
			property("--material-color-1", backgroundOn)
			property("--material-color-2", outline)
			property("--material-color-3", background)
			property("--material-color-4", primary)
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

		val layerClasses = buildList {
			if (contrasted) {
				add(
					buildList {
						add("-m-px")
						if (enabled) {
							add("bg-materialColor7/normal")
						}
					}
				)
				add(
					buildList {
						add("-m-px")
						if (activated) {
							add("group-enabled:hover:bg-materialColor5/hover")
							add("group-focus-visible:bg-materialColor5/focus")
						}
					}
				)
			} else {
				add(
					buildList {
						add("-m-px")
						if (activated) {
							add("group-enabled:hover:bg-materialColor5/hover")
							add("group-focus-visible:bg-materialColor5/focus")
						}
					}
				)
			}
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
			onClick = { onToggle(!activated) },
			enabled = enabled,
			activated = activated,
			loading = loading,
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
			icon = icon,
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
		val outline = Theme.color.outline.css
		val backgroundVariantOn = Theme.color.backgroundVariant.on.css
		val backgroundOn = Theme.color.background.on.css
		val background = Theme.color.background.css
		val primary = Theme.color.primary.accent.css

		val closeButtonClasses = listOf(
			"group-enabled:group-hover:text-materialColor5",
			"group-focus-visible:text-materialColor5",
		)

		BasicChip(
			onClick = onRemoval,
			enabled = enabled,
			loading = loading,
			contrasted = contrasted,
			icon = icon,
			hasClosedButton = true,
			closeButtonClasses = closeButtonClasses,
			content = content,
		)
		{
			property("--material-color-1", backgroundVariantOn)
			property("--material-color-2", outline)
			property("--material-color-3", background)
			property("--material-color-4", primary)
			property("--material-color-5", backgroundOn)
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
		val outline = Theme.color.outline.css
		val backgroundVariantOn = Theme.color.backgroundVariant.on.css
		val background = Theme.color.background.css
		val primary = Theme.color.primary.accent.css

		BasicChip(
			onClick = onClick,
			enabled = enabled,
			loading = loading,
			contrasted = contrasted,
			icon = icon,
			action = action,
			content = content,
		) {
			property("--material-color-1", backgroundVariantOn)
			property("--material-color-2", outline)
			property("--material-color-3", background)
			property("--material-color-4", primary)
		}
	}

	@Composable
	override fun ChipGroup(
		multiline: Boolean,
		chips: @Composable Chips.ChipGroupScope.() -> Unit,
	) = Div(
		{
			classes("flex", "flex-row", "flex-wrap", "gap-1")
		}
	) {
		chips(ChipGroupScope)
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
	private fun BasicChip(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		contrasted: Boolean,
		content: @Composable Chips.ChipScope.() -> Unit,
		icon: (@Composable () -> Unit)? = null,
		action: (@Composable () -> Unit)? = null,
		hasClosedButton: Boolean = false,
		closeButtonClasses: List<String> = emptyList(),
		style: (StyleScope.() -> Unit) = {},
	) {
		val layerClasses = buildList {
			if (contrasted) {
				add(
					buildList {
						add("-m-px")
						add("bg-materialColor4/normal")
					}
				)
			}
		}

		val classes = buildList {
			add("text-materialColor1")
			add("border")
			add("focus-visible:outline-none")
			add("enabled:hover:bg-materialColor1/hover")
			add("focus-visible:bg-materialColor1/focus")
			if (contrasted) {
				add("border-transparent")
				add("bg-materialColor3")
				add("shadow-elevation1")
				add("focus-visible:shadow-elevation1")
				add("enabled:hover:shadow-elevation2")
			} else {
				add("border-materialColor2")
				add("focus-visible:border-materialColor1")
			}
		}


		val disabledClasses = buildList {
			add("disabled:text-materialColor1/disabled")
			if (contrasted) {
				add("disabled:shadow-none")
				add("disabled:bg-materialColor1/disabledBg")
			} else {
				add("disabled:border-inherit")
			}
		}

		AbstractChip(
			onClick = onClick,
			enabled = enabled,
			activated = false,
			loading = loading,
			hasClosedButton = hasClosedButton,
			classes = classes,
			disabledClasses = disabledClasses,
			closeButtonClasses = closeButtonClasses,
			style = style,
			layerClasses = layerClasses,
			icon = icon,
			action = action,
			content = content
		)
	}

	@Composable
	private fun AbstractChip(
		onClick: () -> Unit,
		enabled: Boolean,
		activated: Boolean,
		loading: Progression,
		classes: List<String>,
		disabledClasses: List<String>,
		activatedClasses: List<String> = emptyList(),
		nonActivatedClasses: List<String> = emptyList(),
		closeButtonClasses: List<String> = emptyList(),
		style: StyleScope.() -> Unit = {},
		layerClasses: List<List<String>>? = null,
		hasClosedButton: Boolean = false,
		icon: (@Composable () -> Unit)? = null,
		action: (@Composable () -> Unit)? = null,
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

				onClick { onClick() }

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

			if (action != null)
				action()

			if (hasClosedButton)
				Div(
					{ classes(closeButtonClasses) }
				) { Close() }

			StateLayers(layerClasses, layerAgnosticClasses)
		}
	}

}
