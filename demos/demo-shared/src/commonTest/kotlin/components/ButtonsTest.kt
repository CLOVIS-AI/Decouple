package opensavvy.decouple.demo.components

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import opensavvy.decouple.headless.atom.actionable.Button
import opensavvy.decouple.headless.execution.runHeadlessUI
import opensavvy.progress.Progress
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class ButtonsTest {

    @Test
    fun buttons_load_when_they_are_pressed() = runTest {
        val ui = backgroundScope.runHeadlessUI { Buttons() }

        ui.paused {
            val button = find(Button)

            assertTrue(button.loading is Progress.Done)

            println("Clicking on the first button…")
            button.click()
        }

        ui.paused {
            val button = find(Button)

            assertIs<Progress.Loading>(button.loading)
        }

        delay(10_000)

        ui.paused {
            val button = find(Button)

            assertEquals(Progress.Done, button.loading)
        }
    }

}
