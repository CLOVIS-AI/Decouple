package opensavvy.decouple.testing.node

import kotlin.reflect.KProperty

/**
 * Children components of a component.
 *
 * To store non-composable attributes of a component, see [Attributes].
 */
interface Slots {

	val children: List<Node>

	/**
	 * Creates a clone of this object, which is not impacted by future modifications.
	 *
	 * A clone is not guaranteed to be a different object than its source: for example, an immutable implementation
	 * (not just read-only) may return itself, as modifications are impossible anyway.
	 */
	fun clone(): Slots

	/**
	 * Immutable implementation of [Slots].
	 *
	 * Note that slots are a collections of nodes, which are themselves mutable.
	 * This object is only immutable in a shallow sense of the term.
	 */
	class Immutable(from: Slots) : Slots {
		private val _children = ArrayList(from.children) // defensive copy to ensure immutability
		override val children: List<Node>
			get() = _children

		override fun clone(): Slots = this
	}
}

/**
 * Gets a [slot].
 *
 * Returns an empty list if no slot with that name is present.
 */
operator fun Slots.get(slot: String) =
	children.firstOrNull { it.isSlot && it.name == slot }
		?.content?.get()
		?: NodeTree.Empty

/**
 * Gets a slot named after [property].
 *
 * Returns an empty list if no slot with that name is present.
 */
operator fun Slots.getValue(thisRef: Any?, property: KProperty<*>) =
	get(property.name)
