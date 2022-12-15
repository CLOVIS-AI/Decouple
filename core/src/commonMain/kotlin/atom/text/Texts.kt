package opensavvy.decouple.core.atom.text

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.UI

interface Texts {

	/**
	 * Basic text.
	 */
	@Composable
	fun Text(text: String)

}

/**
 * Basic text.
 *
 * For more information, see [Texts.Text].
 */
@Composable
fun Text(text: String) =
	UI.current.Text(text)
