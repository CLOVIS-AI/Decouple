package opensavvy.decouple.material.tailwind.atom

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.atom.ProgressIndicators
import opensavvy.progress.Progress
import org.jetbrains.compose.web.ExperimentalComposeWebSvgApi
import org.jetbrains.compose.web.svg.Circle
import org.jetbrains.compose.web.svg.Path
import org.jetbrains.compose.web.svg.Svg

object MTProgressIndicators : ProgressIndicators {

	@OptIn(ExperimentalComposeWebSvgApi::class)
	@Composable
	override fun ProgressIndicatorSpec(progress: Progress) {
		Svg(
			"0 0 24 24",
			{
				classes("animate-spin")
				attr("fill", "none")
				classes("absolute", "inset-0", "h-5", "w-5", "transition-opacity", "duration-100")

				if (progress is Progress.Loading) {
					classes("opacity-100", "delay-100")
				} else {
					classes("opacity-0")
				}
			}
		) {
			Circle(
				12,
				12,
				10,
				{
					classes("opacity-25")
					attr("stroke", "currentColor")
					attr("stroke-width", "4")
				}
			)
			Path(
				"M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z",
				{
					classes("opacity-75")
					attr("fill", "currentColor")
				}
			)
		}
	}

}
