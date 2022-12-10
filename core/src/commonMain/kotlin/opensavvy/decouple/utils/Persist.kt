package opensavvy.decouple.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

/**
 * Stores a value throughout program restarts.
 *
 * **Security**: the stored value is not guaranteed to be securely stored.
 * Use this function to store user preferences (theme…), but avoid storing secret information.
 *
 * @param id The identifier of this value. It must be unique for the entire application.
 * @param dependencies If any of these objects change, the initial value is recomputed.
 * @param initialValue Compute the initial value in case no value is currently stored.
 */
@Composable
expect fun <T> persist(
	id: String,
	vararg dependencies: Any?,
	initialValue: () -> T,
): MutableState<T>
