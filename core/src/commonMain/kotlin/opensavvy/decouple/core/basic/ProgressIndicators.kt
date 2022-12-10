package opensavvy.decouple.core.basic

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.UI
import opensavvy.state.Progression

interface ProgressIndicators {

	@Composable
	fun ProgressIndicator(
		progress: Progression,
	)

}

/**
 * Indicates progress to the user.
 *
 * When the progress is over (see [Progression.Done]), displays nothing.
 */
@Composable
fun ProgressIndicator(
	progress: Progression,
) {
	UI.current.ProgressIndicator(progress)
}
