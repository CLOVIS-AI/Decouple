package opensavvy.decouple.material.tailwind.layout

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.layout.Alignment
import opensavvy.decouple.core.layout.Arrangement
import opensavvy.decouple.core.layout.LinearLayouts
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

internal fun linearClasses(
	arrangement: Arrangement,
	alignment: Alignment,
) = listOf(
	"gap-2",
	arrangement.css,
	alignment.css,
)

object MTLinearLayouts : LinearLayouts {

	@Composable
	override fun Column(
		vertical: Arrangement,
		alignment: Alignment,
		content: @Composable LinearLayouts.ColumnScope.() -> Unit,
	) {
		Div(
			{
				classes(
					"flex",
					"flex-col",
				)
				classes(linearClasses(vertical, alignment))
			}
		) {
			content(ColumnScope)
		}
	}

	@Composable
	override fun Row(
		horizontal: Arrangement,
		alignment: Alignment,
		content: @Composable LinearLayouts.RowScope.() -> Unit,
	) {
		Div(
			{
				classes(
					"flex",
					"flex-row",
				)
				classes(linearClasses(horizontal, alignment))
			}
		) {
			content(RowScope)
		}
	}

	@Composable
	override fun Box(alignment: Alignment, content: @Composable LinearLayouts.BoxScope.() -> Unit) {
		Div(
			{
				classes(
					"relative",
					"min-w-full",
					"[&>*]:absolute",
				)
			}
		) {
			content(BoxScope)
		}
	}

	private object ColumnScope : LinearLayouts.ColumnScope
	private object RowScope : LinearLayouts.RowScope
	private object BoxScope : LinearLayouts.BoxScope
}
