package opensavvy.ui.core.theme

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
	 * Simple immutable implementation of the [ColorTheme] interface.
	 */
	data class Immutable(
		override val primary: ColorStrength,
		override val secondary: ColorStrength,
		override val tertiary: ColorStrength,
		override val error: ColorStrength,
		override val background: Color,
		override val backgroundVariant: Color,
		override val contrast: Color,
	) : ColorTheme

}
