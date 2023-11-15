package opensavvy.decouple.core.theme

/**
 * Strength variations on a single color.
 */
data class ColorStrength(
	/**
	 * The strongest variant of the color.
	 *
	 * This may be used for small surfaces, icons, highlighted texts, etc.
	 */
	val accent: Color,

	/**
	 * The weakest variant of the color.
	 *
	 * This may be used for large backgrounds.
	 */
	val container: Color,
)
