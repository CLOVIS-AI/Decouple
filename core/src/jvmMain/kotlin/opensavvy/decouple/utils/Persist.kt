@file:JvmName("PersistJvm")

package opensavvy.decouple.utils

import androidx.compose.runtime.*
import opensavvy.decouple.utils.Persist.log
import opensavvy.logger.Logger.Companion.warn
import opensavvy.logger.loggerFor

private object Persist {
	val log = loggerFor(this)
}

@Composable
actual fun <T> persist(
	id: String,
	vararg dependencies: Any?,
	initialValue: () -> T,
): MutableState<T> {
	log.warn(id) { "persist is currently not implemented on the JVM." }
	return remember { mutableStateOf(initialValue()) }
}
