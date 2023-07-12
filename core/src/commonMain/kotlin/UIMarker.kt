package opensavvy.decouple.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@DslMarker
annotation class UIDslMarker

/**
 * Parent interface for all component declarations.
 *
 * This interface exists as a common parent between [UIMetadata] and [NoUI]. It is mostly an implementation detail
 * of the UI DSL, and should not need to be used in regular code.
 */
@UIDslMarker
sealed interface UIMarker

/**
 * Receiver for [Composable] functions and lambdas that are not allowed to emit UI elements.
 *
 * There exist multiple composable function that do not emit UI. For example, the lambda passed to [remember] cannot
 * emit UI elements, and it is a mistake to do so. However, regular Compose cannot enforce this rule.
 *
 * Because Decouple is interface-based, it can profit from the [DslMarker] annotation. By applying [NoUI] as a receiver,
 * access to any other implementation of [UIMarker] is cut, ensuring no component can be accidentally called.
 *
 * ### Example
 *
 * ```kotlin
 * @Composable
 * fun UI.Foo() {
 *     // Can call components through the receiver
 *     Button({}) { Text("Click here") }
 *
 *     with(NoUI) {
 *         // Cannot call any component, even though we still have access to the UI receiver
 *         Button({}) { Text("Click here") } // Compile-time error
 *     }
 * }
 * ```
 */
@UIDslMarker
object NoUI : UIMarker
