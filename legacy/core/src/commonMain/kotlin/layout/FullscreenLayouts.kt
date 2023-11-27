package opensavvy.decouple.core.layout

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.UI

/**
 * Common examples of fullscreen layouts.
 *
 * The simplest layout is [ScreenSpec].
 */
interface FullscreenLayouts {

	@Composable
	fun ScreenSpec(
		title: String,
		subtitle: String?,
		actions: (@Composable () -> Unit)?,
		content: @Composable () -> Unit,
	)

	@Composable
	fun ListDetailScreenSpec(
		title: String,
		subtitle: String?,
		showDetails: Boolean,
		actions: (@Composable () -> Unit)?,
		list: @Composable () -> Unit,
		content: @Composable () -> Unit,
	) = Row {
		ScreenSpec(title, subtitle, actions, list)

		Column {
			if (showDetails)
				content()
		}
	}

	@Composable
	fun SupportedScreenSpec(
		title: String,
		subtitle: String?,
		supportTitle: String?,
		showSupport: Boolean,
		actions: (@Composable () -> Unit)?,
		support: @Composable () -> Unit,
		content: @Composable () -> Unit,
	) = Row {
		ScreenSpec(title, subtitle, actions, content)

		Column {
			if (showSupport)
				support()
		}
	}

}

/**
 * Simplest screen layout: [content] takes the entire available space.
 */
@Composable
fun Screen(
	title: String,
	subtitle: String? = null,
	actions: (@Composable () -> Unit)? = null,
	content: @Composable () -> Unit,
) {
	UI.current.ScreenSpec(title, subtitle, actions, content)
}

/**
 * The list-detail layout lets user interact with the details, while still allowing them
 * to easily navigate to any element in the list.
 *
 * For example, this layout could be used to represent a list of emails, where the details are used to show
 * the contents of the emails.
 *
 * @param showDetails if `false`, the details are not shown on screen.
 * @param list the list
 * @param content the details
 */
@Composable
fun ListDetailScreen(
	title: String,
	subtitle: String? = null,
	showDetails: Boolean = true,
	list: @Composable () -> Unit,
	actions: (@Composable () -> Unit)? = null,
	content: @Composable () -> Unit,
) {
	UI.current.ListDetailScreenSpec(title, subtitle, showDetails, actions, list, content)
}

/**
 * The support screen displays the most important content in the foreground, with additional support
 * information.
 *
 * The support information could for example be used to add a table of contents.
 *
 * @param showSupport if `false`, the support panel is not shown
 * @param support the support panel
 * @param content the main contents of the screen
 */
@Composable
fun SupportedScreen(
	title: String,
	subtitle: String? = null,
	supportTitle: String? = null,
	showSupport: Boolean = true,
	support: @Composable () -> Unit,
	actions: (@Composable () -> Unit)? = null,
	content: @Composable () -> Unit,
) {
	UI.current.SupportedScreenSpec(title, subtitle, supportTitle, showSupport, actions, support, content)
}
