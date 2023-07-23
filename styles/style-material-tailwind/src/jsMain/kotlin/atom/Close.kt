package opensavvy.decouple.material.tailwind.atom

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.ExperimentalComposeWebSvgApi
import org.jetbrains.compose.web.svg.Path
import org.jetbrains.compose.web.svg.Svg

@OptIn(ExperimentalComposeWebSvgApi::class)
@Composable
fun Close() {
	Svg(
		"0 0 48 48",
		{
			attr("fill", "none")
			classes("h-5", "w-5", "transition")
		}
	) {
		Path(
			"m12.45 37.95-2.4-2.4L21.6 24 10.05 12.45l2.4-2.4L24 21.6l11.55-11.55 2.4 2.4L26.4 24l11.55 11.55-2.4 2.4L24 26.4Z",
			{
				attr("fill", "currentColor")
			}
		)
	}
}
