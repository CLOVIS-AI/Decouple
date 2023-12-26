# Module Design system: Headless

Design system that emits no UI, useful for automated tests.

<a href="https://search.maven.org/search?q=dev.opensavvy.decouple.design-headless"><img src="https://img.shields.io/maven-central/v/dev.opensavvy.decouple/design-headless.svg?label=Maven%20Central"></a>

<a href="https://gitlab.com/opensavvy/wiki/-/blob/main/stability.md#stability-levels"><img src="https://badgen.net/static/Stability/experimental/purple"></a>

## Testing the state, not the appearance

When using Decouple, the appearance of your app may differ from one UI implementation to another, or from one platform to another. There is no multiplatform way of testing the exact appearance of the UI.

However, as Decouple abstracts all the state and behavior of the UI, it is possible to test how components interact and their internal values. Although this is somewhat less informative than appearance testing, it is much easier to read, less likely to break with refactors, and works identically on all platforms.

This module allows you to:

- test that pressing a button will execute a network request,
- test that a button is disabled when form fields are invalid,
- test that the data in a table corresponds to what the server sent.

This module does not help to:

- test the visual appearance of a button,
- test whether an implementation of the Decouple component library is correct.

## Reading order

To create headless UI tests, please read [HeadlessUI](opensavvy.decouple.headless.execution.runHeadlessUI). If you use the [Prepared test framework](https://gitlab.com/opensavvy/prepared), see the corresponding compatibility module instead.

To create typesafe wrappers to allow other users to test your components, please read [Component][opensavvy.decouple.headless.Component].

# Package opensavvy.decouple.headless

The [Component](opensavvy.decouple.headless.Component) class allows to declare typesafe immutable objects that wrap composable function calls.

# Package opensavvy.decouple.headless.execution

[HeadlessUI](opensavvy.decouple.headless.execution.HeadlessUI) provides utilities to run a composable function in a test environment without starting an entire UI environment.

# Package opensavvy.decouple.headless.node

Representation of the state of composable functions during their execution.

# Package opensavvy.decouple.headless.debug

Utilities for analyzing running compose functions.
