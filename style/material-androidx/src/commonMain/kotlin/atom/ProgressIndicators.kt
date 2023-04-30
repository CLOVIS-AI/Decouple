package opensavvy.decouple.material.androidx.atom

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import opensavvy.decouple.core.atom.ProgressIndicators
import opensavvy.progress.Progress

object MAProgressIndicators : ProgressIndicators {

	@Composable
	override fun ProgressIndicator(progress: Progress) = when (progress) {
		is Progress.Done -> Unit
		is Progress.Loading.Unquantified -> {
			CircularProgressIndicator()
		}

		is Progress.Loading.Quantified -> {
			val animatedProgress by animateFloatAsState(
				targetValue = progress.normalized.toFloat(),
				animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
			)

			CircularProgressIndicator(animatedProgress)
		}
	}

}
