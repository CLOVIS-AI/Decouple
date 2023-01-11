package opensavvy.decouple.material.androidx.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import opensavvy.decouple.core.layout.FullscreenLayouts

object MAFullscreenLayouts : FullscreenLayouts {

	@Composable
	override fun Screen(
		title: String,
		subtitle: String?,
		actions: (@Composable () -> Unit)?,
		content: @Composable () -> Unit,
	) = Column(Modifier.verticalScroll(rememberScrollState())) {
		Text(title, fontSize = 30.sp)

		if (subtitle != null)
			Text(subtitle, fontSize = 20.sp)

		content()
	}

	@Composable
	override fun ListDetailScreen(
		title: String,
		subtitle: String?,
		showDetails: Boolean,
		actions: (@Composable () -> Unit)?,
		list: @Composable () -> Unit,
		content: @Composable () -> Unit
	) = Column(Modifier.verticalScroll(rememberScrollState())) {
		Text(title, fontSize = 30.sp)

		if (subtitle != null)
			Text(subtitle, fontSize = 20.sp)

		//TODO in #97
		list()
		if (showDetails)
			content()
	}

	@Composable
	override fun SupportedScreen(
		title: String,
		subtitle: String?,
		supportTitle: String?,
		showSupport: Boolean,
		actions: (@Composable () -> Unit)?,
		support: @Composable () -> Unit,
		content: @Composable () -> Unit
	) = Column(Modifier.verticalScroll(rememberScrollState())) {
		Text(title, fontSize = 30.sp)

		if (subtitle != null)
			Text(subtitle, fontSize = 20.sp)

		//TODO in #97
		content()
		if (showSupport)
			support()
	}

}
