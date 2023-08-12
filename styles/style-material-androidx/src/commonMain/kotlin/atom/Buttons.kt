package opensavvy.decouple.material.androidx.atom

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import opensavvy.decouple.core.UI
import opensavvy.decouple.core.atom.ButtonAttrs
import opensavvy.decouple.core.atom.Buttons
import opensavvy.progress.Progress
import opensavvy.progress.done
import androidx.compose.material3.Button as M3Button
import androidx.compose.material3.ElevatedButton as M3ElevatedButton
import androidx.compose.material3.FilledTonalButton as M3FilledTonalButton
import androidx.compose.material3.OutlinedButton as M3OutlinedButton
import androidx.compose.material3.TextButton as M3TextButton

object MAButtons : Buttons {

	@Composable
	override fun ButtonSpec(attrs: ButtonAttrs) {
		val enabled = attrs.enabled && attrs.loading == done()

		when {
			attrs.role == Buttons.Role.Primary -> M3Button(
				onClick = attrs.onClick,
				enabled = enabled,
			) { ButtonContents(attrs.loading, attrs.icon, attrs.content) }

			attrs.role == Buttons.Role.Secondary -> M3FilledTonalButton(
				onClick = attrs.onClick,
				enabled = enabled,
			) { ButtonContents(attrs.loading, attrs.icon, attrs.content) }

			attrs.role == Buttons.Role.Action -> M3OutlinedButton(
				onClick = attrs.onClick,
				enabled = enabled
			) { ButtonContents(attrs.loading, attrs.icon, attrs.content) }

			attrs.role == Buttons.Role.Normal -> M3TextButton(
				onClick = attrs.onClick,
				enabled = enabled,
			) { ButtonContents(attrs.loading, attrs.icon, attrs.content) }

			attrs.contrasted -> M3ElevatedButton(
				onClick = attrs.onClick,
				enabled = enabled,
			) { ButtonContents(attrs.loading, attrs.icon, attrs.content) }
		}
	}

	@Composable
	@Suppress("UnusedReceiverParameter")
	private fun RowScope.ButtonContents(
		loading: Progress,
		icon: (@Composable () -> Unit)?,
		content: @Composable Buttons.ButtonScope.() -> Unit,
	) {
		if (icon != null)
			icon()

		UI.current.ProgressIndicatorSpec(loading)

		MAButtonScope.content()
	}

	private object MAButtonScope : Buttons.ButtonScope
}
