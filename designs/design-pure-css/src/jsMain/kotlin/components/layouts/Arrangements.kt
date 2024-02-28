package opensavvy.decouple.purecss.components.layouts

import opensavvy.decouple.components.layout.Alignment
import opensavvy.decouple.components.layout.Arrangement
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.justifyContent

internal fun StyleScope.applyArrangement(arrangement: Arrangement) {
	when (arrangement) {
		Arrangement.Stretch -> justifyContent(JustifyContent.Stretch)
		Arrangement.Start -> justifyContent(JustifyContent.Start)
		Arrangement.Center -> justifyContent(JustifyContent.Center)
		Arrangement.End -> justifyContent(JustifyContent.End)
		Arrangement.SpaceBetween -> justifyContent(JustifyContent.SpaceBetween)
		Arrangement.SpaceAround -> justifyContent(JustifyContent.SpaceAround)
		Arrangement.SpaceEvenly -> justifyContent(JustifyContent.SpaceEvenly)
	}
}

internal fun StyleScope.applyAlignment(alignment: Alignment) {
	when (alignment) {
		Alignment.Stretch -> alignItems("stretch")
		Alignment.Start -> alignItems("start")
		Alignment.Center -> alignItems("center")
		Alignment.End -> alignItems("end")
	}
}
