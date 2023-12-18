# Decouple

[Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) is a declarative UI framework for [Kotlin](https://kotlinlang.org/) built by Google and JetBrains. Greatly inspired by [React](https://react.dev/), Compose bases all its syntax on regular Kotlin constructs, avoiding idiosyncrasies of template languages.

```kotlin
@Composable
fun ShowUsers(users: List<User>) {
    for (user in users) {
        Card {
            Title(user.name)

            if (!user.isActive)
                Icon(Icons.Lock)

            Text(user.biography)
        }
    }
}
```

Each component is a `@Composable`-annotated function, using the regular Kotlin keywords, which automatically re-executes (_recomposes_) when its inputs change.

Because all components are top-level functions, there is no polymorphism: that is, there is no way to switch the implementation of a component. This forces all component libraries to choose different function signatures which makes it hard to use them together (e.g. [Jetpack Compose](https://developer.android.com/jetpack/compose), [Jetpack Glance](https://developer.android.com/jetpack/compose/glance), [Mosaic](https://github.com/JakeWharton/mosaic)…).

With Decouple, components **declare their dependencies at compile-time**:

```kotlin
@Composable
context(MyUIComponents)
fun ShowUsers(users: List<User>) {
    // Everything else is the same
}
```

The caller is responsible for providing an implementation of all declared components, with the ability to replace one by another.
**For example, if a library provided a log-in component based on Decouple, the caller could change the design of all buttons without needing to edit the log-in itself**.

But replacing some components here and there is just the start! We can go much further…

## Decouple your design system from your application

A **design system** is a collection of UI decisions that represent a brand. From fonts, to button shapes, to colors, a design system is a unified set of principles aimed to keep a UI coherent.

If you think about it, you can categorize all UI components you have ever used in two categories:

- **Design components**, which are integrations of the design system: buttons, layouts…
- **Business components**, which contain application-specific behavior: a specific page, a specific form…

Clearly separating these tend to lead to more maintainable code: business components only implement business logic, and contain no design decisions—they simply invoke design components. The design team becomes able to make sweeping changes without risks.

Design components are usually platform-specific: buttons and icons for Android and iOS applications won't look the same, actions will be placed differently on screen. Since business components just call design components, they can themselves be kept entirely multiplatform with no access to design APIs.

This solves the main problem with shared multiplatform code: design primitives on different platforms are just too different. Android's `Modifier` and the web's CSS are unreconciliable—but we can create a design layer abstraction, a set of enough design component's signatures, that we can build our apps upon without ever making design decisions. Each design system has access to the real platform-specific APIs and is free to make any design choice, only having to conform to the Kotlin signature of the common layer.

<details>
<summary>Isn't that the same as Redwood? (click to expand)</summary>

Yes! We share the same goal, but in completely different ways.

[Redwood](https://github.com/cashapp/redwood) is a library to declare a component schemas: the signature of all components of a design system.
At compile-time, the schema is converted into component signatures for supported platforms, which can be implemented using the platform's native toolkit (Android Views, SwiftUI…).

Decouple uses no magic: no annotations, no compiler plugin, only features of the Kotlin language. Decouple also provides no specific manner of calling non-Compose components (you can implement your own appliers, or use Decouple as a thin layer on top of Redwood).

Decouple is not compile-time specific. Using Redwood, only a single design system can be implemented in a single compilation. Decouple doesn't have this limitation: it's possible to change the implementation of components even at runtime.

Let's take a look at the example from the Redwood README:

```kotlin
// Schema declaration
@Widget(2)
data class Button(
    @Property(1) val text: String?,
    @Property(2) @Default("true") val enabled: Boolean,
    @Property(3) val onClick: () -> Unit,
)

// Example implementation with Android Views
class AndroidText(
    override val value: TextView,
) : Text<View> {
    override fun text(text: String?) {
        value.text = text
    }

    override fun color(color: String) {
        value.setTextColor(Color.parseColor(color))
    }
}

// Usage
@Composable
fun Counter(value: Int = 0) {
    var count by remember { mutableStateOf(value) }

    Button("-1", onClick = { count-- })
    Text(count.toString())
    Button("+1", onClick = { count++ })
}
```

Let's adapt the same example to follow the Decouple pattern (the real code is slightly more complex for API backwards-compatibility reasons):

```kotlin
// Declare components
interface Buttons : DesignSystem {
    @Composable
    fun Button(text: String?, enabled: Boolean, onClick: () -> Unit)
}

// Declare an exhaustive list of all components you want to use
interface MyUI : Buttons, Texts // …

// Example implementation using Compose for Web (HTML)
interface DomButtons : Buttons {
    @Composable
    override fun Button(text: String?, enabled: Boolean, onClick: () -> Unit)
}

// Usage
@Composable
context(MyUI)
fun Counter(value: Int = 0) {
    var count by remember { mutableStateOf(value) }

    Button("-1", onClick = { count-- })
    Text(count.toString())
    Button("+1", onClick = { count++ })
}
```

One last difference is that Redwood only provides the basics on which to build upon, whereas we can one step further and attempt to provide compatibility layers for popular design systems.

</details>

With Decouple, you will be able to:

- Write apps that target multiple native platforms (via Jetpack Compose, Compose for Web HTML, Compose for Web Canvas…),
- Reuse components between multiple Compose-related technologies (e.g. unify your code for both Jetpack Compose and Jetpack Glance)
- Write apps with multiple design systems (for example if you adapt your app to your client's brand)
- Create high-level UI libraries that behave the same on all platforms while allowing the end user to entirely customize the look and feel (e.g. log-in flows, settings pages, form validation…)

## What's now?

Currently, Decouple is at fairly early stages. In [our previous version](legacy), we proved that the technology worked. Now, we're reworking the project to follow its ideal structure: we have removed many workarounds we needed because of previous Compose bugs, and we extracted many utilities we built to other repositories.

If you just want to use our syntax without a component library, [you can already do so](https://opensavvy.gitlab.io/decouple/api-docs/polymorphism/index.html).

We are building a component library, but it's not an easy task: we are categorizing all components _by their role, not their looks_. This is important to ensure design decisions are made by the implementation layer. If you'd like to help with that, please get in touch!

[![Decouple on Kotlin Slack](https://img.shields.io/badge/Discuss-KotlinLang%20Slack-4A154B)](https://slack-chats.kotlinlang.org/c/decouple)
[![Decouple on Discord](https://img.shields.io/badge/Discuss-Discord-7289da)](https://discord.com/channels/1170433881174986752/1184240694236299264)
[![Decouple on Matrix/Gitter](https://img.shields.io/badge/Discuss-Matrix-00764A)](https://matrix.to/#/#decouple:gitter.im)

You can also help out by creating Compose compatibility layers for other popular design systems (or reimplementing them from scratch!). For example, we're building a custom [Material3 implementation for the DOM](https://gitlab.com/opensavvy/ui/compose-material3-tailwind). We'd be very interested in compatibility layers for [GTK+](https://www.gtk.org/), [Qt](https://www.qt.io/) or other native UI frameworks. Or, of course, you can contribute to this repository directly—documentation is sparse at the moment, so we recommend getting in touch.

The most up-to-date information can be found [in our documentation](https://opensavvy.gitlab.io/decouple/api-docs/index.html) or [in our issue tracker](https://gitlab.com/opensavvy/decouple/-/issues).

## License

This project is licensed under the [Apache 2.0 license](LICENSE).

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md).
- To learn more about our coding conventions and workflow, see the [OpenSavvy Wiki](https://gitlab.com/opensavvy/wiki/-/blob/main/README.md#wiki).
- This project is based on the [OpenSavvy Playground](docs/playground/README.md), a collection of preconfigured project templates.

## Sponsors

[![4SH Logo](https://www.4sh.fr/assets/img/svg/4sh_logo.svg)](https://www.4sh.fr/)
