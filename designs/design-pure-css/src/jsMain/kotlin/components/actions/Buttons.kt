package opensavvy.decouple.purecss.components.actions

import androidx.compose.runtime.Composable
import opensavvy.decouple.components.actions.Buttons
import opensavvy.progress.done
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.dom.Button as DomButton

interface Buttons : Buttons {

	@Composable
	override fun ButtonSpec(args: Buttons.ButtonArgs) {
		DomButton({
			classes("pure-button")

			if (!args.enabled)
				disabled()

			if (args.progress == done())
				onClick { args.onClick() }
			else
				classes("pure-button-active")

			if (args.role == Buttons.Role.Primary || args.role == Buttons.Role.Secondary)
				classes("pure-button-primary")
		}) {
			args.icon?.invoke()

			args.content()
		}
	}
}
