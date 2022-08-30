package opensavvy.ui.material.layout

import androidx.compose.runtime.Composable
import opensavvy.ui.core.layout.Alignment
import opensavvy.ui.core.layout.Arrangement
import opensavvy.ui.core.layout.LinearLayouts
import org.jetbrains.compose.web.dom.Div

actual interface MaterialLinearLayouts : LinearLayouts {

	@Composable
	override fun Column(vertical: Arrangement, alignment: Alignment, content: @Composable () -> Unit) {
		Div(
			{
				//TODO in https://gitlab.com/opensavvy/opensavvy-ui/-/issues/9
			}
		) {
			content()
		}
	}

	@Composable
	override fun Row(horizontal: Arrangement, alignment: Alignment, content: @Composable () -> Unit) {
		Div(
			{
				//TODO in https://gitlab.com/opensavvy/opensavvy-ui/-/issues/9
			}
		) {
			content()
		}
	}

	@Composable
	override fun Box(alignment: Alignment, content: @Composable () -> Unit) {
		Div(
			{
				//TODO in https://gitlab.com/opensavvy/opensavvy-ui/-/issues/9
			}
		) {
			content()
		}
	}

}
