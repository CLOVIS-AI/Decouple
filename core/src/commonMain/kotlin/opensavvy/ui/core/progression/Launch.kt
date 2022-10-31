package opensavvy.ui.core.progression

import kotlinx.coroutines.*
import opensavvy.state.Progression
import opensavvy.state.ProgressionReporter
import opensavvy.state.ProgressionReporter.Companion.report
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Launches a new coroutine without blocking the current thread and returns a reference to the coroutine as a [Job].
 * The coroutine is cancelled when the resulting job is [cancelled][Job.cancel].
 *
 * This is the primary function to execute a callback that implements the [Progression] DSL.
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
	block: suspend () -> Unit,
): Job {
	val reporter = object : ProgressionReporter() {
		override suspend fun emit(progression: Progression) {
			onProgress(progression)
		}
	}

	return launch(context + reporter, start) {
		try {
			report(Progression.loading())
			block()
		} finally {
			report(Progression.done())
		}
	}
}
