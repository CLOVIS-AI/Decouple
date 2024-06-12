package opensavvy.decouple.demo.design

import opensavvy.decouple.material3.androidx.Material3
import kotlin.experimental.ExperimentalObjCRefinement

// In the common code, we declared the 'Components' interface, which declares all components we want to use.
// Each platform is responsible for providing all necessary implementations.

// We can declare an implementation as an object that inherits from our 'Components' interface.
// We can reuse one of the design systems provided by the Decouple project as a default implementation for most components.
@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC // Workaround for https://github.com/JetBrains/compose-multiplatform/issues/4777
object Material3 : Components, Material3 {

	// Here, we can override any component to change its style in the entire application
	// (if you do, don't forget the @Composable annotation!).

	// @Composable
	// override fun TextSpec(text: String) {
	// 	// Change all texts throughout the entire application, when this design system is selected
	// 	super.TextSpec("$text :)")
	// }
}
