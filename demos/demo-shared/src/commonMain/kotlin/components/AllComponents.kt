package opensavvy.decouple.demo.components

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.atom.text.Text
import opensavvy.decouple.core.layout.Screen

@Composable
fun Components() = Screen("Components") {
	Text("The presentation of the various available components.")
}
