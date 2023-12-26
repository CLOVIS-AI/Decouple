package opensavvy.decouple.headless.debug

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import io.kotest.matchers.shouldBe
import opensavvy.decouple.components.actions.Button
import opensavvy.decouple.headless.components.actions.Button
import opensavvy.decouple.headless.prepared.headlessUI
import opensavvy.prepared.suite.SuiteDsl

fun SuiteDsl.spies() = suite("Spies") {
	val ui by headlessUI {
		var counter by remember { mutableStateOf(0) }

		Button(onClick = { counter++ }) {
			Spy(counter)
		}
	}

	test("The spy exposes its data") {
		val ui = ui()

		ui.paused {
			find(Button).click()
		}

		ui.paused {
			// Read the value from the headless tree
			find(Button).content.spy<Int>() shouldBe 1
		}
	}
}
