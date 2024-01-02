package opensavvy.decouple.material3.androidx.components.display

import androidx.compose.runtime.Composable
import opensavvy.decouple.components.display.Texts
import androidx.compose.material3.Text as Material3Text

interface Texts : Texts {

	@Composable
	override fun TextSpec(text: String) {
		Material3Text(text)
	}
}
