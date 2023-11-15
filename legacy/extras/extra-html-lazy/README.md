# Module extra-html-lazy

Compose HTML-compatible lazy column and lazy row implementations.

|              |                                                                                                         |
|--------------|---------------------------------------------------------------------------------------------------------|
| Stability    | Beta: expect missing features, we will help with migration                                              |
| Dependencies | Compose HTML, [Kotlin Browser](https://github.com/JetBrains/kotlin-wrappers/tree/master/kotlin-browser) |

Provides a Compose HTML implementation of [LazyColumn](opensavvy.decouple.extra.html.lazy.LazyColumn) and [LazyRow](opensavvy.decouple.extra.html.lazy.LazyRow). Both are implemented using the Compose HTML conventions (first arguments is an `attrs` block) to ensure they feel like any other DOM element, and can be styled however you want.
