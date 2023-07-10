package opensavvy.decouple.material.androidx.atom.actionable

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import opensavvy.decouple.core.UI
import opensavvy.decouple.core.atom.actionable.Buttons
import opensavvy.progress.Progress
import opensavvy.progress.done
import androidx.compose.material3.Button as M3Button
import androidx.compose.material3.ElevatedButton as M3ElevatedButton
import androidx.compose.material3.FilledTonalButton as M3FilledTonalButton
import androidx.compose.material3.OutlinedButton as M3OutlinedButton
import androidx.compose.material3.TextButton as M3TextButton

object MAButtons : Buttons {

	@Composable
	@Suppress("UnusedReceiverParameter", "UNUSED_PARAMETER")
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

	@Composable
	override fun ButtonSpec(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progress,
		icon: (@Composable () -> Unit)?,
		content: @Composable Buttons.ButtonScope.() -> Unit,
	) {
		M3TextButton(
			onClick = onClick,
			enabled = enabled && loading == done(),
		) { ButtonContents(loading, icon, content) }
	}

	@Composable
	override fun PrimaryButtonSpec(
		onClick: () -> Unit,
		primary: Boolean,
		enabled: Boolean,
		loading: Progress,
		icon: (@Composable () -> Unit)?,
		content: @Composable Buttons.ButtonScope.() -> Unit,
	) {
		if (primary) {
			M3Button(
				onClick = onClick,
				enabled = enabled && loading == done(),
			) { ButtonContents(loading, icon, content) }
		} else {
			M3FilledTonalButton(
				onClick = onClick,
				enabled = enabled && loading == done(),
			) { ButtonContents(loading, icon, content) }
		}
	}

	@Composable
	override fun SecondaryButtonSpec(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progress,
		icon: (@Composable () -> Unit)?,
		content: @Composable Buttons.ButtonScope.() -> Unit,
	) {
		M3OutlinedButton(
			onClick = onClick,
			enabled = enabled && loading == done()
		) { ButtonContents(loading, icon, content) }
	}

	@Composable
	override fun ContrastButtonSpec(
		onClick: () -> Unit,
		enabled: Boolean,
		loading: Progress,
		icon: (@Composable () -> Unit)?,
		content: @Composable Buttons.ButtonScope.() -> Unit,
	) {
		M3ElevatedButton(
			onClick = onClick,
			enabled = enabled && loading == done(),
		) { ButtonContents(loading, icon, content) }
	}

	private object MAButtonScope : Buttons.ButtonScope
}
