package opensavvy.decouple.persist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import opensavvy.decouple.persist.Persist.log
import opensavvy.logger.Logger.Companion.warn
import opensavvy.logger.loggerFor

private object Persist {
	val log = loggerFor(this)
}

actual fun <T> persistentStateOf(id: String, initialValue: () -> T): MutableState<T> {
	log.warn(id) { "persistStateOf is currently not implemented on the JVM." }
	return mutableStateOf(initialValue())
}
