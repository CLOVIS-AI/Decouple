package opensavvy.decouple.material.theme

import opensavvy.decouple.core.theme.RGB

class TonalPalette(val roleType: RoleType, vararg colors: RGB) {

	init {
		require(colors.size == 16) { "Material color roles should have 15 roles (100, 99, 98, 95, 90, 80, 70, 60, 50, 40, 35, 30, 25, 20, 10, 0), found ${colors.size} tones: $colors" }
	}

	val colors = colors.toList()

	val i100 get() = MaterialColor(this, 0)
	val i99 get() = MaterialColor(this, 1)
	val i98 get() = MaterialColor(this, 2)
	val i95 get() = MaterialColor(this, 3)
	val i90 get() = MaterialColor(this, 4)
	val i80 get() = MaterialColor(this, 5)
	val i70 get() = MaterialColor(this, 6)
	val i60 get() = MaterialColor(this, 7)
	val i50 get() = MaterialColor(this, 8)
	val i40 get() = MaterialColor(this, 9)
	val i35 get() = MaterialColor(this, 10)
	val i30 get() = MaterialColor(this, 11)
	val i25 get() = MaterialColor(this, 12)
	val i20 get() = MaterialColor(this, 13)
	val i10 get() = MaterialColor(this, 14)
	val i0 get() = MaterialColor(this, 15)

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is TonalPalette) return false

		if (colors != other.colors) return false

		return true
	}

	override fun hashCode(): Int {
		return colors.hashCode()
	}

	enum class RoleType {
		Normal,
		Variant,
	}
}
