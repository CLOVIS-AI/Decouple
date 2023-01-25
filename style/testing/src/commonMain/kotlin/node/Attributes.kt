package opensavvy.decouple.testing.node

import kotlin.reflect.KProperty

/**
 * Component attributes.
 *
 * Attributes store all data about a component that isn't a child component.
 * This includes its state as well as its events.
 */
interface Attributes {

	/**
	 * Gets an [attribute].
	 *
	 * It is recommended to use [get] or [getValue] instead, as they will perform the type cast.
	 */
	fun getRaw(attribute: String): Any?

	/**
	 * Creates a clone of this object, which is not impacted by future modifications.
	 *
	 * A clone is not guaranteed to be a different object than its source: for example, an immutable implementation
	 * (not just read-only) may return itself, as modifications are impossible anyway.
	 */
	fun clone(): Attributes

	/**
	 * Mutable [component attributes][Attributes].
	 */
	interface Mutable : Attributes {

		/**
		 * Creates or updates an [attribute] to be [value].
		 *
		 * It is recommended to use [set] instead, as it is type-safe.
		 */
		operator fun set(attribute: String, value: Any?)

		/**
		 * Creates or updates an attribute named after [property] to be [value].
		 */
		operator fun <T : Any?> set(property: KProperty<T>, value: T) {
			set(property.name, value)
		}

	}

	/**
	 * Immutable implementation of [Attributes].
	 */
	class Immutable(from: Map<String, Any?>) : Attributes {
		private val attributes = HashMap(from) // defensive copy to ensure immutability
		override fun getRaw(attribute: String): Any? = attributes[attribute]
		override fun clone(): Attributes = this
	}
}

/**
 * Gets an [attribute] and casts its value to [T].
 */
inline operator fun <reified T : Any?> Attributes.get(attribute: String) =
	getRaw(attribute) as T

/**
 * Gets an attribute named after [property] and casts its value to [T].
 */
inline operator fun <reified T : Any?> Attributes.getValue(thisRef: Any?, property: KProperty<*>) =
	get<T>(property.name)
