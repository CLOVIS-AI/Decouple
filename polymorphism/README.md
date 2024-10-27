# Module Polymorphic Compose

Machinery of the polymorphic composable functions.

<a href="https://search.maven.org/search?q=dev.opensavvy.decouple.polymorphism"><img src="https://img.shields.io/maven-central/v/dev.opensavvy.decouple/polymorphism.svg?label=Maven%20Central"></a>
<a href="https://opensavvy.dev/open-source/stability.html"><img src="https://badgen.net/static/Stability/stable/purple"></a>
<a href="https://javadoc.io/doc/dev.opensavvy.decouple/polymorphism"><img src="https://badgen.net/static/Other%20versions/javadoc.io/blue"></a>

## Why?

The Compose UI style guide recommends composable functions to be top-level.
If we wanted to have a composable function that has multiple different looks, we would do it
through `expect`/`actual` to have a different implementation for each Kotlin platform.

However, this is too limiting for the Decouple project: our goal is to have multiple design systems available for each platform.

## Two types of UI components

We consider that there exist two types of UI components:
- Design elements,
- Business components.

Design elements are components that have no business meaning. They are the components we think of the most easily: buttons, chips, styled texts, layouts… **When the business rules change, these components are not modified.** However, the design team may decide to change their appearance at any time.

Business components are the opposite: they have business meaning: for example, a specific screen in your application, the user's profile… They are normally composed of design elements, but do not contain code that relates to the design system themselves (other than some basic layout information).
When the business rules change, they are modified. However, **when the design system changes, they are not modified.** Of course, their appearance changed—but the code modifications happened in the design elements they call, not directly in them.

An application typically contains a mix of both types: for example, we can imagine a global navigation element (design) that calls a composable that represents the current screen (business), which itself uses an article layout with a title and subtitle (design), and places some image, some textual information and an edit button (all design).

Our goal is to allow users to switch between design systems at run-time; we therefore have to treat these types of components differently.

> This module contains the machinery to create these types of components
> with Compose.
> It is separate on purpose such that this pattern can be reused by projects
> who do not want to use the rest of the Decouple project.

## Design components

Design components expose a behavior API that multiple design systems can implement in any way they want. When invoked, the caller selects one of the existing design systems (or creates their own).

To do this, they are declared in two parts:

- a low-level specification, in the form of an interface that implements [PolymorphicComponent][opensavvy.decouple.polymorphism.PolymorphicComponent],
- a high-level helper, in the form of a top-level function, that implements common behavior for all design systems.

For example, here's a simple example of what a button could look like:
```kotlin
interface Buttons : PolymorphicComponent {

	@Composable
	fun ButtonSpec(title: String, onClick: () -> Unit, disabled: Boolean)
}

@Composable
fun Buttons.Button(
	title: String,
	onClick: () -> Unit,
	disabled: Boolean = false,
) {
	ButtonSpec(title, onClick, disabled)
}
```

Here, the only difference of the top-level function is the addition of the default parameter, but in practice, they are often quite different. For example, the top-level function may expose a DSL, sending only the realized value to the specification.

Notice how the top-level function is an extension on the `Buttons` interface? This enforces a compile-time verification that the design system used by the application does indeed provide a button implementation.

