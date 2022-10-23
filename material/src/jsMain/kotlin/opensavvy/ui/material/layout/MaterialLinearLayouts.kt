package opensavvy.ui.material.layout

import androidx.compose.runtime.Composable
import opensavvy.ui.core.layout.Alignment
import opensavvy.ui.core.layout.Arrangement
import opensavvy.ui.core.layout.LinearLayouts
import org.jetbrains.compose.web.dom.Div

private val Arrangement.css: String
	get() = when (this) {
		Arrangement.Stretch -> "justify-items-stretch"
		Arrangement.Start -> "justify-start"
		Arrangement.Center -> "justify-center"
		Arrangement.End -> "justify-end"
		Arrangement.SpaceBetween -> "justify-between"
		Arrangement.SpaceAround -> "justify-around"
		Arrangement.SpaceEvenly -> "justify-evenly"
	}

private val Alignment.css: String
	get() = when (this) {
		Alignment.Stretch -> "items-stretch"
		Alignment.Start -> "items-start"
		Alignment.Center -> "items-center"
		Alignment.End -> "items-end"
	}

actual interface MaterialLinearLayouts : LinearLayouts {

	@Composable
	override fun Column(vertical: Arrangement, alignment: Alignment, content: @Composable () -> Unit) {
		Div(
			{
				classes(
					"flex",
					"flex-col",
					"gap-y-2",
					vertical.css,
					alignment.css
				)
			}
		) {
			content()
		}
	}

	@Composable
	override fun Row(horizontal: Arrangement, alignment: Alignment, content: @Composable () -> Unit) {
		Div(
			{
				classes(
					"flex",
					"flex-row",
					"gap-x-2",
					horizontal.css,
					alignment.css,
				)
			}
		) {
			content()
		}
	}

	@Composable
	override fun Box(alignment: Alignment, content: @Composable () -> Unit) {
		Div(
			{
				classes(
					"relative",
					"min-w-full",
					"[&>*]:absolute",
				)
			}
		) {
			content()
		}
	}

}
