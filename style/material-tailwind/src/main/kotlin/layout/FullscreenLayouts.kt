package opensavvy.decouple.material.tailwind.layout

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.layout.Column
import opensavvy.decouple.core.layout.FullscreenLayouts
import org.jetbrains.compose.web.dom.*

object MTFullscreenLayouts : FullscreenLayouts {

	//TODO in #31: make these components responsive

	private val screenOutline = arrayOf("h-full", "w-full", "p-8")
	private val columns = arrayOf("flex", "flex-row", "gap-6")
	private val panelOneThird = arrayOf("w-[33%]", "min-w-[15em]", "max-w-[25em]", "flex-none")

	@Composable
	private fun ScreenOutline(
		vararg classes: String,
		content: @Composable () -> Unit,
	) = Div(
		{
			classes(*screenOutline)
			classes(*classes)
		}
	) {
		content()
	}

	@Composable
	private fun Title(title: String) = P(
		{
			classes("text-3xl", "pb-4")
		}
	) {
		Text(title)
	}

	@Composable
	private fun Subtitle(subtitle: String?) {
		if (subtitle != null) P(
			{
				classes("text-xl", "pb-2")
			}
		) {
			Text(subtitle)
		}
	}

	@Composable
	override fun Screen(
		title: String,
		subtitle: String?,
		actions: (@Composable () -> Unit)?,
		content: @Composable () -> Unit,
	) = ScreenOutline {
		Article {
			Column {
				Title(title)
				Subtitle(subtitle)

				if (actions != null)
					actions()

				content()
			}
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
	) = ScreenOutline(*columns) {
		Nav(
			{
				classes(*panelOneThird)
			}
		) {
			Column {
				Title(title)
				Subtitle(subtitle)

				list()
			}
		}

		if (showDetails) Article(
			{
				classes("grow")
			}
		) {
			Column {
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
	) = ScreenOutline(*columns) {
		Article(
			{
				classes("grow")
			}
		) {
			Column {
				Title(title)
				Subtitle(subtitle)

				content()
			}
		}

		if (showSupport) Aside(
			{
				classes(*panelOneThird)
			}
		) {
			Column {
				Subtitle(supportTitle)

				support()
			}
		}
	}

}
