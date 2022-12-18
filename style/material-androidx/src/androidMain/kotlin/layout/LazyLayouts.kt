package opensavvy.decouple.material.androidx.layout

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.layout.Alignment
import opensavvy.decouple.core.layout.Arrangement
import opensavvy.decouple.core.layout.LazyLayouts

object MALazyLayouts : LazyLayouts {

	@Composable
	override fun LazyColumn(
		vertical: Arrangement,
		alignment: Alignment,
		content: LazyLayouts.LazyColumnScope.() -> Unit,
	) {
		//TODO in #84
	}

	@Composable
	override fun LazyRow(
		horizontal: Arrangement,
		alignment: Alignment,
		content: LazyLayouts.LazyRowScope.() -> Unit,
	) {
		//TODO in #84
	}

}
