# Module core

This module contains the various interfaces of the OpenSavvy UI with no implementation.

A UI implementation is represented by implementing [the UI interface][opensavvy.ui.core.UI].
Individual components are grouped by kind (buttons, inputs…) into their own interface.

## Component variants

Two variants of each component are available, that have the same name:

- a stateless variant is declared in the interface,
- a stateful variant is declared in the package top level.

The stateless variants have no state nor default arguments.
When implementing a component, you will only have to implement these.
Because they are declared as default methods in interfaces, you can easily override any component.

The stateful variants have default arguments and manage their own state wherever possible.
For example, they will automatically set the `loading` attribute to the correct value.
In turn, they delegate their visual representation to the stateless variant.
Because they are available at the package top-level, manage some of their state directly, and have default values for most parameters, they are much more convenient to use in end-user code or to create your own components on top of existing ones.

You may recognize this pattern as [state hoisting](https://developer.android.com/jetpack/compose/state#state-hoisting).
If you have used web-based reactive frameworks before, note that this is not the same as saying stateless components are controlled and stateful components are uncontrolled: in OpenSavvy UI, all components are controlled.
The stateful-ness of standard components refers to their non-primary functions ("is this component loading") but never to their primary function ("display 'Hello'").

# Package opensavvy.ui.core

The core interface.

# Package opensavvy.ui.core.basic

Basic components (buttons, inputs…).

# Package opensavvy.ui.core.layout

# Package opensavvy.ui.core.theme

Visual customization of a UI implementation (light/dark color scheme, typography…).

# Package opensavvy.ui.core.progression

Management of asynchronous operations.
