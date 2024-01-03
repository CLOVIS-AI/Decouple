package opensavvy.decouple.material3.androidx.components.actions

import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import opensavvy.decouple.components.actions.Buttons
import opensavvy.progress.done
import androidx.compose.material3.Button as FilledButton

interface Buttons : Buttons {

	@Composable
	override fun ButtonSpec(args: Buttons.ButtonArgs) {
		val onClick = args.onClick
		val enabled = args.enabled && args.progress == done()

		when (args.role) {
			Buttons.Role.Primary -> FilledButton(
				onClick = onClick,
				enabled = enabled,
			) {
				args.icon?.invoke()

				args.content()
			}

			Buttons.Role.Secondary -> FilledTonalButton(
				onClick = onClick,
				enabled = enabled,
			) {
				args.icon?.invoke()

				args.content()
			}

			Buttons.Role.Action -> OutlinedButton(
				onClick = onClick,
				enabled = enabled,
			) {
				args.icon?.invoke()

				args.content()
			}

			else -> TextButton(
				onClick = onClick,
				enabled = enabled,
			) {
				args.icon?.invoke()

				args.content()
			}
		}
	}
}
