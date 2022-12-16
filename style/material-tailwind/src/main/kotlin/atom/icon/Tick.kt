package opensavvy.decouple.material.tailwind.atom.icon

import androidx.compose.runtime.Composable
import opensavvy.state.Progression
import org.jetbrains.compose.web.ExperimentalComposeWebSvgApi
import org.jetbrains.compose.web.svg.Path
import org.jetbrains.compose.web.svg.Svg

@OptIn(ExperimentalComposeWebSvgApi::class)
@Composable
fun Tick(activated: Boolean, loading: Progression) {
	Svg(
		"0 0 48 48",
		{
			attr("fill", "none")
			classes("absolute", "inset-0", "h-5", "w-5", "transition-opacity", "duration-100")

			if (activated && loading !is Progression.Loading) {
				classes("opacity-100", "delay-100")
			} else {
				classes("opacity-0")
			}
		}
	) {
		Path(
			"M18.9 36 7.4 24.5l2.45-2.45 9.05 9.05 19.2-19.2 2.45 2.45Z",
			{
				attr("fill", "currentColor")
			}
		)
	}
}
