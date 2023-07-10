package opensavvy.decouple.core.atom

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.UI
import opensavvy.progress.Progress
import opensavvy.progress.done

interface ProgressIndicators {

	@Composable
	fun ProgressIndicatorSpec(
		progress: Progress,
	)

}

/**
 * Indicates progress to the user.
 *
 * When the progress is over (see [done]), displays nothing.
 */
@Composable
fun ProgressIndicator(
	progress: Progress,
) {
	UI.current.ProgressIndicatorSpec(progress)
}
