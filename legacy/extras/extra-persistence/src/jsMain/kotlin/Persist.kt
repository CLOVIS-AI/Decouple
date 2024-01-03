package opensavvy.decouple.persist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.browser.window
import opensavvy.logger.Logger.Companion.warn
import opensavvy.logger.loggerFor
import org.w3c.dom.Window

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

/**
 * Stores a value across page reloads.
 *
 * For general information, please read the documentation in the common code.
 *
 * #### Implementation note
 *
 * > This relates to the current implementation.
 * > We do not consider these details as part of the API, so we may change them without notice.
 *
 * Persisted values are stored in [Window.localStorage].
 * They are serialized/deserialized with [JSON], [which is not appropriate for complex classes](https://stackoverflow.com/a/50934453), but is fine for simple types (primitives, arrays, strings).
 */
actual fun <T> persistentStateOf(id: String, initialValue: () -> T): MutableState<T> =
	SessionStorageState(id, initialValue)
