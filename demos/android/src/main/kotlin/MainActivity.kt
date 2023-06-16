package opensavvy.decouple.demo.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import opensavvy.decouple.demo.Demo
import opensavvy.decouple.demo.Screen
import opensavvy.decouple.material.MaterialUI
import opensavvy.decouple.navigation.ComposeNavigation

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			Demo(
				listOf(MaterialUI),
				ComposeNavigation(Screen.Home)
			)
		}
	}
}
