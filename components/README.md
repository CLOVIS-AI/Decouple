# Module Component library

Standardized component list without design choices.

<a href="https://search.maven.org/search?q=dev.opensavvy.decouple.components"><img src="https://img.shields.io/maven-central/v/dev.opensavvy.decouple/components.svg?label=Maven%20Central"></a>
<a href="https://opensavvy.dev/open-source/stability.html"><img src="https://badgen.net/static/Stability/experimental/purple"></a>
<a href="https://javadoc.io/doc/dev.opensavvy.decouple/components"><img src="https://badgen.net/static/Other%20versions/javadoc.io/blue"></a>

## Polymorphic components

Decouple is founded on the ability to switch the implementation of composable functions at runtime.
This allows creating business-level UI code that is design-less, but calls design-full UI code that can be replaced at any time.

The technical details of how this is implemented are explained in the "Polymorphic composables" module.

# Package opensavvy.decouple.components

Common utilities and annotations that relate to all components.

# Package opensavvy.decouple.components.actions

Action components help people achieve an aim.

## Buttons

> [Specification](opensavvy.decouple.components.actions.Buttons)

Buttons represent actions that have an impact on the application at large.

|                                                                            |                                 |
|----------------------------------------------------------------------------|---------------------------------|
| [`Button`](opensavvy.decouple.components.actions.Button)                   | Basic interaction               |
| [`PrimaryButton`](opensavvy.decouple.components.actions.PrimaryButton)     | Most important action on a page |
| [`SecondaryButton`](opensavvy.decouple.components.actions.SecondaryButton) | Important actions               |
| [`ActionButton`](opensavvy.decouple.components.actions.ActionButton)       | Alternative actions             |

# Package opensavvy.decouple.components.concepts

Explanations of various concepts and decisions we made.

All symbols in this package are empty type aliases that are used as a place to put cross-referencable documentation.
We are not expecting these type aliases to be used anywhere outside of documentation comments.

# Package opensavvy.decouple.components.display

Non-interactive components that provide information to the user.

## Text

> [Specification](opensavvy.decouple.components.display.Texts)

Text is the most important way to communicate static information to the user.

|                                                      |                         |
|------------------------------------------------------|-------------------------|
| [`Text`](opensavvy.decouple.components.display.Text) | Simple unformatted text |

# Package opensavvy.decouple.components.layouts

Components used to specify the visual position of other components.

## Column and Row

> [Specification](opensavvy.decouple.components.layouts.LinearLayouts)

`Column` and `Row` are simple linear layouts: components are laid on top of each other.

|                                                          |                                                |
|----------------------------------------------------------|------------------------------------------------|
| [`Column`](opensavvy.decouple.components.layouts.Column) | Lay components vertically below each other     |
| [`Row`](opensavvy.decouple.components.layouts.Row)       | Lay components horizontally next to each other |
