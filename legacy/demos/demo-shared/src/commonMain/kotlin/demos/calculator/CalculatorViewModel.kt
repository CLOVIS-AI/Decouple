package opensavvy.decouple.demo.demos.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class CalculatorViewModel {
	private var result: Int? by mutableStateOf(null)
	private var number: Int? by mutableStateOf(null)
	private var operation: String by mutableStateOf("")
	private val operators = listOf("+", "-", "*", "%")

	private fun reset() {
		result = 0
		number = null
		operation = ""
	}

	private fun equal() {
		if (result == null || number == null || operation == "") {
			return
		} else {
			val numberCopy = number as Int
			result = when (operation) {
				"+" -> result?.plus(numberCopy)
				"-" -> result?.minus(numberCopy)
				"*" -> result?.times(numberCopy)
				"%" -> result?.div(numberCopy)
				else -> result
			}
			operation = ""
			number = null
		}
	}

	private fun addNumber(numberToAdd: Int) {
		if (result == null) {
			result = numberToAdd
		} else if (operation == "") {
			result = result!! * 10 + numberToAdd
		} else {
			number = if (number == null) {
				numberToAdd
			} else {
				(number!! * 10) + numberToAdd
			}
		}
	}

	private fun operator(operator: String) {
		if (result == null) {
			return
		} else {
			operation = operator
		}
	}

	fun onClick(value: String) {
		when (value) {
			"AC" -> reset()
			"=" -> equal()
			else -> {
				if (operators.contains(value)) {
					operator(value)
				} else {
					addNumber(value.toInt())
				}
			}
		}
	}

	fun getResult(): String = result.toString()
}
