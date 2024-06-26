package opensavvy.decouple.persist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import opensavvy.decouple.persist.Persist.log
import opensavvy.logger.Logger.Companion.warn
import opensavvy.logger.loggerFor

private object Persist {
	val log = loggerFor(this)
}

/**
 * Stores a value across app restarts.
 *
 * **This function is currently NOT implemented for the JVM.
 * If you need it, please manifest yourself on the [tracking issue](https://gitlab.com/opensavvy/ui/decouple/-/issues/167).**
 */
actual fun <T> persistentStateOf(id: String, initialValue: () -> T): MutableState<T> {
	log.warn(id) { "persistStateOf is currently not implemented on the JVM." }
	return mutableStateOf(initialValue())
}
