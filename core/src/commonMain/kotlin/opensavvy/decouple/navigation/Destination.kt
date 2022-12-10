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

	val fullRoute: List<String>
		get() {
			val result = ArrayList<String>()

			var dst: Destination = this
			while (true) {
				if (dst.route.isNotBlank())
					result += dst.route

				dst = dst.parent ?: break
			}

			return result
		}

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
	 */
	val parent: Destination?

	/**
	 * Is this destination a root of the navigation tree?
	 *
	 * `true` if [parent] is `null`.
	 */
	val isRoot get() = parent == null

	@Composable
	fun render()

}
