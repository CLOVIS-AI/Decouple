package opensavvy.decouple.material.androidx.atom

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import opensavvy.decouple.core.atom.ProgressIndicators
import opensavvy.progress.Progress

object MAProgressIndicators : ProgressIndicators {
	private val STROKE_WIDTH = 2.dp

	@Composable
	override fun ProgressIndicatorSpec(progress: Progress) = when (progress) {
		is Progress.Done -> Unit
		is Progress.Loading.Unquantified -> {
			CircularProgressIndicator(
				Modifier.size(currentFontSize()),
				strokeWidth = STROKE_WIDTH,
			)
		}

		is Progress.Loading.Quantified -> {
			val animatedProgress by animateFloatAsState(
				targetValue = progress.normalized.toFloat(),
				animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
			)

			CircularProgressIndicator(
				animatedProgress,
				Modifier.size(currentFontSize()),
				strokeWidth = STROKE_WIDTH,
			)
		}
	}

	@Composable
	private fun currentFontSize(): Dp = with(LocalDensity.current) {
		LocalTextStyle.current.fontSize.toDp()
	}
}
