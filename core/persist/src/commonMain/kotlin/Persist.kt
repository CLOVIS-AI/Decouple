package opensavvy.decouple.persist

import androidx.compose.runtime.MutableState

expect fun <T> persistentStateOf(id: String, initialValue: () -> T): MutableState<T>
