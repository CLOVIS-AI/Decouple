package opensavvy.decouple.components

/**
 * Mark classes which follow a restricted set of binary-compatible changes.
 *
 * ### Why?
 *
 * Compose represents components as `@Composable` functions, representing shared data as function
 * parameters. However, this makes improving a library difficult, as adding a function parameter
 * is a binary-breaking change.
 *
 * Because we want to be able to edit the parameters that are passed to components, we are
 * representing them as data classes. This is important because it lets the Compose Compiler
 * notice that they are stable automatically, avoiding performance issues.
 *
 * However, [data classes have their own binary-compatibility problems](https://jakewharton.com/public-api-challenges-in-kotlin/).
 * To circumvent this, we decided to **explicitly forbid some features of data classes**.
 *
 * ### What is safe
 *
 * For these operations, source and binary compatibility are ensured between Decouple versions.
 *
 * - Explicit access to any field via its getter
 * - Calling [equals], [hashCode] and [toString] (though the results may be different from one version to the next)
 *
 * ### What isn't safe
 *
 * For these operations, source and binary compatibility are **not guaranteed**.
 * Any new Decouple version may break these features, even patch-level versions.
 *
 * - Calling the constructor (because new mandatory arguments may be added)
 * - Calling the generated `copy` method (because new mandatory arguments may be added)
 * - Using destructuration (because the order of fields may change)
 */
@RequiresOptIn(message = "This class opts-out of multiple source and binary compatibility guarantees. Read this annotation's documentation to learn more.")
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
annotation class RestrictedStabilityArgument
