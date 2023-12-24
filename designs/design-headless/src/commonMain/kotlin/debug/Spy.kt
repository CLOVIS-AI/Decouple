package opensavvy.decouple.headless.debug

import androidx.compose.runtime.Composable
import opensavvy.decouple.headless.Component
import opensavvy.decouple.headless.bind
import opensavvy.decouple.headless.compose
import opensavvy.decouple.headless.node.Node
import opensavvy.decouple.headless.node.NodeTree
import opensavvy.decouple.headless.node.getValue

/**
 * Exposed local [value] from the composition.
 *
 * For an example, see [spy].
 */
class Spy(node: Node) : Component {
	val value: Any? by node.attributes

	inline fun <reified T> cast() = value as T

	companion object : Component.Meta<Spy> {
		override val name = "Spy"

		override fun buildFrom(node: Node) = Spy(node)
	}
}

/**
 * Decouple polymorphism implementation for the [Spy][opensavvy.decouple.headless.debug.Spy] component.
 */
interface Spies {

	/**
	 * Exposes a local value from the composition into the headless UI tree.
	 *
	 * For an example, see [spy].
	 */
	@Composable
	fun Spy(value: Any?) {
		Spy.compose(
			update = {
				bind(value, Spy::value)
			}
		) {}
	}
}

/**
 * Accesses a local value from the composition.
 *
 * ### Example
 *
 * In this example, we want to test that a counter is increased when a button is pressed.
 * Testing the string output of the button's text may be difficult is the label is translated to other languages.
 * Instead, we expose the counter value directly to the test code.
 *
 * ```kotlin
 * fun test() = runTest {
 *     val ui = backgroundScope.runHeadlessUI {
 *         var counter by remember { mutableStateOf(0) }
 *
 *         Button(onClick = { counter++ }) {
 *             Text("Counter: $counter")
 *
 *             // Capture the local variable into the headless tree
 *             Spy(counter)
 *         }
 *     }
 *
 *     ui.paused {
 *         find(Button).click()
 *     }
 *
 *     ui.paused {
 *         // Read the value from the headless tree
 *         assertEquals(1, find(Button).content.spy<Int>())
 *     }
 * }
 * ```
 */
inline fun <reified T> NodeTree.spy() =
	find(Spy).cast<T>()
