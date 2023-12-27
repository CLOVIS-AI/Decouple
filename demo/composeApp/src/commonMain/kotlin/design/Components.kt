package opensavvy.decouple.demo.design

import opensavvy.decouple.components.DesignSystem
import opensavvy.decouple.components.actions.Buttons
import opensavvy.decouple.components.display.Texts

// When using Decouple, you need to explicitly say which components you want to use in your project.
// This allows you to override any component: you can override a component, and all its usages (even if they are
// deep inside other libraries) will automatically use your version instead.
//
// In practice, most apps will create a single interface like this one, that implements DesignSystem (which contains
// the machinery for initializing a design system) as well as all component interfaces the app uses.
// In more complicated projects, or if you want to reuse components between projects, you could put this interface
// into its own module, or even split it into multiple sub-interfaces.
// For this demo, we decided to keep the interface directly in the demo module, and have each implementation live in
// its own platform-specific source set.
interface Components : DesignSystem,
	Buttons,
	Texts

// Now that we have declared which components we want to use, we can write all our common code by creating composable
// extension functions on the Components interface.
// However, many design system implementations only exist on some platforms: for example, Jetpack Compose Material3
// is not available for Compose HTML.
//
// We rely on Kotlin's expect/actual mechanism (https://kotlinlang.org/docs/multiplatform-expect-actual.html) to
// request the existence of a design system. All platforms supported by this project must re-declare this function
// to provide their own implementations. This allows the common code to force all platforms to provide something it lacks,
// letting it build things on top of it.
//
// For this demo, we decided to make it return a list of component implementations; this is because our demo allows
// users to switch between different design systems at runtime.
// In practice, most apps will only support a single design system per platform. If you're in one of these cases,
// you can replace the return type from 'List<Components>' to just 'Components'.
expect fun designSystems(): List<Components>

// If you want to learn more about design systems and how each platform initializes them, you can navigate
// to this file in other source sets (e.g. desktopMain for the Desktop implementation).
// Tip: in IntelliJ, click on the 'A' icon in the margin of the function 'designSystems' to navigate between platforms.

// If you want to learn more about how each platform starts, can read `DesignSelector.kt`
// (in the same folder as the current file).
