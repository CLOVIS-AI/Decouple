package opensavvy.decouple.demo.design

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.atom.text.Text
import opensavvy.decouple.core.layout.Screen

@Composable
fun DesignOverview() = Screen("Design") {
    Text(
        "Using Decouple, we can split the codebase of the application's UI from the application's design, " +
                "making it possible to build a single application on top of multiple design systems or themes. In this " +
                "section, we explore how styles and themes interact with the other components."
    )
}
