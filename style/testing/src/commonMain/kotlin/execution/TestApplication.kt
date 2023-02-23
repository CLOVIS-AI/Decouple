package opensavvy.decouple.testing.execution

import androidx.compose.runtime.BroadcastFrameClock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composition
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.snapshots.Snapshot
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import opensavvy.decouple.core.UI.Companion.Install
import opensavvy.decouple.testing.Component
import opensavvy.decouple.testing.TestUI
import opensavvy.decouple.testing.node.NodeTree
import opensavvy.logger.Logger.Companion.trace
import opensavvy.logger.loggerFor

/**
 * Executes a composable function in a test environment where no UI is emitted.
 * Instead, all state is captured and wrapped into [Component] instances, making it possible to query the current
 * state of the UI and execute events.
 *
 * To build a test application, see the [compose] builder.
 */
class TestApplication internal constructor(
	private val recomposer: Recomposer,
	private val clock: BroadcastFrameClock,
	private val root: ExecutableNode,
) : NodeTree by root {

	private val log = loggerFor(this)
	private val executionLock = Mutex()

	// DO NOT CALL WITHOUT HOLDING THE EXECUTION LOCK
	private suspend fun recomposeUnlocked() {
		log.trace { "The test application is recomposing…" }
		yield()
		Snapshot.sendApplyNotifications()
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
	suspend fun paused(block: suspend TestApplication.() -> Unit) {
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
 * An application started this way is stopped by default, giving the test code full control over when to apply changes
 * and respond to events.
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
 *     val ui = backgroundScope.compose {
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
 * By default, the application is running between [paused][TestApplication.paused] calls.
 * For more fine-tuned control over the execution, set the optional parameter [manualRecomposition] to `true`.
 *
 * In that case, the application will be stopped by default.
 * To execute it step-by-step, call [recompose][TestApplication.recompose].
 * To start it, call [recomposeAutomatically][TestApplication.recomposeAutomatically].
 */
fun CoroutineScope.compose(
	manualRecomposition: Boolean = false,
	content: @Composable () -> Unit,
): TestApplication {
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
		Install(TestUI) {
			content()
		}
	}

	val application = TestApplication(
		recomposer,
		clock,
		root,
	)

	if (!manualRecomposition) {
		launch { with(application) { recomposeAutomatically() } }
	}

	return application
}
