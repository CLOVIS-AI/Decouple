package opensavvy.decouple.demo

import androidx.compose.ui.window.ComposeUIViewController
import opensavvy.decouple.demo.design.InstallSelectedDesign

// Regular Compose for iOS initialization code.
// The only new code is the 'InstallSelectedDesign' call to initialize Decouple.
fun MainViewController() = ComposeUIViewController {
	InstallSelectedDesign {
		App()
	}
}
