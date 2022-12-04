package opensavvy.ui.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.browser.window
import opensavvy.logger.Logger.Companion.debug
import opensavvy.logger.Logger.Companion.trace
import opensavvy.logger.loggerFor
class BrowserNavigation(
	initial: Destination,
	private val routes: Collection<Destination>,
) : Navigation {

	private val log = loggerFor(this)

	override var current: Destination by mutableStateOf(initial)
		private set

	init { // Find out what the current local is
		initPath()
	}

	private fun initPath() {
		val path = window.location.pathname.removePrefix("/")

		val candidate = routes.find { it.fullRoute.joinToString(separator = "/") == path }
		if (candidate != null)
			current = candidate

		log.debug { "Loaded URL from navigation bar: $candidate" }
	}

	init { // React to the actions made in the browser's UI
		window.addEventListener("popstate", {
			log.trace { "Received a popstate event: the user has clicked the forward or back button in their browser" }

			initPath()
			it.preventDefault()
		})
	}

	override fun forwards(destination: Destination) {
		log.debug(destination) { "Forwards navigation" }
		window.history.pushState(
			null,
			destination.title,
			'/' + destination.fullRoute.joinToString(separator = "/")
		)
		current = destination
	}

	override fun lateral(destination: Destination) {
		log.debug(destination) { "Lateral navigation" }
		window.history.replaceState(
			null,
			destination.title,
			'/' + destination.fullRoute.joinToString(separator = "/")
		)
		current = destination
	}

	override fun backwards() {
		log.debug { "Backwards navigation" }
		window.history.back() // let the browser handle this
	}

	override fun upwards(from: Destination) {
		log.debug(from) { "Upwards navigation, interpreted as forwards navigation" }
		forwards(from)
	}

	override fun exit() {
		// Nothing to do, the browser exists the page automatically
	}

}
