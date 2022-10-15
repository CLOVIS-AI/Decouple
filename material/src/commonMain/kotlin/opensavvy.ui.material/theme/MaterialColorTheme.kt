package opensavvy.ui.material.theme

import opensavvy.ui.core.theme.Color
import opensavvy.ui.core.theme.ColorStrength
import opensavvy.ui.core.theme.ColorTheme

class MaterialColorTheme(
	val palette: Palette,
	override val primary: ColorStrength,
	override val secondary: ColorStrength,
	override val tertiary: ColorStrength,
	override val error: ColorStrength,
	override val background: Color,
	override val backgroundVariant: Color,
	override val contrast: Color,
	override val outline: Color,
	override val outlineVariant: Color,
) : ColorTheme

/**
 * Returns the Material palette for this [ColorTheme].
 *
 * If this color theme is not a [MaterialColorTheme], returns `null`.
 */
val ColorTheme.materialPalette: Palette?
	get() = when (this) {
		is MaterialColorTheme -> palette
		else -> null
	}
