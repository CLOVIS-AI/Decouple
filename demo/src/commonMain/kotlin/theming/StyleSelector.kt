package opensavvy.decouple.demo.theming

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.UI
import opensavvy.decouple.core.atom.actionable.Button
import opensavvy.decouple.core.atom.text.Text
import opensavvy.decouple.core.layout.Row
import opensavvy.decouple.core.layout.Screen
import opensavvy.decouple.demo.Screen
import opensavvy.decouple.navigation.Destination

class StyleSelector(
    private val available: List<UI>,
    private val current: UI,
    private val setCurrent: (UI) -> Unit,
) : Destination {
    override val route: String get() = "style"
    override val title: String get() = "Styles"
    override val parent: Destination get() = Screen.Design

    @Composable
    override fun render() = Screen("Styles") {
        Text("Decouple provides a collection of components which your application can use.")
        Text(
            "Because your application is written using these components, " +
                    "and not by directly calling system functions, it is possible to switch which implementation is used, " +
                    "even at run-time if they share the same Compose Applier."
        )

        Text(
            "Styles are much more powerful than CSS or other traditional theming methods, " +
                    "because they can alter everything about components: their look and feel, the structure of the page, " +
                    "and even the rendering technology used. " +
                    "Using Kotlin's expect/actual mechanism, it is possible to create styles which adapt the UI to the " +
                    "curent platform automatically."
        )

        Text(
            "A single app is deployed on multiple heterogeneous platforms by delegating the UI of each platform " +
                    "to the corresponding Style implementation. For example, the only difference between the various " +
                    "versions of this demonstration application is the style used."
        )

        Text("This version of the documentation app is compatible with the following styles:")
        Row {
            for (implementation in available) {
                Button(
                    onClick = { setCurrent(implementation) },
                    enabled = implementation != current,
                ) {
                    Text(implementation.name)
                }
            }
        }
    }

}
