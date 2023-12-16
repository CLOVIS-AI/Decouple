package opensavvy.decouple.polymorphism

import androidx.compose.runtime.Composable

@DslMarker
annotation class PolymorphicComponentMarker

/**
 * Parent interface for all component declarations.
 *
 * For a detailed introduction, see the module-level documentation.
 *
 * ### Polymorphic components
 *
 * Interfaces are *the* way to declare APIs that have multiple implementations, so they are the
 * obvious choice for declaring polymorphic composables. However, we do not want to lose the
 * convenience of components being top-level functions, and we may want to provide some common
 * behavior to all implementations of a component.
 *
 * For these reasons, we split each component declarations in two: an interface that describes
 * the API of the component, and a top-level function that substitutes default values and behavior:
 * ```kotlin
 * interface Buttons : PolymorphicComponent {
 *     @Composable
 *     fun ButtonSpec(title: String, onClick: () -> Unit, disabled: Boolean)
 * }
 *
 * fun Buttons.Button(
 *     title: String,
 *     onClick: suspend () -> Unit,
 *     disabled: Boolean = false,
 * ) {
 *     ButtonSpec(title, onClick, disabled)
 * }
 * ```
 *
 * Here, the default behavior is simply a default parameter, but it could be anything.
 *
 * > Note how the top-level function has the interface as a receiver.
 * > In the future, this will be replaced by [context receivers](https://github.com/Kotlin/KEEP/blob/master/proposals/context-receivers.md).
 *
 * ### Centralized interface
 *
 * All components are therefore extension functions on interfaces that subtype [PolymorphicComponent].
 *
 * To simplify development, an application should create one (or more) central interface that subtype all the components
 * they want to use:
 * ```kotlin
 * interface FooUI : Buttons, Chips, StyledTexts, LinearLayouts
 * ```
 *
 * This patterns allows compile-time verification of component dependencies.
 */
@PolymorphicComponentMarker
interface PolymorphicComponent

/**
 * Special marker that forbids calling any component.
 *
 * Applying this marker as a receiver on a [`@Composable`][Composable] function forbids the usage of any [PolymorphicComponent],
 * while still allowing other composable functions (e.g. `remember`).
 * ```kotlin
 * @Composable
 * fun FooUI.Home(
 *     // all components are allowed
 *     content: @Composable () -> Unit,
 *
 *     // composable functions are allowed, but polymorphic components are not
 *     state: @Composable NoUI.() -> Unit,
 * ) {
 *     // …
 * }
 * ```
 */
@PolymorphicComponentMarker
data object NoUI : PolymorphicComponent
