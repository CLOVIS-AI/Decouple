package opensavvy.decouple.demo.design

import androidx.compose.runtime.*
import opensavvy.decouple.components.Install
import opensavvy.decouple.components.actions.ActionButton
import opensavvy.decouple.components.display.Text
import opensavvy.decouple.components.layout.Column
import opensavvy.decouple.components.layout.Row

// By default, Composable functions cannot call Decouple components: they require the Components receiver.
// However, supplying the receiver manually (like we would do for regular extension functions) is not enough:
// some design systems need to initialize the app before working (e.g. creating CSS variables…).
//
// To do this, you should call the 'Install' function:
//     Install(TheDesignSystemYouWantToUse) {
//         // Here, you can call Decouple components.
//     }

// For this demo, we have decided to let the user select between multiple design systems at run-time.
// This composable function is responsible for loading the design systems available on this platform,
// and selecting one, before calling 'Install'.
//
// If you are adapting this demo, and only use a single design system per platform, you can remove this function
// and call 'Install' directly when starting the app.
@Composable
fun InstallSelectedDesign(block: @Composable Components.() -> Unit) {
	val designSystems = remember { designSystems() }

	var currentDesignSystem by remember { mutableStateOf(designSystems.first()) }

	Install(currentDesignSystem) {
		Column {
			if (designSystems.size > 1) {
				Row {
					for (designSystem in designSystems) {
						ActionButton(onClick = { currentDesignSystem = designSystem }) {
							Text(designSystem.name)
						}
					}
				}
			}

			block()
		}
	}
}

// To learn more about how each platform is initialized, see the places where InstallSelectedDesign is called.
// Tip: CTRL+click on the function name in IntelliJ.
