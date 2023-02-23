package opensavvy.decouple.testing

import androidx.compose.runtime.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import opensavvy.decouple.core.atom.actionable.Button
import opensavvy.decouple.core.atom.text.Text
import opensavvy.decouple.testing.atom.actionable.Button
import opensavvy.decouple.testing.atom.text.Text
import opensavvy.decouple.testing.execution.compose
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ExecutionTest {

	@Test
	fun counter() = runTest {
		val ui = backgroundScope.compose {
			var count by remember { mutableStateOf(0) }

			Button(onClick = { count++ }, icon = { Text("Hello world!") }) {
				Text("Count: $count")
			}
		}

		ui.paused {
			val button = find(Button)

			assertEquals("Hello world!", button.icon[Text].text)
			assertEquals("Count: 0", button.content[Text].text)

			button.click()
		}

		ui.paused {
			val button = find(Button)

			assertEquals("Hello world!", button.icon[Text].text)
			assertEquals("Count: 1", button.content[Text].text)
		}
	}

}
