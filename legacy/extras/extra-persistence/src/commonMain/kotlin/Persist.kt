package opensavvy.decouple.persist

import androidx.compose.runtime.MutableState

/**
 * Stores a value across app restarts or configuration changes.
 *
 * Although this function is quite useful, we recommend using it with frugality.
 * It is intended to easily persist small amounts of data (like fields in a form) which would
 * otherwise be verbose to persist.
 * We do not recommend using it to store anything business-related because of how simple it is.
 *
 * We also strongly discourage storing any complex values using this function.
 * We recommend only using it with standard types (e.g. `Int`, `Char`, `Boolean`, `String`…).
 *
 * **Security**. No specific claims are made in regard to the confidentiality of the stored data.
 * Do NOT store confidential data using this function!
 *
 * #### Example in a Composable function
 *
 * ```kotlin
 * @Composable
 * fun LogInForm() {
 *     var email by remember { persistentStateOf("my.app.login.email") { "" } }
 *     var password by remember { mutableStateOf("") } // do NOT persist confidential data!
 *
 *     EmailField(
 *         email,
 *         onChange = { email = it },
 *     )
 *
 *     PasswordField(
 *         password,
 *         onChange = { password = it },
 *     )
 * }
 * ```
 *
 * #### Example as field of a class
 *
 * ```kotlin
 * private class LogInFormVM {
 *     var email by persistentStateOf("my.app.login.email") { "" }
 *     var password by mutableStateOf("") // do NOT persist confidential data!
 * }
 *
 * @Composable
 * fun LogInForm() {
 *     val form = remember { LogInFormVM() }
 *
 *     EmailField(
 *         form.email,
 *         onChange = { form.email = it },
 *     )
 *
 *     PasswordField(
 *         form.password,
 *         onChange = { form.password = it },
 *     )
 * }
 * ```
 *
 * @param id A unique identifier of this value for the current app.
 * Each value is stored in a platform-specific manner, associated with this id.
 * @param initialValue A function executed to generate the first value if none is available in storage.
 */
expect fun <T> persistentStateOf(id: String, initialValue: () -> T): MutableState<T>
