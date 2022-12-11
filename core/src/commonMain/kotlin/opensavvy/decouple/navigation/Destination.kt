package opensavvy.decouple.navigation

import androidx.compose.runtime.Composable

/**
 * Representation of a navigation route in an application.
 */
interface Destination {

	/**
	 * The route segment to the current screen.
	 *
	 * Must not contain a `/`.
	 */
	val route: String

	/**
	 * The absolute route of this destination.
	 *
	 * Returns a sequence of the [route] value for all elements from the root to this destination.
	 * For more information about the hierarchy, see [parent].
	 */
	val fullRoute: Sequence<String>
		get() = parents
			.map { it.route }
			.filter { it.isNotBlank() }

	/**
	 * The user-visible title of this screen.
	 *
	 * It may be sent to the platform for pretty printing (in the user's history, in the browser's title), but will not
	 * be directly visible on screen.
	 */
	val title: String

	/**
	 * The parent of this screen.
	 *
	 * Screens form hierarchical forests.
	 *
	 * `null` if this screen is one of the navigation roots.
	 *
	 * To recursively access all parents, see [parents].
	 */
	val parent: Destination?

	private suspend fun SequenceScope<Destination>.generateParents() {
		parent?.apply { generateParents() }
		yield(this@Destination)
	}

	/**
	 * Generates the parents by following the [parent] attribute.
	 *
	 * Results are presented in postfix order: the parent appears before the child.
	 * This is the same order as URLs are displayed in, for example, for the URl `/test/foo/bar`,
	 * this property returns the sequence `[test, foo, bar]`, where the root is the first element,
	 * and the current destination is the last element.
	 */
	val parents: Sequence<Destination>
		get() = sequence {
			generateParents()
		}

	/**
	 * Is this destination a root of the navigation tree?
	 *
	 * `true` if [parent] is `null`.
	 */
	val isRoot get() = parent == null

	@Composable
	fun render()

}
