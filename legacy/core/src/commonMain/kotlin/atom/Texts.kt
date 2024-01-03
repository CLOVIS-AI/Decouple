package opensavvy.decouple.core.atom

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.UI

interface Texts {

	/**
	 * Basic text.
	 */
	@Composable
	fun TextSpec(text: String)

}

/**
 * Basic text.
 *
 * For more information, see [Texts.TextSpec].
 */
@Composable
fun Text(text: String) =
	UI.current.TextSpec(text)
