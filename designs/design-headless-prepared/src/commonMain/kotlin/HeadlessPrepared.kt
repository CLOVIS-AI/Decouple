package opensavvy.decouple.headless.prepared

import androidx.compose.runtime.Composable
import opensavvy.decouple.components.DesignSystem
import opensavvy.decouple.headless.Component
import opensavvy.decouple.headless.HeadlessDesignComponents
import opensavvy.decouple.headless.execution.HeadlessUI
import opensavvy.decouple.headless.execution.runHeadlessUI
import opensavvy.prepared.suite.PreparedProvider
import opensavvy.prepared.suite.backgroundScope
import opensavvy.prepared.suite.prepared
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Executes [content] in a test environment where no UI is emitted. Instead, all state is captured and wrapper into
 * [Component] instances, making it possible to query the current state of the UI and execute events.
 *
 * This overload allows to plug a different [design] into the headless machinery.
 * If you want to reuse the existing headless component implementations, make sure to implement [HeadlessDesignComponents].
 *
 * @see runHeadlessUI Learn more about the headless machinery.
 */
fun <D : DesignSystem> headlessUI(
	design: D,
	context: CoroutineContext = EmptyCoroutineContext,
	manualRecomposition: Boolean = false,
	content: @Composable (D.() -> Unit),
): PreparedProvider<HeadlessUI> = prepared(context) {
	backgroundScope.runHeadlessUI(design, manualRecomposition, content)
}

/**
 * Executes [content] in a test environment where no UI is emitted. Instead, all state is captured and wrapper into
 * [Component] instances, making it possible to query the current state of the UI and execute events.
 *
 * ### Example
 *
 * ```kotlin
 * val ui by headlessUI {
 *     // Declare your UI
 *     // Any composable that follows the Decouple structure can be called.
 *     // It's not possible to test other UI-emitting composables,
 *     // for example those from Jetpack Compose or Compose for Web,
 *     // as they are hard-coded to build real UI trees.
 *     // Non-UI emitting composables (remember, animate…) can be used even
 *     // if they do not originate from a Decouple-based project.
 *     var counter by remember { mutableStateOf(0) }
 *
 *     Button(onClick = { counter++ }) {
 *         Text("Counter: $count")
 *     }
 * }
 *
 * test("A Prepared test") {
 *     val ui = ui() // Prepare the UI for this test
 *
 *     // Print the entire UI hierarchy
 *     println(ui)
 *
 *     // By default, the UI is running asynchronously for the duration of the test.
 *     // To ensure our assertions do not impact each other, we must temporarily pause
 *     // the event handler.
 *     ui.paused {
 *         // This example uses the Kotest assertions,
 *         // but you can use any assertion framework.
 *         find(Text).text shouldBe "Counter: 0"
 *
 *         // We can also trigger events
 *         find(Button).click()
 *         // Because the UI is currently paused,
 *         // the events are queued but not executed yet.
 *         find(Text).text shouldBe "Counter: 0"
 *     }
 *
 *     // The UI is now running, the queued events are being executed.
 *
 *     // Let's pause it again to check the results…
 *     ui.paused {
 *         find(Text).text shouldBe "Counter: 1"
 *     }
 * }
 * ```
 *
 * @see runHeadlessUI Learn more about the headless machinery.
 */
fun headlessUI(
	context: CoroutineContext = EmptyCoroutineContext,
	manualRecomposition: Boolean = false,
	content: @Composable (HeadlessDesignComponents.() -> Unit),
): PreparedProvider<HeadlessUI> = prepared(context) {
	backgroundScope.runHeadlessUI(manualRecomposition, content)
}
