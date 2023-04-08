package opensavvy.decouple.demo.components

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import opensavvy.decouple.headless.atom.actionable.Button
import opensavvy.decouple.headless.execution.runHeadlessUI
import opensavvy.state.Progression
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

            assertTrue(button.loading is Progression.Done)

            println("Clicking on the first button…")
            button.click()
        }

        ui.paused {
            val button = find(Button)

            assertIs<Progression.Loading>(button.loading)
        }

        delay(10_000)

        ui.paused {
            val button = find(Button)

            assertEquals(Progression.Done, button.loading)
        }
    }

}
