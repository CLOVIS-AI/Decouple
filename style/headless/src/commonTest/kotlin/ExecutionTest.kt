package opensavvy.decouple.headless

import androidx.compose.runtime.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import opensavvy.decouple.core.atom.actionable.Button
import opensavvy.decouple.core.atom.text.Text
import opensavvy.decouple.headless.atom.actionable.Button
import opensavvy.decouple.headless.atom.text.Text
import opensavvy.decouple.headless.execution.runHeadlessUI
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ExecutionTest {

	@Test
	fun counter() = runTest {
		val ui = backgroundScope.runHeadlessUI {
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
