package opensavvy.decouple.headless

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import opensavvy.decouple.core.atom.actionable.Button
import opensavvy.decouple.core.atom.text.Text
import opensavvy.decouple.headless.atom.actionable.Button
import opensavvy.decouple.headless.atom.text.innerText
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

			assertEquals("Hello world!", button.icon.innerText)
			assertEquals("Count: 0", button.content.innerText)

			button.click()
		}

		ui.paused {
			val button = find(Button)

			assertEquals("Hello world!", button.icon.innerText)
			assertEquals("Count: 1", button.content.innerText)
		}
	}

}
