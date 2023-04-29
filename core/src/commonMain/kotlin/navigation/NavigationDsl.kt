package opensavvy.decouple.core.navigation

import androidx.compose.runtime.Composable
import kotlin.experimental.ExperimentalTypeInference

@DslMarker
annotation class NavigationDslMarker

/**
 * DSL to create a [NavigationMenu], see [navigationMenu].
 */
@NavigationDslMarker
interface NavigationDsl<P> {

    @NavigationDslMarker
    @Composable
    fun page(title: String, payload: P)

    @NavigationDslMarker
    @Composable
    fun menu(title: String, block: @Composable NavigationDsl<P>.() -> Unit)

}

/**
 * Creates a new [NavigationMenu].
 *
 * This function is [composable][Composable] to allow the pages to adapt to the screen values.
 */
@OptIn(ExperimentalTypeInference::class)
@NavigationDslMarker
@Composable
fun <P> navigationMenu(@BuilderInference block: @Composable NavigationDsl<P>.() -> Unit): NavigationMenu.Menu<P> {
    val dsl = NavigationDslImpl<P>()
    block(dsl)

    return NavigationMenu.Menu("Root", dsl.elements)
}

private class NavigationDslImpl<P> : NavigationDsl<P> {
    val elements = ArrayList<NavigationMenu<P>>()

    @Composable
    override fun page(title: String, payload: P) {
        elements += NavigationMenu.Page(title, payload)
    }

    @Composable
    override fun menu(title: String, block: @Composable NavigationDsl<P>.() -> Unit) {
        val dsl = NavigationDslImpl<P>()
        block(dsl)

        elements += NavigationMenu.Menu(title, dsl.elements)
    }
}
