package opensavvy.decouple.testing.layout

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.layout.FullscreenLayouts
import opensavvy.decouple.testing.Component
import opensavvy.decouple.testing.bind
import opensavvy.decouple.testing.compose
import opensavvy.decouple.testing.execution.Slot
import opensavvy.decouple.testing.node.Node
import opensavvy.decouple.testing.node.getValue

class Screen(node: Node) : Component {
	val title: String by node.attributes
	val subtitle: String? by node.attributes

	val actions by node.slots

	val content by node.content

	companion object : Component.Meta<Screen> {
		override val name = "FullScreenLayouts.Screen"

		override fun buildFrom(node: Node) = Screen(node)
	}
}

class ListDetailScreen(node: Node) : Component {
	val title: String by node.attributes
	val subtitle: String? by node.attributes
	val showDetails: Boolean by node.attributes

	val actions by node.slots
	val list by node.slots
	val details by node.slots

	companion object : Component.Meta<ListDetailScreen> {
		override val name = "FullScreenLayouts.ListDetailScreen"

		override fun buildFrom(node: Node) = ListDetailScreen(node)
	}
}

class SupportedScreen(node: Node) : Component {
	val title: String by node.attributes
	val subtitle: String? by node.attributes
	val supportTitle: String? by node.attributes
	val showSupport: Boolean by node.attributes

	val actions by node.slots
	val support by node.slots

	val content by node.content

	companion object : Component.Meta<SupportedScreen> {
		override val name = "FullScreenLayouts.SupportedScreen"

		override fun buildFrom(node: Node) = SupportedScreen(node)
	}
}

object TFullscreenLayouts : FullscreenLayouts {

	@Composable
	override fun Screen(
		title: String,
		subtitle: String?,
		actions: (@Composable () -> Unit)?,
		content: @Composable () -> Unit,
	) {
		Screen.compose(
			update = {
				bind(title, Screen::title)
				bind(subtitle, Screen::subtitle)
			}
		) {
			if (actions != null) Slot(Screen::actions) {
				actions()
			}

			content()
		}
	}

	@Composable
	override fun ListDetailScreen(
		title: String,
		subtitle: String?,
		showDetails: Boolean,
		actions: (@Composable () -> Unit)?,
		list: @Composable () -> Unit,
		content: @Composable () -> Unit,
	) {
		ListDetailScreen.compose(
			update = {
				bind(title, ListDetailScreen::title)
				bind(subtitle, ListDetailScreen::subtitle)
				bind(showDetails, ListDetailScreen::showDetails)
			}
		) {
			if (actions != null) Slot(ListDetailScreen::actions) {
				actions()
			}

			Slot(ListDetailScreen::list) {
				list()
			}

			Slot(ListDetailScreen::details) {
				content()
			}
		}
	}

	@Composable
	override fun SupportedScreen(
		title: String,
		subtitle: String?,
		supportTitle: String?,
		showSupport: Boolean,
		actions: (@Composable () -> Unit)?,
		support: @Composable () -> Unit,
		content: @Composable () -> Unit,
	) {
		SupportedScreen.compose(
			update = {
				bind(title, SupportedScreen::title)
				bind(subtitle, SupportedScreen::subtitle)
				bind(supportTitle, SupportedScreen::supportTitle)
				bind(showSupport, SupportedScreen::showSupport)
			}
		) {
			if (actions != null) Slot(SupportedScreen::actions) {
				actions()
			}

			Slot(SupportedScreen::support) {
				support()
			}

			content()
		}
	}

}
