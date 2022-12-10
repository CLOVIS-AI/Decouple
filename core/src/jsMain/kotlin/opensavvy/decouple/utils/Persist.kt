package opensavvy.decouple.utils

import androidx.compose.runtime.*
import kotlinx.browser.window
import opensavvy.decouple.utils.Persist.log
import opensavvy.logger.Logger.Companion.warn
import opensavvy.logger.loggerFor

private object Persist {
	val log = loggerFor(this)
}

@Composable
actual fun <T> persist(id: String, vararg dependencies: Any?, initialValue: () -> T): MutableState<T> {
	val key = remember(id) { "opensavvy.ui.$id" }
	var value by remember(id, *dependencies) {
		val value = window.localStorage.getItem(key)
			?.let {
				try {
					JSON.parse<T>(it)
				} catch (e: Throwable) {
					log.warn(key) { "Couldn't deserialize from the session storage" }
					null
				}
			}

		mutableStateOf(value ?: initialValue())
	}

	return remember {
		DelegatedState(
			get = { value },
			set = {
				window.localStorage.setItem(key, JSON.stringify(it))
				value = it
			},
		)
	}
}
