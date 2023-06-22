package opensavvy.decouple.extra.html.lazy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import org.jetbrains.compose.web.dom.Div
import web.dom.document
import web.dom.observers.IntersectionObserver
import kotlin.random.Random
import kotlin.random.nextUInt

@Composable
internal fun VisibilityDetector(onVisible: () -> Unit) {
	val id = remember { "visibility-observer-" + Random.nextUInt() }

	DisposableEffect(onVisible) {
		val div = document.getElementById(id) ?: run {
			console.warn("Decouple: could not find the div with identifier $id, the lazy elements are broken")
			return@DisposableEffect onDispose { /* Nothing to do */ }
		}

		val observer = IntersectionObserver(
			callback = { entries, _ ->
				if (entries.any { it.isIntersecting }) {
					onVisible()
				}
			}
		)

		observer.observe(div)

		onDispose {
			observer.disconnect()
		}
	}

	Div({
		id(id)
	})
}
