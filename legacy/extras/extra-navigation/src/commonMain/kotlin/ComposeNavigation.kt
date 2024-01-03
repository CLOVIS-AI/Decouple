package opensavvy.decouple.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Simple pure-Compose implementation of [Navigation].
 *
 * This implementation **does not** communicate the current state of the navigation with the system (URL…).
 */
class ComposeNavigation(
	initial: Destination,
	private val onExit: () -> Unit = {},
) : Navigation {

	private var instance by mutableStateOf(Instance(initial, previous = null))

	override val current: Destination
		get() = instance.destination

	override fun forwards(destination: Destination) {
		instance = Instance(destination, previous = instance)
	}

	override fun lateral(destination: Destination) {
		instance = Instance(destination, previous = instance.previous)
	}

	override fun backwards() {
		val previous = instance.previous

		if (previous != null)
			instance = previous
		else
			exit()
	}

	override fun upwards(from: Destination) {
		val to = from.parent ?: return

		instance = Instance(to, previous = instance)
	}

	override fun exit() {
		onExit()
	}

	private data class Instance(
		val destination: Destination,
		val previous: Instance?,
	)
}
