package opensavvy.decouple.persist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.browser.window
import opensavvy.logger.Logger.Companion.warn
import opensavvy.logger.loggerFor

private class SessionStorageState<T>(
	id: String,
	private val initialValue: () -> T,
) : MutableState<T> {

	private val key = "opensavvy.decouple.$id"
	private val log = loggerFor(this)

	private var cache by mutableStateOf(
		window.localStorage.getItem(key)
			?.let {
				try {
					JSON.parse<T>(it)
				} catch (e: Throwable) {
					log.warn(key) { "Couldn't deserialize from the local storage" }
					null
				}
			}
			?: initialValue()
	)

	override var value: T
		get() = cache
		set(value) {
			cache = value
			window.localStorage.setItem(key, JSON.stringify(value))
		}

	override fun component1(): T = value

	override fun component2(): (T) -> Unit = { value = it }

}

actual fun <T> persistentStateOf(id: String, initialValue: () -> T): MutableState<T> =
	SessionStorageState(id, initialValue)
