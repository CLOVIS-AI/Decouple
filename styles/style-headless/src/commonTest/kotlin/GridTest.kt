package opensavvy.decouple.headless

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import opensavvy.decouple.core.atom.Text
import opensavvy.decouple.core.layout.Grid
import opensavvy.decouple.headless.execution.runHeadlessUI
import opensavvy.decouple.headless.layout.Grid
import kotlin.test.Test
import kotlin.test.assertEquals

class GridTest {
	@OptIn(ExperimentalCoroutinesApi::class)
	@Test
	fun counter() = runTest {
		val ui = backgroundScope.runHeadlessUI {
			Grid(0..3, 0..3) { x, y ->
				Text("$x, $y")
			}
		}

		ui.paused {
			val grid = find(Grid)
			assertEquals(16, grid.content.nodes().count())
		}
	}
}
