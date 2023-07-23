package opensavvy.decouple.demo

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.atom.Text
import opensavvy.decouple.core.layout.Screen

@Composable
fun GettingStarted() = Screen("Getting started") {
	Text("Welcome to OpenSavvy Decouple!")
	Text("In the future, this page will explain how to install the project.")
}
