package opensavvy.decouple.extra.html.lazy

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.Snapshot
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement
import kotlin.math.max

@Composable
internal fun LazyLinearLayout(
	dsl: LazyDsl,
	attrs: AttrBuilderContext<HTMLDivElement>? = null,
) {
	val elements = dsl.elements

	var nextIndex by remember { mutableStateOf(0) }
	val items = remember { mutableStateListOf<LazyItem>() }

	val visibilityDetector = remember(nextIndex, elements) {
		{
			if (nextIndex <= elements.lastIndex) {
				Snapshot.withMutableSnapshot {
					items += elements[nextIndex]()
					nextIndex++
				}
			}
		}
	}

	Div(attrs) {
		if (items.isEmpty()) {
			VisibilityDetector(visibilityDetector)
		}

		for ((i, item) in items.withIndex()) {
			Div {
				// Add the visibility detector as part of an element to avoid duplicate margins
				if (i == max(0, items.size - 5)) {
					VisibilityDetector(visibilityDetector)
				}

				key(item.key) {
					item.block()
				}
			}
		}
	}
}
