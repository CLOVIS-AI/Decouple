package opensavvy.decouple.headless.execution

import androidx.compose.runtime.BroadcastFrameClock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composition
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.snapshots.Snapshot
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import opensavvy.decouple.components.DesignSystem
import opensavvy.decouple.components.Install
import opensavvy.decouple.headless.Component
import opensavvy.decouple.headless.HeadlessDesignComponents
import opensavvy.decouple.headless.HeadlessDesignSystem
import opensavvy.decouple.headless.node.NodeTree

/**
 * Executes a composable function in a test environment where no UI is emitted.
 * Instead, all state is captured and wrapped into [Component] instances, making it possible to query the current
 * state of the UI and execute events.
 *
 * To build a test application, see the [runHeadlessUI] builder.
 */
class HeadlessUI internal constructor(
	private val recomposer: Recomposer,
	private val clock: BroadcastFrameClock,
	private val root: ExecutableNode,
) : NodeTree by root {

	private val executionLock = Mutex()

	// DO NOT CALL WITHOUT HOLDING THE EXECUTION LOCK
	private suspend fun recomposeUnlocked() {
		yield()
		Snapshot.sendApplyNotifications()
		yield()
		clock.sendFrame(1)
		recomposer.awaitIdle()
	}

	/**
	 * Allows the application to respond to events once.
	 *
	 * The test application is frozen in time, actions executed have no impact until this method is called.
	 */
	suspend fun recompose() = executionLock.withLock("recompose") {
		recomposeUnlocked()
	}

	fun CoroutineScope.recomposeAutomatically(delayMillis: Long = 100) = launch {
		while (true) {
			executionLock.withLock("recomposeAutomatically") { recomposeUnlocked() }
			delay(delayMillis)
		}
	}

	/**
	 * Executes [block] while the application is paused.
	 *
	 * Inside this block,
	 */
	suspend fun paused(block: suspend HeadlessUI.() -> Unit) {
		println(this)

		executionLock.withLock("paused") {
			block()

			recomposeUnlocked()
		}
	}

	override fun toString() = root.toString()
}

/**
 * Executes [content] in a test environment where no UI is emitted.
 * Instead, all state is captured and wrapped into [Component] instances, making it possible to query the current
 * state of the UI and execute events.
 *
 * This overload allows to plug a different [design] into the headless machinery.
 * If you want to reuse the existing headless component implementations, make sure to implement [HeadlessDesignComponents].
 */
fun <D : DesignSystem> CoroutineScope.runHeadlessUI(
	design: D,
	manualRecomposition: Boolean = false,
	content: @Composable D.() -> Unit,
): HeadlessUI {
	val clock = BroadcastFrameClock()

	val root = ExecutableNode("Root", isSlot = true)
	val applier = root.Applier()
	val recomposer = Recomposer(coroutineContext + clock)
	val composition = Composition(applier, recomposer)

	coroutineContext.job.invokeOnCompletion {
		composition.dispose()
		recomposer.cancel()
	}

	launch(clock) {
		recomposer.runRecomposeAndApplyChanges()
	}

	composition.setContent {
		Install(design) {
			content()
		}
	}

	val application = HeadlessUI(
		recomposer,
		clock,
		root,
	)

	if (!manualRecomposition) {
		launch { with(application) { recomposeAutomatically() } }
	}

	return application
}

/**
 * Executes [content] in a test environment where no UI is emitted.
 * Instead, all state is captured and wrapped into [Component] instances, making it possible to query the current
 * state of the UI and execute events.
 *
 * ### Example usage with KotlinX.coroutines.test
 *
 * ```
 * @Test
 * fun yourTest() = runTest {
 *     // runTest is necessary to suspend in tests: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-test/kotlinx.coroutines.test/run-test.html
 *
 *     // Declare our UI
 *     // You should either use backgroundScope, or create your own scope that you later cancel to stop the application
 *     val ui = backgroundScope.runHeadlessUI {
 *         // Inside this lambda, any composable that follows the Decouple structure can be called.
 *         // It's not possible to test other UI-emitting composables, for example those from Jetpack Compose
 *         // or Compose for Web, as they are hard-coded to build real UI trees.
 *
 *         // Non-UI emitting composables (remember, animate…) can be used here even if they do not originate from
 *         // a Decouple-based project.
 *         var counter by remember { mutableStateOf(0) }
 *
 *         Button(onClick = { counter++ }) {
 *             Text("Counter: $count")
 *         }
 *     }
 *
 *     // Print the entire UI hierarchy
 *     println(ui)
 *
 *     // By default, the UI is running asynchronously for the duration of the test
 *     // To ensure our assertions do not impact each other, we must temporarily pause it
 *     ui.paused {
 *         // We can now assert the state of the components
 *         assertEquals("Counter: 0", find(Text).text)
 *
 *         // We can also trigger events
 *         find(Button).click()
 *         // Because the UI is currently paused, the events are queued but not executed yet.
 *         assertEquals("Counter: 0", find(Text).text)
 *     }
 *
 *     // The UI is now running, the queued events are being executed
 *
 *     // Let's pause it again to check the results…
 *     ui.paused {
 *         assertEquals("Counter: 1", find(Text).text)
 *     }
 * }
 * ```
 *
 * ### Manual recomposition
 *
 * By default, the application is running between [paused][HeadlessUI.paused] calls.
 * For more fine-tuned control over the execution, set the optional parameter [manualRecomposition] to `true`.
 *
 * In that case, the application will be stopped by default.
 * To execute it step-by-step, call [recompose][HeadlessUI.recompose].
 * To start it, call [recomposeAutomatically][HeadlessUI.recomposeAutomatically].
 */
fun CoroutineScope.runHeadlessUI(
	manualRecomposition: Boolean = false,
	content: @Composable HeadlessDesignComponents.() -> Unit,
): HeadlessUI = runHeadlessUI(HeadlessDesignSystem, manualRecomposition, content)
