package opensavvy.decouple.core.theme

/**
 * Design-system-independent color representation.
 *
 * Most UI implementations will most likely implement this interface by creating a simple class that stores an [RGB] value.
 * However, other implementations may represent the color differently (for example with an external CSS variable whose
 * value does not appear in code).
 *
 * In all cases, to facilitate interoperability, each implementation must provide conversion methods into standard
 * formats ([rgb], [hex]).
 *
 * Colors also store their relationship with other colors: see [lighter], [darker] and [on].
 *
 * **Note for UI implementors**: If you create your own Color implementation for your UI, you must still accept any other
 * valid Color implementation in all functions that accept colors.
 * This is necessary to let end-users provide their own colors for your components.
 * All Color implementations must implement [rgb] and [hex], which you can use to convert any arbitrary color to your own system.
 */
interface Color {

	/**
	 * A lighter shade of the same tone.
	 *
	 * Each implementation may decide how it selects a lighter shade.
	 * If this color is the lightest shade available, this property returns `null`.
	 *
	 * @see lighter
	 * @see darkerOrNull
	 */
	val lighterOrNull: Color?

	/**
	 * A lighter shade of the same tone.
	 *
	 * Each implementation may decide how it selects a lighter shade.
	 * If this color is the lightest shade available, this property returns itself.
	 *
	 * @see lighterOrNull
	 * @see darker
	 */
	val lighter: Color
		get() = lighterOrNull ?: this

	/**
	 * A darker shade of the same tone.
	 *
	 * Each implementation may decide how it selects a darker shade.
	 * If this color is the darkest shade available, this property returns `null`.
	 *
	 * @see darker
	 * @see lighterOrNull
	 */
	val darkerOrNull: Color?

	/**
	 * A darker shade of the same tone.
	 *
	 * Each implementation may decide how it selects a darker shade.
	 * If this color is the darkest shade available, this property returns itself.
	 *
	 * @see darkerOrNull
	 * @see lighter
	 */
	val darker: Color
		get() = darkerOrNull ?: this

	/**
	 * The color used by default for elements that are displayed on top of this color.
	 *
	 * The color returned by this property is a good default color for text, icons and other similar components displayed
	 * on top of the current color.
	 *
	 * Example usage:
	 * ```kotlin
	 * val background = Theme.current.background
	 * val foreground = Theme.current.background.on
	 * ```
	 */
	val on: Color

	/**
	 * Converts this color into [RGB].
	 *
	 * Although the [Color] interface does not specify the way the color is represented in memory, it is necessary to
	 * provide a way to convert any color into RGB. This may be called if your color is used by another implementation.
	 */
	val rgb: RGB

	/**
	 * Converts this color into a hexadecimal representation.
	 *
	 * This value must follow the same conventions as [RGB.fromHex].
	 */
	val hex: String
		get() = rgb.hex

}
