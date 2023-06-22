package opensavvy.decouple.extra.html.lazy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.w3c.dom.HTMLDivElement

@Composable
fun LazyColumn(
	attrs: AttrBuilderContext<HTMLDivElement>? = null,
	block: LazyDsl.() -> Unit,
) {
	val dsl = remember(block) { LazyDsl().also(block) }

	LazyLinearLayout(dsl) {
		style {
			display(DisplayStyle.Flex)
			flexDirection(FlexDirection.Column)
		}

		attrs?.invoke(this)
	}
}

@Composable
fun LazyRow(
	attrs: AttrBuilderContext<HTMLDivElement>? = null,
	block: LazyDsl.() -> Unit,
) {
	val dsl = remember(block) { LazyDsl().also(block) }

	LazyLinearLayout(dsl) {
		style {
			display(DisplayStyle.Flex)
			flexDirection(FlexDirection.Row)
		}

		attrs?.invoke(this)
	}
}
