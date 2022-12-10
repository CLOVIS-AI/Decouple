package explanations

import opensavvy.decouple.core.UI

/**
 * Each component exists in two variants, with the same name.
 *
 * ### The stateless variant
 *
 * The stateless variant is declared in an interface.
 * It has no default parameters.
 * When implementing a design system, a developer implements this variant.
 *
 * It is not recommended to call stateless components from other stateless components, as it makes it impossible for
 * downstream users to replace the called component through inheritance.
 *
 * ### The stateful variant
 *
 * The stateful variant is declared in the package top-level.
 * It manages simple states from the stateless variant (e.g. the `loading` parameter), and automatically
 * calls the stateless implementation from the
 * currently installed design system.
 *
 * ### Example
 *
 * Let's create a simplified button.
 * This button displays a simple string, and has a simple action.
 * Because the action may take some time to complete, we must also know if something is currently ongoing to display
 * feedback to the user.
 *
 * To make it possible to let multiple design systems to implement this button differently, we declare it in an interface:
 * ```kotlin
 * interface MyButton {
 *    @Composable
 *    fun Button(
 *        text: String,
 *        onClick: () -> Unit,
 *        loading: Progression,
 *    )
 * }
 * ```
 *
 * As we can see, each design system will only need to implement the visual appearance, and is not concerned by the
 * behavior of the button.
 * Even the fact that the operation may take time is abstracted away by the [Progression][opensavvy.state.Progression]
 * abstraction.
 *
 * To facilitate usage, we now declare the stateful variant, which will delegate its implementation to the correct
 * design system.
 * In everyday life, this is the variant that end-users will be calling, it can also add default parameters.
 * Notice that `onClick` is now marked as `suspend`: we use the [launch][opensavvy.decouple.core.progression.launch]
 * helper to extract the progression information.
 *
 * ```kotlin
 * @Composable
 * fun Button(
 *     text: String,
 *     onClick: suspend () -> Unit,
 *     scope: CoroutineScope = rememberCoroutineScope(),
 * ) {
 *     var loading by remember { mutableStateOf<Progression>(Progression.Done) }
 *
 *     UI.current.Button(
 *         text = text,
 *         onClick = {
 *             scope.launch {
 *                 onProgress = { loading = it },
 *                 block = onClick(),
 *             }
 *         },
 *         loading = loading,
 *     )
 * }
 * ```
 *
 * ### Future improvements
 *
 * The injection of the current design system into the application is implemented using a composition-local
 * (see [UI.LocalUI] and [UI.current]).
 * This implementation requires the [UI] interface to be provided by the `core` module; it is not possible to add your
 * own component interfaces to it.
 *
 * In the future, [context receivers](https://github.com/Kotlin/KEEP/blob/master/proposals/context-receivers.md) will
 * allow us to remove the composition-local.
 * Stateful variants will be declared in the context of the interface declaring the stateless variant.
 * Each project using this library will be able to declare its own [UI] interface that declares the components they want
 * to reuse between design systems, making it possible to add your own components to the API.
 */
@Suppress("ClassName", "unused")
@JsName("ComponentVariants")
object `Component variants`
