package opensavvy.decouple.purecss.components.layouts

import androidx.compose.runtime.Composable
import opensavvy.decouple.components.ExperimentalComponent
import opensavvy.decouple.components.layout.LinearLayouts
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

@ExperimentalComponent
interface LinearLayouts : LinearLayouts {

	@Composable
	override fun ColumnSpec(args: LinearLayouts.LinearLayoutArgs) {
		Div({
			style {
				display(DisplayStyle.Flex)
				flexDirection(if (!args.reverse) FlexDirection.Column else FlexDirection.ColumnReverse)
				gap(0.5.cssRem)
				applyArrangement(args.arrangement)
				applyAlignment(args.alignment)
			}
		}) {
			args.content()
		}
	}

	@Composable
	override fun RowSpec(args: LinearLayouts.LinearLayoutArgs) {
		Div({
			style {
				display(DisplayStyle.Flex)
				flexDirection(if (!args.reverse) FlexDirection.Row else FlexDirection.RowReverse)
				gap(0.5.cssRem)
				applyArrangement(args.arrangement)
				applyAlignment(args.alignment)
			}
		}) {
			args.content()
		}
	}
}
