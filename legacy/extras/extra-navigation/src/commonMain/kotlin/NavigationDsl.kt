package opensavvy.decouple.navigation

import androidx.compose.runtime.Composable
import opensavvy.decouple.core.navigation.NavigationDsl
import opensavvy.decouple.core.navigation.NavigationDslMarker

@NavigationDslMarker
@Composable
fun NavigationDsl<Destination>.page(destination: Destination) {
    page(destination.title, destination)
}

//region Composable destination

private class ComposableDestination(
    override val route: String,
    override val title: String,
    override val parent: Destination?,
    val renderFunction: @Composable () -> Unit,
) : Destination {

    @Composable
    override fun render() {
        renderFunction()
    }
}

@NavigationDslMarker
@Composable
fun NavigationDsl<Destination>.page(
    route: String,
    title: String,
    parent: Destination?,
    block: @Composable () -> Unit,
) {
    page(title, ComposableDestination(route, title, parent, block))
}

//endregion
