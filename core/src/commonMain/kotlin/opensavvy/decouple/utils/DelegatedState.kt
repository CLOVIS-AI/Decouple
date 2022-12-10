package opensavvy.decouple.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State

/**
 * Utility to create delegated properties compatible with Compose [State].
 */
@Stable
class DelegatedState<T>(
	val get: () -> T,
	val set: (T) -> Unit,
) : MutableState<T> {

	override var value: T
		get() = get()
		set(value) = set(value)

	override fun component1(): T = get()
	override fun component2(): (T) -> Unit = set
}
