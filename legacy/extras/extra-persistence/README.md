# Module extra-persistence

Utilities for persisting Compose states.

|              |                                                     |
|--------------|-----------------------------------------------------|
| Stability    | Alpha: expect missing features and breaking changes |
| Dependencies | Compose                                             |

Decouple Persistence is a lightweight utility to store Compose state across application restarts.

## Usage

To persist state, simply replace the standard Compose state declaration:

```kotlin
var value by remember { mutableStateOf(0) }
```

by our persistent state declaration:

```kotlin
var value by remember { persistentStateOf("your.app.value") { 0 } }
```

As standard with Compose state management, a persistent state is usable outside composables (for example in your view models).
For more information, see the [`persistentStateOf`](opensavvy.decouple.persist.persistentStateOf) documentation.

**Platform support**. To request support for another platform, please [create an issue](https://gitlab.com/opensavvy/decouple/-/issues/new) and describe how persistence can be implemented on the platform, or submit a new merge request directly to the project.
