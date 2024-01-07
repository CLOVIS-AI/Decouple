package opensavvy.decouple.headless.components.actions

import io.kotest.matchers.shouldBe
import opensavvy.decouple.components.actions.Button
import opensavvy.decouple.components.display.Text
import opensavvy.decouple.headless.prepared.headlessUI
import opensavvy.prepared.suite.SuiteDsl

fun SuiteDsl.buttons() = suite("Buttons") {

	test("onClick is called") {
		var clicked = false

		val ui = headlessUI {
			Button({ clicked = true }) {
				Text("Click me")
			}
		}.immediate()

		ui.paused {
			find(Button).click()
		}

		clicked shouldBe true
	}

	test("The icon is present") {
		val ui = headlessUI {
			Button({ }, icon = { Spy(Unit) }) {}
		}.immediate()

		ui.paused {
			find(Button).icon.present shouldBe true
			find(Button).icon.absent shouldBe false
		}
	}

	test("The icon is absent") {
		val ui = headlessUI {
			Button({ }) {}
		}.immediate()

		ui.paused {
			find(Button).icon.absent shouldBe true
			find(Button).icon.present shouldBe false
		}
	}

}
