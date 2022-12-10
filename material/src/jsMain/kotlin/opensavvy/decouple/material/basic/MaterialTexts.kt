package opensavvy.decouple.material.basic

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.basic.Texts
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text as DomText

actual interface MaterialTexts : Texts {

	@Composable
	override fun Text(text: String) {
		Span { DomText(text) }
	}

}