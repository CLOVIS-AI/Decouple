package opensavvy.decouple.components


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import opensavvy.progress.Progress
import opensavvy.progress.coroutines.asCoroutineContext
import opensavvy.progress.coroutines.report
import opensavvy.progress.done
import opensavvy.progress.loading
import opensavvy.progress.report.ProgressReporter
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Remember a [Progress] value, [done] by default.
 *
 * Often used with [launch].
 *
 * @see remember
 */
@Composable
fun rememberProgress() = remember { mutableStateOf<Progress>(done()) }

/**
 * Launches a new coroutine without blocking the current thread and returns a reference to the coroutine as a [Job].
 * The coroutine is cancelled when the resulting job is [cancelled][Job.cancel].
 *
 * This is the primary function to execute a callback that implements the [Progress] DSL.
 * See its documentation for an introduction on the concepts.
 *
 * This function mimics the standard [kotlinx.coroutines.launch] function.
 * The only difference is that it calls the [onProgress] lambda each time the [block] coroutine emits some loading
 * information.
 * The parameters [context] and [start] are identical to their standard equivalent.
 */
fun CoroutineScope.launch(
	onProgress: (Progress) -> Unit,
	context: CoroutineContext = EmptyCoroutineContext,
	start: CoroutineStart = CoroutineStart.DEFAULT,
	block: suspend () -> Unit,
): Job {
	val reporter = ProgressReporter { onProgress(it) }

	return launch(context + reporter.asCoroutineContext(), start) {
		try {
			report(loading())
			block()
		} finally {
			report(done())
		}
	}
}
