package opensavvy.decouple.purecss.components.display

import androidx.compose.runtime.Composable
import opensavvy.decouple.components.display.Texts
import org.jetbrains.compose.web.dom.Text as DomText

interface Texts : Texts {

	@Composable
	override fun TextSpec(text: String) {
		DomText(text)
	}
}
