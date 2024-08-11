package opensavvy.decouple.material3.html.components.actions

import androidx.compose.runtime.Composable
import opensavvy.decouple.components.actions.Buttons
import opensavvy.material3.html.ExperimentalComponent
import opensavvy.material3.html.UnfinishedComponent
import opensavvy.material3.html.actions.buttons.FilledButton
import opensavvy.material3.html.actions.buttons.FilledTonalButton
import opensavvy.material3.html.actions.buttons.OutlinedButton
import opensavvy.material3.html.actions.buttons.TextButton

@UnfinishedComponent
@ExperimentalComponent
interface Buttons : Buttons {

	@Composable
	override fun ButtonSpec(args: Buttons.ButtonArgs) {
		when (args.role) {
			Buttons.Role.Primary -> FilledButton(
				label = "TODO",
				enabled = args.enabled,
				progress = args.progress,
				action = args.onClick,
				icon = args.icon,
			)

			Buttons.Role.Secondary -> FilledTonalButton(
				label = "TODO",
				enabled = args.enabled,
				progress = args.progress,
				action = args.onClick,
				icon = args.icon,
			)

			Buttons.Role.Action -> OutlinedButton(
				label = "TODO",
				enabled = args.enabled,
				progress = args.progress,
				action = args.onClick,
				icon = args.icon,
			)

			else -> TextButton(
				label = "TODO",
				enabled = args.enabled,
				progress = args.progress,
				action = args.onClick,
				icon = args.icon,
			)
		}
	}
}
