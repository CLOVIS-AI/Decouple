package opensavvy.decouple.demo

import androidx.compose.runtime.*
import opensavvy.decouple.components.actions.Button
import opensavvy.decouple.components.display.Text
import opensavvy.decouple.components.layout.Alignment
import opensavvy.decouple.components.layout.Column
import opensavvy.decouple.components.layout.Row
import opensavvy.decouple.demo.design.Components

// This is the project's entrypoint.
// All platform-specific code (androidMain, desktopMain…) is there to initialize the platform before this function
// can be called.
//
// This function is responsible for starting your app and triggering everything else.
// For example, you could put your navigation logic here.
@Composable
fun Components.App() = Column {
	Text("Welcome to the Decouple demo!")

	Counter()
}

// This is a regular composable function written using Decouple.
//
// Notice that it is exactly the same as a normal composable function without Decouple, but with an added 'Components'
// receiver.
//
// In the future, the receiver will be replaced by a context receiver.
@Composable
fun Components.Counter() {
	var counter by remember { mutableStateOf(0) }

	Row(alignment = Alignment.Center) {
		Button({ counter-- }) { Text("-") }
		Text("$counter")
		Button({ counter++ }) { Text("+") }
	}
	// ^ Notice that these components are *not* part of androidx.compose.
	// Here, we are calling thin abstractions over other existing components.
	// The actual implementation is delegated to the Components receiver, which allows this function to run
	// in different conditions (e.g. on platforms which don't have canvas-based rendering).

	// If you want to play a bit with this demo, don't hesitate to pause here and edit this block of code.
	// When you open the project in IntelliJ (Community or Ultimate, as you prefer), there should already be
	// a run configuration available for all projects.
}

// To learn more about how design systems are represented, open design/Components.kt.
// Tip: CTRL+click on the 'Components' class used as receiver of the previous function.
