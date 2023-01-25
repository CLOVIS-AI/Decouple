package opensavvy.decouple.testing.execution

import androidx.compose.runtime.BroadcastFrameClock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composition
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.snapshots.Snapshot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import opensavvy.decouple.core.UI.Companion.Install
import opensavvy.decouple.testing.Component
import opensavvy.decouple.testing.TestUI

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
) {

	/**
	 * Allows the application to respond to events once.
	 *
	 * The test application is frozen in time, actions executed have no impact until this method is called.
	 */
	suspend fun recompose() {
		yield()
		Snapshot.sendApplyNotifications()
		clock.sendFrame(1)
		recomposer.awaitIdle()
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
 *         // Inside this lambda, you can use any composable that follows the Decouple structure
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
 *     // Display the entire UI hierarchy
 *     println(ui)
 *
 *     // Access the component state
 *     val text = ui.find(Text)
 *     assertEquals("Counter: 0", text.text)
 *
 *     // Trigger events
 *     ui.find(Button).click()
 *     ui.recompose() // let the UI process events
 *
 *     // Check the new state
 *     val text = ui.find(Text)
 *     assertEquals("Counter: 1", text.text)
 * }
 * ```
 */
fun CoroutineScope.compose(content: @Composable () -> Unit): TestApplication {
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

	return TestApplication(
		recomposer,
		clock,
		root,
	)
}
