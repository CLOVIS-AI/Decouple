@file:JvmName("PersistJvm")

package opensavvy.ui.utils

import androidx.compose.runtime.*
import opensavvy.logger.Logger.Companion.warn
import opensavvy.logger.loggerFor
import opensavvy.ui.utils.Persist.log

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
