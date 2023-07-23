package opensavvy.decouple.demo.demos.calculator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import opensavvy.decouple.core.atom.Button
import opensavvy.decouple.core.atom.Text
import opensavvy.decouple.core.layout.Box
import opensavvy.decouple.core.layout.Grid
import opensavvy.decouple.core.layout.Screen

@Composable
fun Calculator() = Screen("Calculator") {
	val calc = remember { CalculatorViewModel() }

	val matrix = listOf(
		listOf("1", "2", "3", "+"),
		listOf("4", "5", "6", "-"),
		listOf("7", "8", "9", "*"),
		listOf("AC", "0", "=", "%"),
	)

	Text("Calculator")

	Text(calc.getResult())

	Box {
		Grid(matrix) {
			Button(onClick = {
				calc.onClick(it)
			}) {
				Text(it)
			}
		}
	}
}
