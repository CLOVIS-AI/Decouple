package opensavvy.decouple.headless

import io.kotest.core.spec.style.StringSpec
import opensavvy.decouple.headless.components.actions.buttons
import opensavvy.decouple.headless.debug.spies
import opensavvy.prepared.runner.kotest.preparedSuite

class HeadlessUI : StringSpec({
	preparedSuite {
		buttons()
		spies()
	}
})
