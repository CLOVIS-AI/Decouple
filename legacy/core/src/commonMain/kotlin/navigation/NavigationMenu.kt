package opensavvy.decouple.core.navigation


sealed class NavigationMenu<out P> {

    abstract val title: String

    abstract fun asPrefixSequence(): Sequence<NavigationMenu<P>>

    open fun firstPage(): Page<P>? = asPrefixSequence()
        .firstNotNullOfOrNull { it as? Page }

    open fun find(predicate: (NavigationMenu<P>) -> Boolean): NavigationMenu<P>? = asPrefixSequence()
        .find(predicate)

    open fun payloads(): Sequence<P> = asPrefixSequence()
        .mapNotNull { if (it is Page) it.payload else null }

    data class Page<out P>(
        override val title: String,
        val payload: P,
    ) : NavigationMenu<P>() {

        override fun asPrefixSequence() = sequenceOf(this)

        override fun firstPage() = this

        override fun find(predicate: (NavigationMenu<P>) -> Boolean) =
            this.takeIf(predicate)
    }

    data class Menu<out P>(
        override val title: String,
        val children: List<NavigationMenu<P>>,
    ) : NavigationMenu<P>() {

        override fun asPrefixSequence() = sequence {
            yield(this@Menu)
            yieldAll(children.asSequence().flatMap { it.asPrefixSequence() })
        }

        override fun firstPage() = children
            .firstNotNullOfOrNull { it.firstPage() }
    }
}
