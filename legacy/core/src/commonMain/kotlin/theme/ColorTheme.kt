package opensavvy.decouple.core.theme

/**
 * The colors used by the app.
 */
interface ColorTheme {

	/**
	 * Color of the most important elements of the UI.
	 */
	val primary: ColorStrength

	/**
	 * Color for important elements of the UI that are not the main focus.
	 */
	val secondary: ColorStrength

	/**
	 * Color for contrasting with [secondary] when many actions are available.
	 */
	val tertiary: ColorStrength

	/**
	 * Color for failures.
	 */
	val error: ColorStrength

	/**
	 * Background color of the whole application.
	 */
	val background: Color

	/**
	 * Background color used to differentiate elements.
	 *
	 * The background variant may be used for surfaces that must be differentiated from the general background for understanding
	 * reasons.
	 * To differentiate with the general background for importance reasons, see [contrast].
	 */
	val backgroundVariant: Color

	/**
	 * Background color used to highlight elements.
	 *
	 * The background variant may be used for surfaces that must be differentiated from the general background for importance reasons.
	 * If the differentiation doesn't originate from a difference in importance, use [backgroundVariant] instead.
	 */
	val contrast: Color

	/**
	 * Outline/border of components.
	 */
	val outline: Color

	/**
	 * Alternative outline/border of components.
	 */
	val outlineVariant: Color

	/**
	 * Which is the dominant style of this theme?
	 */
	val dominant: Dominant?

	/**
	 * Simple immutable implementation of the [ColorTheme] interface.
	 */
	data class Immutable(
		override val dominant: Dominant?,
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

	enum class Dominant {
		/** Mostly light background with dark text. */
		Light,

		/** Mostly dark background with light text. */
		Dark,
	}
}