> In the future, the extension receiver will become a [context receiver](https://github.com/Kotlin/KEEP/blob/master/proposals/context-receivers.md).

## Component dependencies

Let's imagine that we have an application that is compiled both as an Android app, and a CLI program.
These two platforms have vastly different design considerations; the most visible of which being that CLI programs are extremely limited when it comes to layout.

To share code between these parts of the application, we can create the following interfaces:
```kotlin
interface CommonUI : Buttons, StyledText, LazyLayouts
interface CLI : CommonUI, AnsiColors
interface DesktopUI : CommonUI, Cards, SystemMenus
```

Because all components are implemented as extension functions on either one of these three interfaces, it is immediately clear (and compile-time checked) that all components are only called in contexts where the appropriate components are available.

This pattern of declaring an interface for each application variant that simply subtypes all the components this variant uses, is the core idea of Decouple. These interfaces become the source of truth for which components are needed by an application.

This enables much more code-reuse. If you think about it, the desktop system toolbar menus and submenus are conceptually identical to lists of items, separators and buttons that we could find anywhere else on screen. With Decouple, these are simply considered a different design systems, but the code is the same.

## Business components

As we saw in the previous sections, design elements are implemented as extension functions on their respective specification interface, and there exist central interfaces that declare all the components used by the entirety of the application.

Business components do not need their own visual appearance to switch based on the design system, since all they do is call design elements (which themselves switch) and other business documents. They therefore do not require the specification interface.

Business components therefore follow the same rules as regular Composable functions—except that they have a receiver on one of the centralized interfaces. 
For example, here is an imaginary user profile:
```kotlin
// Somewhere else in the application, we have declared the list of components
// we want to use.
interface FooComponents : Texts, Buttons, LinearLayouts, Images

@Composable
fun FooComponents.UserProfile(user: User) {
	Row {
		SmallImage(user.profilePicture)
		Title(user.name)
	}
}
```

We automatically have access to all components we declared, and cannot accidentally call any other.

## Implementing a design component specification

As we saw previously, design components declare a specification interface, but we didn't mention how to implement that interface.

A tricky concern is that specification interfaces can have dependencies on other specification interfaces. For example, a button will probably need to call a loading indicator when an action is in-progress—but if the user selects a different loading indicator design, we want the loading indicator that appears inside the button to respect that!

To be able to get this to work, we need to be a bit creative. **We implement design components as interfaces too.** For example, to implement the earlier example (where "Magic" is an imaginary design system):
```kotlin
interface MagicButtons : Buttons {
	
	@Composable
	override fun ButtonSpec(title: String, onClick: () -> Unit, disabled: Boolean) {
		// …
	}
}
```

> When generating these overrides with IntelliJ, it forgets the `@Composable` annotation, which is necessary (otherwise, the code doesn't compile).

This ensures that all component implementations are combined into a single design system in which all implementations have access to all others, if needed.

If a dependency on another component is optional, it is also possible to test its presence with `if (this is OptionalCompoennt)`, though we recommend mandatory dependencies on most cases.

## Creating a design system

Creating a design system is therefore just the combination of multiple specification implementation interfaces into a single object, to conform to the centralized interface required by the application. Since everything is an interface…
```kotlin
object MagicDesignSystem :
	DesktopUI,    // implement the variant of the centralized implementation you want
	MagicButtons, // next, we delegate the implementation of all components to the spec interfaces
	MagicStyledTexts,
	MagicLinearLayouts,
	MagicCards,
	MagicSystemMenus
```

When our application starts, we can simply bring the material design into scope to be able to call any other component:
```kotlin
@Composable
fun AppEntrypoint() {
	with(MagicDesignSystem) {
		UserProfile(/* … */)
	}
}
```

Finally, because all design systems have, by definition, a common interface (in this example, one of `CommonUI`, `DesktopUI` or `CLI`), it is trivial to write code that selects between multiple of them to switch the design system at runtime.

## Bonus: NoUI

Have you ever wanted to accept a `@Composable` lambda parameter in a function you're writing, but have hesitated because it would allow invoking UI components, and it would make no sense in the place where you are using it?

Well, the pattern we're using here means that all components are naturally scoped to a single interface. Through a bit of [DslMarker][kotlin.DslMarker] magic, we can create an object that forbids using any component, which we call [NoUI][opensavvy.decouple.polymorphism.NoUI]:
```kotlin
@Composable
fun DesktopUI.HomePage(
	// …
	content: @Composable () -> Unit,
	state: @Composable NoUI.() -> Unit,
) {
	// …
}
```
In this example, the `content` parameter accepts a lambda that can call any composable function, as well as any component declared in `DesktopUI` (because it will be available on the call-site). However, the `state` parameter only accepts calls to non-component composable functions (e.g. `remember`) but isn't allowed to call any UI component!
