package opensavvy.ui.material.basic

import androidx.compose.runtime.Composable
import opensavvy.ui.core.basic.Texts
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text as DomText

actual interface MaterialTexts : Texts {

	@Composable
	override fun Text(text: String) {
		Span { DomText(text) }
	}

}
