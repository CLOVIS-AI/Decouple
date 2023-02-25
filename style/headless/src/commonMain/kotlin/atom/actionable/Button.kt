package opensavvy.decouple.headless.atom.actionable

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.atom.actionable.Buttons
import opensavvy.decouple.headless.Component
import opensavvy.decouple.headless.bind
import opensavvy.decouple.headless.compose
import opensavvy.decouple.headless.execution.Slot
import opensavvy.decouple.headless.node.Node
import opensavvy.decouple.headless.node.NodeTree
import opensavvy.decouple.headless.node.getValue
import opensavvy.state.Progression

interface AbstractButton {
	val click: () -> Unit
	val enabled: Boolean
	val loading: Progression

	val icon: NodeTree

	val content: NodeTree
}

class Button(node: Node) : AbstractButton, Component {
	override val click: () -> Unit by node.attributes
	override val enabled: Boolean by node.attributes
	override val loading: Progression by node.attributes

	override val icon by node.slots

	override val content by node.content

	companion object : Component.Meta<Button> {
		override val name = "Buttons.Button"

		override fun buildFrom(node: Node) = Button(node)
	}
}

class PrimaryButton(node: Node) : AbstractButton, Component {
	override val click: () -> Unit by node.attributes
	override val enabled: Boolean by node.attributes
	override val loading: Progression by node.attributes
	val primary: Boolean by node.attributes

	override val icon by node.slots

	override val content by node.content

	companion object : Component.Meta<PrimaryButton> {
		override val name = "Buttons.PrimaryButton"

		override fun buildFrom(node: Node) = PrimaryButton(node)
	}
}

class SecondaryButton(node: Node) : AbstractButton, Component {
	override val click: () -> Unit by node.attributes
	override val enabled: Boolean by node.attributes
	override val loading: Progression by node.attributes

	override val icon by node.slots

	override val content by node.content

	companion object : Component.Meta<SecondaryButton> {
		override val name = "Buttons.SecondaryButton"

		override fun buildFrom(node: Node) = SecondaryButton(node)
	}
}

class ContractButton(node: Node) : AbstractButton, Component {
	override val click: () -> Unit by node.attributes
	override val enabled: Boolean by node.attributes
	override val loading: Progression by node.attributes

	override val icon by node.slots

	override val content by node.content

	companion object : Component.Meta<SecondaryButton> {
		override val name = "Buttons.ContrastButton"

		override fun buildFrom(node: Node) = SecondaryButton(node)
	}
}

object TButtons : Buttons {

	@Composable
	override fun Button(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		icon: (@Composable () -> Unit)?,
		content: @Composable Buttons.ButtonScope.() -> Unit,
	) {
		Button.compose(
			update = {
				bind(onClick, Button::click)
				bind(enabled, Button::enabled)
				bind(loading, Button::loading)
			}
		) {
			if (icon != null) Slot(Button::icon) {
				icon()
			}

			content(TButtonScope)
		}
	}

	@Composable
	override fun PrimaryButton(
		onClick: () -> Unit,
		primary: Boolean,
		enabled: Boolean,
		loading: Progression,
		icon: (@Composable () -> Unit)?,
		content: @Composable Buttons.ButtonScope.() -> Unit,
	) {
		PrimaryButton.compose(
			update = {
				bind(onClick, PrimaryButton::click)
				bind(primary, PrimaryButton::primary)
				bind(enabled, PrimaryButton::enabled)
				bind(loading, PrimaryButton::loading)
			}
		) {
			if (icon != null) Slot(PrimaryButton::icon) {
				icon()
			}

			content(TButtonScope)
		}
	}

	@Composable
	override fun SecondaryButton(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		icon: (@Composable () -> Unit)?,
		content: @Composable Buttons.ButtonScope.() -> Unit,
	) {
		SecondaryButton.compose(
			update = {
				bind(onClick, SecondaryButton::click)
				bind(enabled, SecondaryButton::enabled)
				bind(loading, SecondaryButton::loading)
			}
		) {
			if (icon != null) Slot(SecondaryButton::icon) {
				icon()
			}

			content(TButtonScope)
		}
	}

	@Composable
	override fun ContrastButton(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progression,
		icon: (@Composable () -> Unit)?,
		content: @Composable Buttons.ButtonScope.() -> Unit,
	) {
		ContractButton.compose(
			update = {
				bind(onClick, SecondaryButton::click)
				bind(enabled, SecondaryButton::enabled)
				bind(loading, SecondaryButton::loading)
			}
		) {
			if (icon != null) Slot(SecondaryButton::icon) {
				icon()
			}

			content(TButtonScope)
		}
	}

	object TButtonScope : Buttons.ButtonScope
}
