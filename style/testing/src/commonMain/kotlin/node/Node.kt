package opensavvy.decouple.testing.node

import opensavvy.decouple.testing.Component
import kotlin.reflect.KProperty

private const val INDENT = "  "

/**
 * Mutable real-time representation of a [Component].
 *
 * During composition, the UI is represented as a tree of [Node].
 * For testing, they are converted to the immutable type-safe wrapper [Component].
 */
interface Node {
	val name: String
	val isSlot: Boolean
	val attributes: Attributes
	val slots: Slots

	/**
	 * Accessor for the main slot of this node.
	 *
	 * For an example, see the [Component] documentation.
	 */
	val content get() = MainSlot(slots)

	/**
	 * Creates a clone of this object, which is not impacted by future modifications.
	 *
	 * A clone is not guaranteed to be a different object than its source: for example, an immutable implementation
	 * (not just read-only) may return itself, as modifications are impossible anyway.
	 */
	fun clone(): Node

	fun toPrettyString(): String = buildString {
		fun inner(node: Node, indent: String) {
			append(indent)
			append(node.name)

			if (node.isSlot)
				append(" slot")

			appendLine(" ${node.attributes}")

			for (child in node.slots.children) {
				inner(child, indent + INDENT)
			}
		}

		inner(this@Node, "")
	}

	/**
	 * See [content].
	 */
	class MainSlot(private val slots: Slots) {
		operator fun getValue(thisRef: Any?, property: KProperty<*>) =
			slots.children.filterNot { it.isSlot }
	}

	data class Immutable(
		override val name: String,
		override val isSlot: Boolean,
		override val attributes: Attributes.Immutable,
		override val slots: Slots.Immutable,
	) : Node {

		override fun clone() = this
	}
}
