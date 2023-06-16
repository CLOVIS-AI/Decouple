package opensavvy.decouple.material.common.theme

import opensavvy.decouple.core.theme.Color
import opensavvy.decouple.core.theme.RGB

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
		get() = when {
			role.roleType == TonalPalette.RoleType.Variant && index == 4 -> MaterialColor(role, 11)
			role.roleType == TonalPalette.RoleType.Variant && index == 11 -> MaterialColor(role, 5)
			role.roleType == TonalPalette.RoleType.Normal && index == 4 -> MaterialColor(role, 14)
			role.roleType == TonalPalette.RoleType.Normal && index == 5 -> MaterialColor(role, 13)
			index in 0..3 -> MaterialColor(role, role.colors.lastIndex - 1)
			index in 4..6 -> MaterialColor(role, role.colors.lastIndex)
			index in 7..10 -> MaterialColor(role, 0)
			else -> MaterialColor(role, 3)
		}

	override val rgb: RGB
		get() = role.colors[index]

}
