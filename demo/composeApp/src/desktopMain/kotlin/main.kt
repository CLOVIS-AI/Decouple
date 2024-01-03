package opensavvy.decouple.demo

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import opensavvy.decouple.components.Install
import opensavvy.decouple.demo.design.InstallSelectedDesign
import opensavvy.decouple.demo.design.Material3

// Regular Compose for Desktop initialization code.
// The only new code is the 'InstallSelectedDesign' call to initialize Decouple.
fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Decouple demo") {
        InstallSelectedDesign {
            App()
        }
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    Install(Material3) {
        App()
    }
}
