# Module core

Component behavior specification.

This module specifies which components are included as part of the Decouple project.
Components are declared as interface methods. Top-level functions are provided to call them easily.

At the root of the Compose call hierarchy, a style instance should be installed.

# Package opensavvy.decouple.core

Overall utilities and entry points for the Decouple component collection.

# Package opensavvy.decouple.core.atom

Small components used to build more complex ones.

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
