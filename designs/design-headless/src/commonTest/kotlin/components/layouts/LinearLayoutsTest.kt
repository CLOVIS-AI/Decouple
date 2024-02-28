package opensavvy.decouple.headless.components.layouts

import io.kotest.matchers.shouldBe
import opensavvy.decouple.components.actions.Button
import opensavvy.decouple.components.display.Text
import opensavvy.decouple.components.layout.Row
import opensavvy.decouple.headless.prepared.headlessUI
import opensavvy.prepared.suite.SuiteDsl

fun SuiteDsl.linearLayouts() = suite("Column & Row") {
	test("Column") {
		val ui = headlessUI {
			Row {
				Text("This is a test")
				Button({}) { Text("Test") }
			}
		}.immediate("ui")

		ui.paused {
			find(Row).reverse shouldBe false
		}
	}
}
