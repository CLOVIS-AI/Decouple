# Frequently Asked Questions

Welcome to the OpenSavvy Decouple FAQ. The Compose ecosystem is currently quite disorganized, so it can be hard to understand how all pieces fit together; hopefully this article can help clear it up.

## What was the context around the creation of Decouple?

After a background in Java development, I became interested in Kotlin and its promises of multiplatform always-native development in late 2017. Since then, most of the Kotlin code I wrote has been shared between the JVM and JS targets, but was always some kind of library or CLI app that wouldn't need a proper UI. I didn't love the XML-based imperative UIs like JavaFX or Android Views.

When the first news of Compose started coming out, I was hooked. In 2021, I created the [Formulaide project](https://gitlab.com/opensavvy/formulaide), a fullstack Kotlin form editor, for a town hall in south-west France. Compose wasn't yet stable, even for Android, so I picked up the [Kotlin React wrapper](https://github.com/JetBrains/kotlin-wrappers/tree/master/kotlin-react). The reactive model was a revelation with how well it fit a developer's expectation of how UI should be written—no more templates, no more new syntax to learn to bind data, the entire UI was now plain old code. However, React was not built from Kotlin, and the limitations were felt (React compares objects by identity instead of using `equals`, the syntax to declare props is verbose leading to larger components than advised, the rule of hooks in particular is unnatural). Meanwhile, Compose had none of these issues, and was slowly maturing.

In 2022, a rewrite of Formulaide was started to fix shortcomings of the first version. With my now complete understanding of the project's scope, Compose being stable, and Compose for Web being stable enough to be an option, all the stars aligned. Three things were missing, though: an efficient caching and error-handling library, a way to share components between platforms to prepare the possibility of an Android app, and an idiomatic implementation of Material Design 3 for the web with Compose. I would solve the first by creating the [Pedestal collection](https://gitlab.com/opensavvy/pedestal). The later two, however, were not so simple: Decouple was born.

Decouple would be based from its inception on [context receivers](https://github.com/Kotlin/KEEP/blob/master/proposals/context-receivers.md), the spiritual successor to the much-loved [KEEP-87](https://github.com/Kotlin/KEEP/pull/87), which embraced Kotlin's nature to write typesafe DSLs to bring it to its next stage. Decouple would be able to abstract over the API differences of any Compose component library, allowing them to cohabit in a single project. It would be possible to write an application once and run it with entirely different UI libraries, making it possible to always be native no matter the platform or design style requested by a client. A friend would help me implement Material Design 3 from scratch for Kotlin/JS to provide an out-of-the-box implementation of the API.

## How can I contribute?

Thanks for reading this entire article. If you still have more questions, you can contact us on the [official Kotlin Slack](https://kotl.in/slack) in the [#decouple](https://kotlinlang.slack.com/archives/C04QPSCQ39T) channel.

The easiest way to contribute if you have used other Compose-based projects is to help us fine-tune our implementations for existing platforms, such as Android. You can search our [issue tracker](https://gitlab.com/opensavvy/decouple/-/issues/) for tasks related to specific platforms (e.g. [Android](https://gitlab.com/opensavvy/decouple/-/issues/?label_name%5B%5D=material-androidx)). You can also use our issue tracker to report bugs or request features. We also appreciate help with documentation, or reports of problems with our implementations (e.g. accessibility).

Before starting to work on any major feature, component addition or platform addition, please create an issue to ensure we are on the same page on how it should be done. Platform additions in particular, require that we are able to maintain the platform long-term, by compiling it in CI, etc. Otherwise, it risks being dropped in the future if we find no one to maintain it.

We are also grateful for blog articles mentioning the project, but please mention to your users that Decouple is not ready for production use.

> If we forget to remove this disclaimer when Decouple eventually goes stable, please [report it to us](https://gitlab.com/opensavvy/decouple/-/issues/new).

For a more thorough description of how to send us modifications, please read our [contribution guide](CONTRIBUTING.md).
