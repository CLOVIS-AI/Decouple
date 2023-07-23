package opensavvy.decouple.headless

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.Updater
import opensavvy.decouple.core.atom.actionable.Buttons
import opensavvy.decouple.headless.Component.Meta
import opensavvy.decouple.headless.execution.ExecutableNode
import opensavvy.decouple.headless.execution.Node
import opensavvy.decouple.headless.node.Node
import kotlin.reflect.KProperty

/**
 * A component class.
 *
 * With Compose, components are represented by functions.
 * However, it is often useful to describe the component tree as objects with state,
 * for example during testing.
 * This interface bridges between these two representations.
 *
 * During recomposition, components are represented by the [Node] class.
 * [Component] implementations are simply typesafe views over their [Node] equivalent.
 * [Component] instances should be immutable.
 *
 * When converting from a composable function to a [Component], it is only necessary to implement the [Meta]
 * interface, which extracts data from a [Node].
 * However, we recommend using our utilities which make the conversion less error-prone.
 *
 * ### Commented example
 *
 * As an example, we want to build a component from this composable function, which has:
 * - regular state: `name`, `enabled`
 * - an event: `onClick`
 * - a secondary slot (child component as a composable lambda): `icon`
 * - a main slot: `content`
 * ```
 * @Composable
 * fun Foo(
 *     name: String,
 *     enabled: Boolean,
 *     onClick: () -> Unit,
 *     icon: @Composable () -> Unit,
 *     content: @Composable () -> Unit,
 * )
 * ```
 *
 * We must declare a class that implements [Component], whose companion object implements [Meta],
 * and we need to bind the state from the composable parameters.
 *
 * ```
 * class Foo(node: Node) : Component {
 *     // We can access the state directly from the node attributes
 *     // Declaring the type is mandatory. Events are regular attributes.
 *     val name: String by node.attributes
 *     val enabled: Boolean by node.attributes
 *     val onClick: () -> Unit by node.attributes
 *
 *     // The 'icon' slot is accessed through the slots
 *     // No need to declare a type, as it will be a node anyway
 *     val icon by node.slots
 *
 *     // The main slot has its own utility function
 *     val content by node.content
 *
 *     // We must declare the name of our composable function as well
 *     // as the way it should be built
 *     companion object : Component.Meta<Foo> {
 *         override val name = "your.package.Foo"
 *         override fun buildFrom(node: Node) = Foo(node)
 *     }
 * }
 *
 * // We can now declare our component to bind values to our class
 * @Composable
 * fun Foo(
 *     name: String,
 *     enabled: Boolean,
 *     onClick: () -> Unit,
 *     icon: @Composable () -> Unit,
 *     content: @Composable () -> Unit,
 * ) {
 *     Foo.compose(
 *         update = {
 *             // We must bind all non-slot parameters to the object
 *             // This will update their value in 'node.attributes'
 *             bind(name, Foo::name)
 *             bind(enabled, Foo::enabled)
 *             bind(onClick, Foo::onClick)
 *         }
 *     ) {
 *         // We now bind the various slots
 *
 *         // Secondary slots should be named to facilitate tests
 *         Slot(Foo::icon) { icon() }
 *
 *         // The main slot should be called directly
 *         content()
 *     }
 * }
 * ```
 */
interface Component {

	/**
	 * Metadata about a component.
	 */
	interface Meta<C : Component> {

		/**
		 * The unique name of a component.
		 *
		 * ### Naming convention for Decouple
		 *
		 * Components created as part of the Decouple project (declared as an interface in `core`)
		 * should be named after their interface and their function name.
		 *
		 * For example, the component [PrimaryButton][Buttons.PrimaryButtonSpec] declared in the [Buttons] interface should
		 * be declared as `"Buttons.PrimaryButton"`.
		 *
		 * ### Naming convention for custom components
		 *
		 * Custom components created outside the Decouple project should be named similarly to a fully qualified name,
		 * ensuring they start with a common prefix that is unique for the project.
		 * For example, you could simply reuse the fully qualified name of the composable function.
		 */
		val name: String

		/**
		 * Builds a [Component] instance from a [node].
		 *
		 * This function is mainly called by the [viewAs] builder.
		 */
		fun buildFrom(node: Node): C

		/**
		 * Returns `true` if this meta component can [build][buildFrom] the passed [node].
		 *
		 * If this method returns `false`, [buildFrom] of [node] will fail with some exception.
		 */
		fun canBuildFrom(node: Node): Boolean = !node.isSlot && node.name == name

	}

	companion object {

		/**
		 * Builds the [Component] represented by [component] using data from this [Node].
		 *
		 * The component is created from a copy of the node, to avoid the component changing in the future
		 * if the node is recomposed.
		 */
		fun <C : Component> Node.viewAs(component: Meta<C>): C {
			check(component.canBuildFrom(this)) { "The component $component cannot be built from the node:\n$this" }

			return component.buildFrom(clone())
		}

	}
}

/**
 * Helper function to bind composable values to a [Component] class.
 *
 * For an example, see the [Component] documentation.
 */
@Composable
fun Meta<*>.compose(
	update: @DisallowComposableCalls() (Updater<ExecutableNode>.() -> Unit) = {},
	content: @Composable () -> Unit,
) {
	Node(name, isSlot = false, update, content)
}

/**
 * Binds [value] to a [Node] [attribute].
 *
 * When [value] is modified, the attribute named after [attribute] is created or updated.
 * For an example, see the [Component] documentation.
 */
fun <T> Updater<ExecutableNode>.bind(value: T, attribute: KProperty<T>) {
	set(value) { attributes[attribute] = it }
}
