# Frequently Asked Questions

Welcome to the OpenSavvy Decouple FAQ. The Compose ecosystem is currently quite disorganized, so it can be hard to understand how all pieces fit together; hopefully this article can help clear it up.

## What was the context around the creation of Decouple?

After a background in Java development, I became interested in Kotlin and its promises of multiplatform always-native development in late 2017. Since then, most of the Kotlin code I wrote has been shared between the JVM and JS targets, but was always some kind of library or CLI app that wouldn't need a proper UI. I didn't love the XML-based imperative UIs like JavaFX or Android Views.

When the first news of Compose started coming out, I was hooked. In 2021, I created the [Formulaide project](https://gitlab.com/opensavvy/formulaide), a fullstack Kotlin form editor, for a town hall in south-west France. Compose wasn't yet stable, even for Android, so I picked up the [Kotlin React wrapper](https://github.com/JetBrains/kotlin-wrappers/tree/master/kotlin-react). The reactive model was a revelation with how well it fit a developer's expectation of how UI should be written—no more templates, no more new syntax to learn to bind data, the entire UI was now plain old code. However, React was not built from Kotlin, and the limitations were felt (React compares objects by identity instead of using `equals`, the syntax to declare props is verbose leading to larger components than advised, the rule of hooks in particular is unnatural). Meanwhile, Compose had none of these issues, and was slowly maturing.

In 2022, a rewrite of Formulaide was started to fix shortcomings of the first version. With my now complete understanding of the project's scope, Compose being stable, and Compose for Web being stable enough to be an option, all the stars aligned. Three things were missing, though: an efficient caching and error-handling library, a way to share components between platforms to prepare the possibility of an Android app, and an idiomatic implementation of Material Design 3 for the web with Compose. I would solve the first by creating the [Pedestal collection](https://gitlab.com/opensavvy/pedestal). The later two, however, were not so simple: Decouple was born.

Decouple would be based from its inception on [context receivers](https://github.com/Kotlin/KEEP/blob/master/proposals/context-receivers.md), the spiritual successor to the much-loved [KEEP-87](https://github.com/Kotlin/KEEP/pull/87), which embraced Kotlin's nature to write typesafe DSLs to bring it to its next stage. Decouple would be able to abstract over the API differences of any Compose component library, allowing them to cohabit in a single project. It would be possible to write an application once and run it with entirely different UI libraries, making it possible to always be native no matter the platform or design style requested by a client. A friend would help me implement Material Design 3 from scratch for Kotlin/JS to provide an out-of-the-box implementation of the API.

## What are the high level goals of Decouple?

At its core, Decouple uses [context receivers](https://github.com/Kotlin/KEEP/blob/master/proposals/context-receivers.md) to allow Composable functions to declare compile-time dependencies on other composables they expect to be available. These dependencies are satisfied using [interface delegation](https://kotlinlang.org/docs/delegation.html), allowing applications to easily substitute some components by an alternative version without having to rewrite all components.

Because Decouple is interface-based, and not `expect`/`actual`-based, it is possible to provide multiple implementations of components for a single platform, allowing to build a single application that has multiple versions that run on the same platform, for example to develop white-label applications that can have entirely different styles.

However, we do not believe it is possible to provide a library as low-level as Jetpack Compose, because platforms are too different from each other. We believe this is the main reason why the Compose landscape is currently so incompatible. We also believe this isn't a real issues, as UI code should be split up in two distinct layers:

- the **application UI** declares the "business logic" of the UI, and as such only declares domain-specific components (e.g. `UserList`, `PostEditor`). For better maintainability and easier designing, we believe this layer **should not** have access to low-level graphics primitives,
- the **design system** declares components that are not domain-specific and are expected to be used in exactly the same way across all applications (e.g. `Button`, `GlobalNavigation`). The implementation of such components does require low-level graphics primitives, but should not be aware of business contraints.

Decouple lives between these layers, to ensure any application UI can be built on top of any design system, while providing the exact same experience and functionality, even though the visual appearance may be different. It is a major goal of Decouple to always ensure any project can entirely replace provided components or add new ones, because all projects are different—if Decouple provides _most_ of the components you use, and not _all_, it's still a major increase in your usage time.

Between these layers, Decouple is in a privileged place to add common functionality to all design systems. For example, Decouple adds automatic progress reporting to any component's events based on coroutines (e.g. a button's `onClick` is `suspend` and automatically displays a loading indicator while the operation is not done). Decouple also provides a headless testing library used to test the behavior of the application layer and its responses to events, which is a good supplement to platform-specific snapshot testing (efficient to test the design system and accessibility, but inconvenient to test the user's workflow and the validity of displayed data).

Decouple also provides opt-in utilities (called "extras") to interface with other tools useful for all Compose-based projects, such as multiplatform wrappers on top of persistent storage, to remember a page's state between sessions, or helpers to use Arrow error-handling in Compose component events.

Ultimately, this means Decouple is only limited in what it can support by what Kotlin itself support, and what the Compose compiler and runtime support. Decouple could in theory be implemented on top of these platforms, for example to provide native applications that use GTK or KDE on Linux desktops, etc. Of course, these implementations are a massive undertaking, and are not planned for the short-term (we do welcome contributions!).

## But, context receivers are not yet available?

> If context receivers are available on all platforms we support when you read this, please [report to us](https://gitlab.com/opensavvy/decouple/-/issues/new) that we forgot to update this page!

Until context receivers are available, we are emulating them with [composition locals](https://developer.android.com/jetpack/compose/compositionlocal). Composition locals are not type-safe (components can not declare compile-time dependencies on a subset of the available components), and force the existence of a god-interface, `UI`, which declares all components of Decouple. This interface has the major downside that any modification to any component (including the introduction of new ones) is a binary breaking change for all users of Decouple, as well as source breaking change for all implementations of the `UI` interface. It also stops downstream users to declare their own components (since they would need to be added to the `UI` interface).

When context receivers are available, `UI` will be removed (or at least discouraged) and all [stateful component variants](https://opensavvy.gitlab.io/decouple/documentation/documentation/explanations/-component%20variants/index.html) will declare their dependencies using context receivers. Each project using Decouple will be expected to declare its own interface declaring which components it plans to use (implementing them by delegation to provided or custom implementations), thus ensuring component additions in Decouple itself will not lead to breaking changes in user projects, as users can decide when (and if) they introduce them to their component surface.

## How can I contribute?

Thanks for reading this entire article. If you still have more questions, you can contact us on the [official Kotlin Slack](https://kotl.in/slack) in the [#decouple](https://kotlinlang.slack.com/archives/C04QPSCQ39T) channel.

The easiest way to contribute if you have used other Compose-based projects is to help us fine-tune our implementations for existing platforms, such as Android. You can search our [issue tracker](https://gitlab.com/opensavvy/decouple/-/issues/) for tasks related to specific platforms (e.g. [Android](https://gitlab.com/opensavvy/decouple/-/issues/?label_name%5B%5D=material-androidx)). You can also use our issue tracker to report bugs or request features. We also appreciate help with documentation, or reports of problems with our implementations (e.g. accessibility).

Before starting to work on any major feature, component addition or platform addition, please create an issue to ensure we are on the same page on how it should be done. Platform additions in particular, require that we are able to maintain the platform long-term, by compiling it in CI, etc. Otherwise, it risks being dropped in the future if we find no one to maintain it.

We are also grateful for blog articles mentioning the project, but please mention to your users that Decouple is not ready for production use.

> If we forget to remove this disclaimer when Decouple eventually goes stable, please [report it to us](https://gitlab.com/opensavvy/decouple/-/issues/new).

For a more thorough description of how to send us modifications, please read our [contribution guide](CONTRIBUTING.md).
