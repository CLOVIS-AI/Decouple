package opensavvy.decouple.headless.node

import opensavvy.decouple.headless.Component
import opensavvy.decouple.headless.node.Node.MainSlot
import kotlin.reflect.KProperty

private const val INDENT = "  "

/**
 * Mutable real-time representation of a [Component].
 *
 * During composition, the UI is represented as a tree of [Node].
 * Users are expected to convert nodes to the immutable type-safe wrapper [Component] before accessing its attributes.
 *
 * ### Attributes and slots
 *
 * Compose represents components as functions, which are configured via their parameters.
 * Conceptually, we split these parameters into three categories:
 * - [Attributes] are non-composable parameters, they represent data inputs (e.g. `Int`, `String`, `YourClass`) or
 *   events (e.g. `onClick: () -> Unit`).
 * - [Slots] are composable parameters, which customize some inner parts of the component (e.g. a table's cells).
 * - The primary content (also called [main slot][MainSlot]) is a slot marked as the most important child of a component.
 *   Idiomatically, it is named `content` and declared as the last mandatory argument of a composable function, meaning
 *   its name never appears in the codebase (thanks to Kotlin's syntax).
 *
 * Here is an example of what they look like:
 * ```kotlin
 * SomeComposable(
 *     value = "Hello",                    // attribute (data type)
 *     onClick = { println("Clicked!") },  // attribute (event)
 *     actions = {                         // slot
 *         Text("×")
 *     },
 * ) {                                     // content/main slot
 *     Text("Click and read the console!")
 *     Text("Some other text")
 * }
 * ```
 *
 * The node tree stores all children of a component interleaved, no matter if they are slots or not.
 * To avoid confusion between the main content and slots, a node representing a fake component is inserted as a parent
 * for each slot. The previous example is thus stored like this:
 * ```
 * SomeComposable (attributes: value, onClick)
 *     Slot "actions"
 *         Text "×"
 *     Text "Click and read the console!"
 *     Text "Some other text"
 * ```
 *
 * To read more about slots, see the [Compose documentation](https://developer.android.com/jetpack/compose/layouts/basics#slot-based-layouts).
 *
 * This difference is of little importance to users of the library: attributes and slots are both represented as regular
 * function parameters in composable functions, and as regular fields of a [Component] implementation. This is also the
 * reason why attributes and slots only appear in this interface, and not its parent [NodeTree].
 */
interface Node : NodeTree {
	val name: String

	/**
	 * `true` if this node corresponds to no composable, instead corresponding to a slot parameter of its parent node.
	 *
	 * To read more about slots, see [Node].
	 */
	val isSlot: Boolean

	/**
	 * The different attributes of this node.
	 *
	 * We call attributes the arguments of a composable function that are not composable themselves.
	 * For more information on attributes and slots, see [Node].
	 *
	 * A usage example is provided in the [Component] documentation.
	 */
	val attributes: Attributes

	/**
	 * The different slots of this node.
	 *
	 * We call slots the arguments of a composable function that are themselves composable.
	 * For more information on attributes and slots, see [Node].
	 *
	 * The main slot (also called 'content') is not included in this collection. To access it, see [content].
	 */
	val slots: Slots

	/**
	 * Accessor for the main slot of this node.
	 *
	 * An example of usage is provided in the [Component] documentation.
	 * For more information on attributes and slots, see [Node].
	 */
	val content get() = MainSlot(slots)

	/**
	 * Creates a clone of this object, which is not impacted by future modifications.
	 *
	 * A clone is not guaranteed to be a different object than its source: for example, an immutable implementation
	 * (not just read-only) may return itself, as modifications are impossible anyway.
	 */
	fun clone(): Node

	override fun nodes(): Sequence<Node> = sequence {
		yieldAll(slots.children.flatMap { it.nodes() })

		yield(this@Node)
	}

	override fun directChildren(): Sequence<Node> = sequence {
		yieldAll(slots.children)
	}

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
		fun get(): NodeTree = slots.children
			.filterNot { it.isSlot }
			.let(::IterableNodeTree)

		operator fun getValue(thisRef: Any?, property: KProperty<*>) = get()
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
