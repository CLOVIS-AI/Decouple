package opensavvy.decouple.material3.androidx.components.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import opensavvy.decouple.components.ExperimentalComponent
import opensavvy.decouple.components.layout.Alignment
import opensavvy.decouple.components.layout.Arrangement
import opensavvy.decouple.components.layout.LinearLayouts
import androidx.compose.foundation.layout.Arrangement as JetpackArrangement
import androidx.compose.ui.Alignment.Companion as JetpackAlignment

@ExperimentalComponent
interface LinearLayouts : LinearLayouts {

	@Composable
	override fun RowSpec(args: LinearLayouts.LinearLayoutArgs) {
		// TODO #213: support for 'reverse'

		Row(
			horizontalArrangement = when (args.arrangement) {
				Arrangement.Stretch -> JetpackArrangement.Center // TODO #214
				Arrangement.Start -> JetpackArrangement.Start
				Arrangement.Center -> JetpackArrangement.Center
				Arrangement.End -> JetpackArrangement.End
				Arrangement.SpaceBetween -> JetpackArrangement.SpaceBetween
				Arrangement.SpaceAround -> JetpackArrangement.SpaceAround
				Arrangement.SpaceEvenly -> JetpackArrangement.SpaceEvenly
			},
			verticalAlignment = when (args.alignment) {
				Alignment.Stretch -> JetpackAlignment.CenterVertically // TODO #214
				Alignment.Start -> JetpackAlignment.Top
				Alignment.Center -> JetpackAlignment.CenterVertically
				Alignment.End -> JetpackAlignment.Bottom
			},
		) {
			args.content()
		}
	}

	@Composable
	override fun ColumnSpec(args: LinearLayouts.LinearLayoutArgs) {
		// TODO #213: support for 'reverse'

		Column(
			verticalArrangement = when (args.arrangement) {
				Arrangement.Stretch -> JetpackArrangement.Center // TODO #214
				Arrangement.Start -> JetpackArrangement.Top
				Arrangement.Center -> JetpackArrangement.Center
				Arrangement.End -> JetpackArrangement.Bottom
				Arrangement.SpaceBetween -> JetpackArrangement.SpaceBetween
				Arrangement.SpaceAround -> JetpackArrangement.SpaceAround
				Arrangement.SpaceEvenly -> JetpackArrangement.SpaceEvenly
			},
			horizontalAlignment = when (args.alignment) {
				Alignment.Stretch -> JetpackAlignment.CenterHorizontally // TODO #214
				Alignment.Start -> JetpackAlignment.Start
				Alignment.Center -> JetpackAlignment.CenterHorizontally
				Alignment.End -> JetpackAlignment.End
			}
		) {
			args.content()
		}
	}
}
