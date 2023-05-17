package opensavvy.decouple.demo.demos.calculator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import opensavvy.decouple.core.atom.actionable.Button
import opensavvy.decouple.core.atom.text.Text
import opensavvy.decouple.core.layout.Row
import opensavvy.decouple.core.layout.Screen

@Composable
fun Calculator() = Screen("Calculator") {
	val calc = remember { CalculatorViewModel() }

	val matrix = listOf(
		listOf("1", "2", "3", "+"),
		listOf("4", "5", "6", "-"),
		listOf("7", "8", "9", "*"),
		listOf("0", "AC", "=", "%"),
	)

	Text("Calculator")

	Text(calc.getResult())

	for (row in matrix) {
		Row {
			for (text in row) {
				Button(onClick = {
					calc.onClick(text)
				}) {
					Text(text)
				}
			}
		}
	}
}
