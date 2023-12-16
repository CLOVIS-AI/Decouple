package opensavvy.decouple.material.androidx.atom

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.atom.Texts
import androidx.compose.material3.Text as M3Text

object MATexts : Texts {

	@Composable
	override fun TextSpec(text: String) {
		M3Text(text)
	}

}
