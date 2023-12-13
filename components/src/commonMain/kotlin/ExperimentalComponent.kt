package opensavvy.decouple.components

/**
 * Marks a component or attribute as experimental.
 *
 * Experimental components may be removed at any time without warning.
 * All attributes of an experimental component are to be considered experimental as well, even
 * if they are not marked with this annotation.
 *
 * Experimental attributes of a stable component may be removed at any time without warning.
 * However, other fields of a stable component are safe to use.
 */
@RequiresOptIn(message = "This component or attribute is experimental. It may be changed in the future without notice.")
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION)
annotation class ExperimentalComponent
