package opensavvy.ui.core.progression

import androidx.compose.runtime.snapshots.Snapshot
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Simple DSL for reporting progress throughout an asynchronous operation.
 *
 * For more information on progression and task responsibility, see [Progression].
 *
 * Components that do not account for task responsibility, as can be seen in other UI frameworks, often accept callback
 * parameters of a type similar to `() -> Unit`. For example:
 * ```kotlin
 * Button(
 *     onClick = { println("Clicked") } // synchronous callback
 * ) { Text("Click here!") }
 * ```
 * If we wanted to launch an asynchronous operation from this callback, we would have to manage task cancellation and
 * updating the UI to display some kind of loading spinner.
 *
 * Instead, the button is marked as responsible for the task.
 * The button manages all these details for us: if the button is removed from the screen, the operation is cancelled.
 * As long as the operation is running, the button displays some loading indicator (depending on the UI implementation).
 * For example, some implementation could display a loading spinner instead of the button icon, or even display a global
 * notification.
 *
 * This is done by accepting a parameter of type `suspend ReportProgression.() -> Unit`:
 * - `suspend` means the callback is allowed to suspend on asynchronous operations,
 * - `ReportProgression` (this type alias) allows the callback to report its current state to the component, so it can
 * update its indicator.
 *
 * ### Usage
 *
 * Example of progression reporting:
 * ```kotlin
 * Button(
 *     onClick = {
 *         loading(0)  // the operation is 0% done
 *         val a = someLongRunningOperation()
 *
 *         loading(50) // the operation is 50% done
 *         otherLongOperation(a)
 *
 *         done()      // the operation is done
 *     }
 * ) { Text("Click here!") }
 * ```
 * For more detailed information, see [loading] and [done].
 *
 * All buttons automatically insert the loading state at the start of the operation and the done state at the end
 * ([more information][launch]).
 * These two examples are therefore identical:
 * ```kotlin
 * Button(
 *     onClick = {
 *         loading()     // optional
 *         doSomething()
 *         done()        // optional
 *     }
 * ) { Text("Click here!") }
 * ```
 * ```kotlin
 * Button(
 *     onClick = {
 *         doSomething()
 *     }
 * ) { Text("Click here!") }
 * ```
 * If you have no way of knowing what the current progress is, you can simply omit all progression calls.
 *
 * ### Navigation tasks cancelling themselves
 *
 * Because the component is responsible for the long-running operation, it will be cancelled if the component is removed
 * from the screen.
 * This is useful to cancel useless work if the user navigates away.
 * However, if one of the button's actions is to navigate away, it may cancel itself, and subsequent operations will not be executed:
 * ```kotlin
 * Button(
 *     onClick = {
 *         doSomething()
 *         navigateTo(otherScreen) // Here, the screen recomposes.
 *         // The button is not displayed anymore.
 *         // The button was responsible for this task, so this task is stopped.
 *         doSomethingElse()       // This is never executed!
 *     }
 * ) { Text("Click here!") }
 * ```
 *
 * One solution is to ensure navigation is always the last operation.
 * Another solution is to manage the task yourself (see the 'Opting out' section below).
 * Another solution is to isolate the task from the rest of the application using [Snapshot]:
 * ```kotlin
 * Button(
 *     onClick = {
 *         Snapshot.withMutableSnapshot {
 *             doSomething()
 *             navigateTo(otherScreen)
 *             doSomethingElse()
 *         }
 *     }
 * ) { Text("Click here!") }
 * ```
 * Everything in the [withMutableSnapshot][Snapshot.withMutableSnapshot] block is isolated from any other Composed-managed
 * state. All operations in this block that set the state only impact the application after the block is over.
 * Note that suspension is forbidden inside `withMutableSnapshot` (so you cannot run asynchronous operations, including the functions `loading` and `done`).
 *
 * ### Opting out
 *
 * All synchronous operations are valid asynchronous operations.
 * If you want to start a synchronous operation, you can simply give the callback you would have used without task
 * responsibility.
 *
 * Sometimes, we want the button to start an operation that _another component_ is responsible for (for example,
 * a refresh button may start an operation that the entire page is responsible for).
 * The responsibility is recognized as the lifetime of the started coroutine.
 * To opt out, you can simply start another independent coroutine with its own lifetime:
 * ```kotlin
 * val scope: CoroutineScope = /* … */
 * Button(
 *     onClick = {
 *         scope.launch {  // start another independent coroutine managed by 'scope', not by the button
 *             delay(1000) // wait 1 second
 *             println("Done.")
 *         }
 *     }
 * ) { Text("Click here!") }
 * ```
 * In the above example, the button is responsible for starting the other task, but not for running that other task.
 * Do not forget to give some kind of feedback to the user about the state of the task, since the button will not!
 */
typealias ReportProgression = FlowCollector<Progression>

/**
 * Launches a new coroutine without blocking the current thread and returns a reference to the coroutine as a [Job].
 * The coroutine is cancelled when the resulting job is [cancelled][Job.cancel].
 *
 * This is the primary function to execute a callback that implements the [ReportProgression] DSL.
 * See its documentation for an introduction on the concepts.
 *
 * This function mimics the standard [kotlinx.coroutines.launch] function.
 * The only difference is that it calls the [onProgress] lambda each time the [block] coroutine emits some loading
 * information.
 * The parameters [context] and [start] are identical to their standard equivalent.
 */
fun CoroutineScope.launch(
	onProgress: (Progression) -> Unit,
	context: CoroutineContext = EmptyCoroutineContext,
	start: CoroutineStart = CoroutineStart.DEFAULT,
	block: suspend ReportProgression.() -> Unit,
) = launch(context, start) {
	try {
		onProgress(Progression.Loading.Unquantified)

		flow { block() }
			.collect { onProgress(it) }
	} finally {
		onProgress(Progression.Done)
	}
}

//region DSL

/**
 * Reports that the current operation is [done][Progression.Done].
 *
 * This function is part of the [ReportProgression] DSL, see its documentation for usage examples.
 */
suspend fun ReportProgression.done() {
	emit(Progression.Done)
}

/**
 * Reports that the current operation is [loading with unknown progress][Progression.Loading.Unquantified].
 *
 * This function is part of the [ReportProgression] DSL, see its documentation for usage examples.
 */
suspend fun ReportProgression.loading() {
	emit(Progression.Loading.Unquantified)
}

/**
 * Reports that the current operation is [loading][Progression.Loading.Quantified] with a [progress] in `0.0..1.0`.
 *
 * This function is part of the [ReportProgression] DSL, see its documentation for usage examples.
 */
suspend fun ReportProgression.loading(progress: Double) {
	emit(Progression.Loading.Quantified(progress))
}

/**
 * Reports that the current operation is [loading][Progression.Loading.Quantified] with a [progress] in `0..100`.
 *
 * This function is part of the [ReportProgression] DSL, see its documentation for usage examples.
 */
suspend fun ReportProgression.loading(progress: Int) {
	emit(Progression.Loading.Quantified(progress / 100.0))
}

//endregion
