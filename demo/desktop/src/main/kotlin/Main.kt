import androidx.compose.ui.window.singleWindowApplication
import opensavvy.decouple.demo.Demo
import opensavvy.decouple.demo.Screen
import opensavvy.decouple.material.MaterialUI
import opensavvy.decouple.navigation.ComposeNavigation

fun main() = singleWindowApplication {
    Demo(listOf(MaterialUI), ComposeNavigation(Screen.Home))
}
