package opensavvy.decouple.core

/**
 * Marks a component, or attribute of a component, as experimental.
 *
 * Experimental components may be removed at any time without warning.
 * All attributes of an experimental component are to be considered experimental as well, even if they are not marked with
 * this annotation.
 *
 * Experimental attributes of a stable component may be removed at any time without warning.
 * However, other fields of the argument are still safe to use.
 */
@RequiresOptIn(message = "This component or attribute is experimental. It may be changed in the future without notice.")
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION)
annotation class ExperimentalComponent
