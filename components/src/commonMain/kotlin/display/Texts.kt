package opensavvy.decouple.components.display

import androidx.compose.runtime.Composable
import opensavvy.decouple.components.ExperimentalComponent
import opensavvy.decouple.polymorphism.PolymorphicComponent

/**
 * Specification for text displaying.
 */
interface Texts : PolymorphicComponent {

	/**
	 * See [Texts].
	 */
	@Composable
	fun TextSpec(text: String)
}

/**
 * Displays unformatted [text] to the user.
 */
@ExperimentalComponent
@Composable
fun Texts.Text(text: String) {
	TextSpec(text)
}
