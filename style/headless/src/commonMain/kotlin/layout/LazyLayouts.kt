package opensavvy.decouple.headless.layout

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.layout.Alignment
import opensavvy.decouple.core.layout.Arrangement
import opensavvy.decouple.core.layout.LazyLayouts

object TLazyLayouts : LazyLayouts {

	@Composable
	override fun LazyColumn(
		vertical: Arrangement,
		alignment: Alignment,
		content: LazyLayouts.LazyColumnScope.() -> Unit,
	) {
		TODO("Will be implemented in https://gitlab.com/opensavvy/decouple/-/issues/91")
	}

	@Composable
	override fun LazyRow(
		horizontal: Arrangement,
		alignment: Alignment,
		content: LazyLayouts.LazyRowScope.() -> Unit,
	) {
		TODO("Will be implemented in https://gitlab.com/opensavvy/decouple/-/issues/91")
	}

}
