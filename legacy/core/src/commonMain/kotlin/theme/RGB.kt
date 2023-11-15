package opensavvy.decouple.core.theme

/**
 * A color in the [RGBA color model](https://en.wikipedia.org/wiki/RGBA_color_model).
 */
data class RGB(
	val red: UByte,
	val green: UByte,
	val blue: UByte,
	/** Opaqueness: `0u` is fully transparent, `255u` is fully opaque. */
	val alpha: UByte = 255u,
) {

	/**
	 * Creates a [RGB] value.
	 *
	 * This constructor is provided for convenience as most programs deal with [Int] more often than [UByte].
	 * However, the values of all components must be in the range `0..255`.
	 * Otherwise, the behavior is unspecified.
	 */
	constructor(red: Int, green: Int, blue: Int, alpha: Int = 255) : this(
		red.toUByte(),
		green.toUByte(),
		blue.toUByte(),
		alpha.toUByte()
	)

	private fun UByte.toHex() = toString(16).padStart(2, '0')

	/**
	 * Converts this RGB color into a hexadecimal string.
	 *
	 * The only constraint on the value of this property is that it must be accepted by [RGB.fromHex], and return an identical value:
	 * ```kotlin
	 * assert(rgb == RGB.fromHex(rgb.hex))
	 * ```
	 * The exact value returned by this property may change in the future, but this relationship will always hold true.
	 */
	// This function caches its result.
	// It's not documented because it may change in the future.
	val hex by lazy(LazyThreadSafetyMode.PUBLICATION) {
		"#${red.toHex()}${green.toHex()}${blue.toHex()}${alpha.toHex()}"
	}

	override fun toString() = "RGB($red, $green, $blue, $alpha)"

	companion object {

		/**
		 * Parses a hexadecimal string into an [RGB] value.
		 *
		 * [hex] should be in the format `RRGGBB` or `RRGGBBAA` (one byte for the red component, one byte for the green
		 * component, one byte for the blue component, and an optional one byte for the alpha component).
		 */
		fun fromHex(hex: String): RGB {
			val stripped = hex.removePrefix("#")
			require(stripped.length == 6 || stripped.length == 8) { "Expected an hexadecimal value of 6 or 8 digits, found ${stripped.length} digits: $hex" }
			val red = stripped.substring(0..1).toUByte(16)
			val green = stripped.substring(2..3).toUByte(16)
			val blue = stripped.substring(4..5).toUByte(16)
			val alpha = if (stripped.length == 8) stripped.substring(6..7).toUByte(16) else 255u

			return RGB(red, green, blue, alpha)
		}
	}

}
