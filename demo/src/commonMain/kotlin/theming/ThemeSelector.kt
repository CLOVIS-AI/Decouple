package opensavvy.decouple.demo.theming

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.atom.actionable.Button
import opensavvy.decouple.core.atom.actionable.SecondaryButton
import opensavvy.decouple.core.atom.text.Text
import opensavvy.decouple.core.layout.Row
import opensavvy.decouple.core.layout.Screen
import opensavvy.decouple.core.theme.Theme
import opensavvy.decouple.demo.Screen
import opensavvy.decouple.navigation.Destination

class ThemeSelector(
    private val available: List<Theme>,
    private val recommended: List<Theme>,
    private val current: Theme,
    private val setCurrent: (Theme) -> Unit,
) : Destination {
    override val route: String get() = "theme"
    override val title: String get() = "Themes"
    override val parent: Destination get() = Screen.Design

    @Composable
    override fun render() = Screen("Themes") {
        Text(
            "It is often necessary to provide visual differences between instances of an application, " +
                    "without going as far as creating a entirely new style. For example, some users may prefer white text " +
                    "on a dark background, or you may want to adapt the colors of the application to your brand or your " +
                    "client's."
        )

        Text(
            "Themes are a set of design tokens to allow all components of the app to speak the same design " +
                    "language, even if they are created by entirely different teams. All components work in term of “the " +
                    "primary color” and not in term of hard-coded values. Artists and designers can edit the values of the " +
                    "theme without needing to know programming."
        )

        Text(
            "An application can embark as many themes as you deem necessary: you can create some yourself, or " +
                    "use some procedural generation to create new themes tailored to the user on-the-fly."
        )

        Text("This version of the documentation app is compatible with the following themes:")
        Row {
            for (theme in available) {
                if (theme in recommended) {
                    SecondaryButton(
                        onClick = { setCurrent(theme) },
                        enabled = theme != current,
                    ) {
                        Text(theme.name)
                    }
                } else {
                    Button(
                        onClick = { setCurrent(theme) },
                        enabled = theme != current,
                    ) {
                        Text(theme.name)
                    }
                }
            }
        }
        Text(
            "The highlighted themes are recommended by the style you're currently using. The other themes were " +
                    "created for other styles, and may or may not look good with the current style."
        )
    }

}
