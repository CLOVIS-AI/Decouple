# Module core

Component behavior specification.

This module specifies which components are included as part of the Decouple project.
Components are declared as interface methods. Top-level functions are provided to call them easily.

At the root of the Compose call hierarchy, a style instance should be installed.

# Package opensavvy.decouple.core

Overall utilities and entry points for the Decouple component collection.

# Package opensavvy.decouple.core.atom

Small components used to build more complex ones.

## Buttons

> [Specification](opensavvy.decouple.core.atom.actionable.Buttons)

Buttons represent actions that have an impact on the application at large.

|                                                                            |                                                          |
|----------------------------------------------------------------------------|----------------------------------------------------------|
| [Button](opensavvy.decouple.core.atom.actionable.Button)                   | Regular action                                           |
| [PrimaryButton](opensavvy.decouple.core.atom.actionable.PrimaryButton)     | Most important action on a page                          |
| [SecondaryButton](opensavvy.decouple.core.atom.actionable.SecondaryButton) | Other important actions                                  |
| [ContrastButton](opensavvy.decouple.core.atom.actionable.ContrastButton)   | Increased contrast for use on top of complex backgrounds |

## Chips

> [Specification](opensavvy.decouple.core.atom.actionable.Chips)

Chips represent actions localized to the current page, like filters or sorting.

|                                                                          |                                                    |
|--------------------------------------------------------------------------|----------------------------------------------------|
| [AssistChip](opensavvy.decouple.core.atom.actionable.AssistChip)         | Action that always behaves the same way            |
| [SuggestionChip](opensavvy.decouple.core.atom.actionable.SuggestionChip) | Action available only in specific cases            |
| [FilterChip](opensavvy.decouple.core.atom.actionable.FilterChip)         | Filter that can be active or inactive              |
| [InputChip](opensavvy.decouple.core.atom.actionable.InputChip)           | Filter created by the user                         |
| [ChipGroup](opensavvy.decouple.core.atom.actionable.ChipGroup)           | Layout component to align chips that work together |

## Inputs

> [Specification](opensavvy.decouple.core.atom.input.TextFields)

Used to let the user enter some data.

|                                                                             |                                      |
|-----------------------------------------------------------------------------|--------------------------------------|
| [TextField](opensavvy.decouple.core.atom.input.TextField)                   | Text-based inputs                    |
| [InstantField](opensavvy.decouple.core.atom.input.InstantField)             | Timezone-independent instant in time |
| [LocalDateTimeField](opensavvy.decouple.core.atom.input.LocalDateTimeField) | Timezone-dependent instant in time   |
| [LocalDateField](opensavvy.decouple.core.atom.input.LocalDateField)         | Timezone-dependent date              |
| [LocalTimeField](opensavvy.decouple.core.atom.input.LocalTimeField)         | Timezone-dependent time              |

## Text

> [Specification](opensavvy.decouple.core.atom.text.Texts)

Display text on screen.

|                                                |                              |
|------------------------------------------------|------------------------------|
| [Text](opensavvy.decouple.core.atom.text.Text) | Display basic text on screen |

## Miscellaneous indicators

|                                                                     |                                                             |
|---------------------------------------------------------------------|-------------------------------------------------------------|
| [ProgressIndicator](opensavvy.decouple.core.atom.ProgressIndicator) | Visual feedback for actions that take some time to complete |

# Package opensavvy.decouple.core.layout

Components related to layout management.

# Package opensavvy.decouple.core.navigation

Components related to navigation.

This package is not a navigation framework, it just stores a standard way to represent destinations so UI components can list them.
If you're searching for a complete navigation framework, we recommend looking at our `extra-navigation` module.

# Package opensavvy.decouple.core.theme

Visual customization of a UI implementation (light/dark color scheme, typography…).

# Package opensavvy.decouple.core.progression

Management of asynchronous operations.
