package opensavvy.decouple.headless.execution

import androidx.compose.runtime.*
import opensavvy.decouple.headless.Component
import opensavvy.decouple.headless.Component.Companion.viewAs
import opensavvy.decouple.headless.node.Attributes
import opensavvy.decouple.headless.node.Node
import opensavvy.decouple.headless.node.NodeTree
import opensavvy.decouple.headless.node.Slots
import kotlin.reflect.KProperty

/**
 * The node used internally by Compose to represent the entire UI.
 *
 * This class is the internal state of the UI, with no protections.
 * It should not be used directly when writing UI tests.
 * Instead, [viewAs] should be used to convert it into a [Component].
 *
 * Users of Decouple should only interact with this class when writing a new [Component] instance (to bind the values
 * it stores to their component, using [attributes], [nodes] and [content]).
 * A complete example is available in [Component]'s documentation.
 */
class ExecutableNode(
	override val name: String,
	override val isSlot: Boolean,
) : Node {

	private val _attributes = HashMap<String, Any?>()
	private val _slots = ArrayList<ExecutableNode>()

	//region Attributes

	override val attributes: Attributes.Mutable = ExecutableAttributes()

	private inner class ExecutableAttributes : Attributes.Mutable {
		override fun getRaw(attribute: String): Any? =
			_attributes[attribute]

		override fun set(attribute: String, value: Any?) {
			_attributes[attribute] = value
		}

		override fun clone(): Attributes =
			Attributes.Immutable(_attributes)

		override fun toString(): String =
			if (_attributes.isEmpty()) ""
			else _attributes.toString()
	}

	//endregion
	//region Slots

	override val slots: Slots = ExecutableSlots()

	private inner class ExecutableSlots : Slots {
		override val children: MutableList<ExecutableNode>
			get() = _slots

		override fun clone(): Slots =
			Slots.Immutable(this)

	}

	//endregion

	override fun clone() = Node.Immutable(
		name,
		isSlot,
		Attributes.Immutable(_attributes),
		Slots.Immutable(slots),
	)

	override fun toString() = toPrettyString()

	internal inner class Applier : AbstractApplier<ExecutableNode>(this) {
		override fun insertBottomUp(index: Int, instance: ExecutableNode) {
			current._slots.add(index, instance)
		}

		override fun insertTopDown(index: Int, instance: ExecutableNode) {
			// We insert bottom-up, this is a no-op
		}

		override fun move(from: Int, to: Int, count: Int) {
			current._slots.move(from, to, count)
		}

		override fun onClear() {
			current._slots.clear()
		}

		override fun remove(index: Int, count: Int) {
			current._slots.remove(index, count)
		}
	}
}

/**
 * Convenience function to recompose an [ExecutableNode].
 */
@Composable
fun Node(
	name: String,
	isSlot: Boolean = false,
	update: @DisallowComposableCalls() (Updater<ExecutableNode>.() -> Unit),
	content: @Composable () -> Unit,
) {
	ComposeNode<ExecutableNode, ExecutableNode.Applier>(
		factory = { ExecutableNode(name, isSlot) },
		update = update,
		content = content,
	)
}

/**
 * Convenience function to recompose a secondary slot named [name].
 *
 * Secondary slots do not have attributes, so they do not need to be updated.
 */
@Composable
fun Slot(
	name: String,
	content: @Composable () -> Unit,
) {
	Node(
		name,
		isSlot = true,
		update = {},
		content,
	)
}

/**
 * Convenience function to recompose a secondary slot named after [property].
 *
 * Secondary slots do not have attributes, so they do not need to be updated.
 */
@Composable
fun Slot(
	property: KProperty<NodeTree>,
	content: @Composable () -> Unit,
) = Slot(property.name, content)
