package opensavvy.ui.material.theme

import opensavvy.ui.core.theme.Color
import opensavvy.ui.core.theme.RGB

internal val white = RGB(255, 255, 255)
internal val black = RGB(0, 0, 0)

class MaterialColor(private val role: TonalPalette, private val index: Int) : Color {
	override val lighterOrNull: MaterialColor?
		get() = when {
			index > 0 -> MaterialColor(role, index - 1)
			else -> null
		}

	override val darkerOrNull: Color?
		get() = when {
			index < role.colors.lastIndex -> MaterialColor(role, index + 1)
			else -> null
		}

	override val on: Color
		get() = when (index) {
			in 0..3 -> MaterialColor(role, role.colors.lastIndex - 1)
			in 4..6 -> MaterialColor(role, role.colors.lastIndex)
			in 7..10 -> MaterialColor(role, 0)
			else -> MaterialColor(role, 3)
		}

	override val rgb: RGB
		get() = role.colors[index]

}
