package opensavvy.decouple.material3.html.components.layouts

import androidx.compose.runtime.Composable
import opensavvy.decouple.components.ExperimentalComponent
import opensavvy.decouple.components.layout.LinearLayouts
import org.jetbrains.compose.web.dom.Div

private val flexClasses = listOf("flex", "gap-2")

@ExperimentalComponent
interface LinearLayouts : LinearLayouts {

	@Composable
	override fun ColumnSpec(args: LinearLayouts.LinearLayoutArgs) {
		Div({
			classes(flexClasses)
			classes(if (!args.reverse) "flex-col" else "flex-col-reverse")
			classes(args.arrangement.css, args.alignment.css)
		}) {
			args.content()
		}
	}

	@Composable
	override fun RowSpec(args: LinearLayouts.LinearLayoutArgs) {
		Div({
			classes(flexClasses)
			classes(if (!args.reverse) "flex-row" else "flex-row-reverse")
			classes(args.arrangement.css, args.alignment.css)
		}) {
			args.content()
		}
	}
}
