package opensavvy.decouple.material.theme

import opensavvy.decouple.core.theme.Color
import opensavvy.decouple.core.theme.RGB

internal val white = RGB(255, 255, 255)
internal val black = RGB(0, 0, 0)

class MaterialColor(private val role: opensavvy.decouple.material.theme.TonalPalette, private val index: Int) : Color {
	override val lighterOrNull: opensavvy.decouple.material.theme.MaterialColor?
		get() = when {
			index > 0 -> opensavvy.decouple.material.theme.MaterialColor(role, index - 1)
			else -> null
		}

	override val darkerOrNull: Color?
		get() = when {
			index < role.colors.lastIndex -> opensavvy.decouple.material.theme.MaterialColor(role, index + 1)
			else -> null
		}

	override val on: Color
		get() = when (index) {
			in 0..3 -> opensavvy.decouple.material.theme.MaterialColor(role, role.colors.lastIndex - 1)
			in 4..6 -> opensavvy.decouple.material.theme.MaterialColor(role, role.colors.lastIndex)
			in 7..10 -> opensavvy.decouple.material.theme.MaterialColor(role, 0)
			else -> opensavvy.decouple.material.theme.MaterialColor(role, 3)
		}

	override val rgb: RGB
		get() = role.colors[index]

}
