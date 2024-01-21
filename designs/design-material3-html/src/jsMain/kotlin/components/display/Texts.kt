package opensavvy.decouple.material3.html.components.display

import androidx.compose.runtime.Composable
import opensavvy.decouple.components.display.Texts
import org.jetbrains.compose.web.dom.Text

interface Texts : Texts {

	@Composable
	override fun TextSpec(text: String) {
		Text(text)
	}
}
