package opensavvy.decouple.material.androidx.atom

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import opensavvy.decouple.core.atom.ProgressIndicators
import opensavvy.state.Progression

object MAProgressIndicators : ProgressIndicators {

	@Composable
	override fun ProgressIndicator(progress: Progression) = when (progress) {
		is Progression.Done -> Unit
		is Progression.Loading.Unquantified -> {
			CircularProgressIndicator()
		}

		is Progression.Loading.Quantified -> {
			val animatedProgress by animateFloatAsState(
				targetValue = progress.normalized.toFloat(),
				animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
			)

			CircularProgressIndicator(animatedProgress)
		}
	}

}
