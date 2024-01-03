package opensavvy.decouple.material.tailwind.atom

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.atom.Texts
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text as DomText

object MTTexts : Texts {

	@Composable
	override fun TextSpec(text: String) {
		Span(
			{
				classes("max-w-[50em]")
			}
		) {
			DomText(text)
		}
	}

}
