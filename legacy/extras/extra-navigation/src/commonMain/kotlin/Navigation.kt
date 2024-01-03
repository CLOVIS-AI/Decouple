package opensavvy.decouple.navigation

import androidx.compose.runtime.Composable

/**
 * Handles the navigation from one place to another in a CLOVIS app.
 *
 * Because navigation might not always refer to full screens, but some sub-areas of a screen, we prefer to call navigation
 * targets "[destinations][Destination]".
 * Places form a hierarchical tree, which can be navigated in multiple different ways:
 * - Lateral navigation: moving to another place at the same level of hierarchy.
 *   It should be obvious to the user how to reach any of the places under the same level of hierarchy.
 *   For example, tabs in an app, a bottom navigation bar, etc.
 *   See [lateral].
 * - Forward navigation: moving to another place anywhere else in the application.
 *   For example, search results, clicking a button, etc.
 *   See [forwards].
 * - Backward navigation: moving back to the last visited place.
 *   For example, the 'back' button on Android.
 *   See [backwards].
 * - Upward navigation: moving to the current place's hierarchical parent.
 *   For example, the 'back' arrow on the top left of many Android apps, the 'back' button on iOS.
 *   See [upwards].
 *
 * This navigation scheme is inspired by the
 * [Material Guidelines](https://material.io/design/navigation/understanding-navigation.html)
 * and the [iOS Guidelines](https://developer.apple.com/design/human-interface-guidelines/ios/app-architecture/navigation/).
 */
interface Navigation {

	val current: Destination

	//region Movement

	/**
	 * Moves forward to a [destination].
	 *
	 * This is the most common way of navigating in an app.
	 * Navigating forward stores which screen we're coming from, so we can navigate [backwards] to go back to it later.
	 * Forward navigation should not be mistaken for [lateral] navigation (navigating between multiple places with the
	 * same hierarchical status).
	 *
	 * For an overview of the different navigation directions, see [Navigation].
	 */
	fun forwards(destination: Destination)

	/**
	 * Laterally moves to [destination].
	 *
	 * Unlike [forwards] navigation, lateral navigation does not impact [backwards] navigation.
	 * That is, if the current place is `A` and the backward navigation target is `Z`,
	 * laterally moving to `B` will keep `Z` as the backward navigation target.
	 *
	 * This type of navigation should be used when navigating from a place to another one at the same hierarchical level
	 * in the navigation tree.
	 * For example, this is used for navigating the different tabs of a navigation bottom bar.
	 * The different lateral options should be easily discoverable by the user.
	 *
	 * For an overview of the different navigation directions, see [Navigation].
	 */
	fun lateral(destination: Destination)

	/**
	 * Moves backward to the last visited [Destination].
	 *
	 * Moving backwards means going back to the last visited place in the application.
	 * Backward navigation should not be mistaken with [upwards] navigation.
	 *
	 * When it is not possible to go back (we reached the top of the stack), [exit] is called.
	 *
	 * For an overview of the different navigation directions, see [Navigation].
	 */
	fun backwards()

	/**
	 * Moves upward to the last visited [Destination].
	 *
	 * Moving upwards means going to the current place's [parent][Destination.parent].
	 * From a given place, upward navigation always goes to the same place, unlike [backwards] navigation.
	 *
	 * When it is not possible to go up (we reached the navigation [root][Destination.isRoot]), this function does nothing.
	 *
	 * For an overview of the different navigation directions, see [Navigation].
	 *
	 * @param from The destination the user is moving upwards from.
	 * If not set, the user is assumed to be navigating upwards from the current destination.
	 * Use this argument when creating a navigation element that provides an action for upward navigation that can be
	 * accessed by the user even if they are currently navigating in a sub-element (e.g. a global left arrow on the top-left).
	 */
	fun upwards(from: Destination = current)

	//endregion
	//region Render

	@Composable
	fun render() = current.render()

	//endregion
	//region Internal behavior

	/**
	 * Called when the user navigates backwards from the root.
	 */
	fun exit()

	//endregion
}
