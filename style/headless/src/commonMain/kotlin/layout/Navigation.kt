package opensavvy.decouple.headless.layout

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.layout.Navigation
import opensavvy.decouple.core.layout.NavigationMenu
import opensavvy.decouple.headless.Component
import opensavvy.decouple.headless.bind
import opensavvy.decouple.headless.compose
import opensavvy.decouple.headless.node.Node
import opensavvy.decouple.headless.node.getValue

/**
 * Type-safe wrapper for [opensavvy.decouple.core.layout.GlobalNavigation].
 */
class GlobalNavigation(node: Node) : Component {
	val menu: NavigationMenu.Menu<*> by node.attributes
	val selected: NavigationMenu<*> by node.attributes
	val select: (NavigationMenu.Page<*>) -> Unit by node.attributes

	val content by node.content

	companion object : Component.Meta<GlobalNavigation> {
		override val name = "Navigation.GlobalNavigation"

		override fun buildFrom(node: Node) = GlobalNavigation(node)
	}
}

object TNavigation : Navigation {
	@Composable
	override fun <P> GlobalNavigation(
		menu: NavigationMenu.Menu<P>,
		selected: NavigationMenu<P>,
		onSelect: (NavigationMenu.Page<P>) -> Unit,
		currentContents: @Composable () -> Unit,
	) {
		GlobalNavigation.compose(
			update = {
				bind(menu, GlobalNavigation::menu)
				bind(selected, GlobalNavigation::selected)
				bind(onSelect, GlobalNavigation::select)
			}
		) {
			currentContents()
		}
	}

}
