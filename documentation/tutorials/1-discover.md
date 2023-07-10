# Introduction to Compose with Decouple

This tutorial is a light introduction to the most important concepts of Compose: syntax, recomposition and state management. It is written for users who are already familiar with the Kotlin syntax, but are not familiar with Compose or Decouple.

Pre-requisites:

- Java 17 (or more recent)
- Basic knowledge of IntelliJ IDEA (Community or Professional) or Android Studio: running programs, opening Gradle projects, autocompletion…
  - Decouple can be used in any other IDE, but this tutorial will assume usage of one of these
- Basic knowledge of the Kotlin language and syntax
- Basic knowledge of Git

No Compose prior knowledge is necessary.

## Cloning the project

For this tutorial, we will avoid setting up a project. Instead, we will clone the Decouple project itself and edit its demo app.

First, clone the project:

```shell
git clone https://gitlab.com/opensavvy/decouple.git
cd decouple
```

In IntelliJ IDEA or Android Studio, open a new project, and select the `settings.gradle.kts` at the root of the project. When prompted, select "open as project". Wait until the IDE is done indexing the project.

<details>
<summary>Troubleshooting: Could not resolve com.android.tools.build:gradle</summary>

Ensure you are running the project with Java 17 or more recent.

To configure your IDE, go into "File → Project Structure → Project" and ensure the selected SDK is at least Java 17.
If you have no suitable Java installation, use "Add SDK" option in the dropdown to let IntelliJ install a new Java SDK.

Then, ensure Gradle is configured to use it: go into "File → Settings → Build, Execution, Deployment → Build tools → Gradle" and ensure the "Gradle JVM" at the bottom of the screen is set to "Project SDK".

</details>
<details>
<summary>Troubleshooting: Invalid Android plugin version</summary>

The project needs to be configured with the same version of the Android plugin as your IDE uses.
If you see this message, it means your IDE uses a different version of the Android plugin than the one we use.
If the difference between both versions is small, it is probably safe for you to override the version we configured.

To override it, open the [gradle/libs.versions.toml](../../gradle/libs.versions.toml) file, and replace the version number in the line starting by `android = ` by the version suggested by the error message.

After editing the file, the IDE should prompt you to "reload the project", "reload configuration changes" or similar wording. Confirm to apply the modifications.

</details>

To ensure everything works, run the Desktop demo app by executing "Demo (desktop) » Run" on the top-right of the IDE, or running `./gradlew demo:desktop:run` in your terminal.

## Prepare the project

When the app started, it opened by default on the "Home" page, which presents Decouple.
In this tutorial, we will create a simple counter which displays an integer, with a '+' and '-' button.

The demo app is used as living documentation of the Decouple project. It is structured as follows:

```text
demos/
  demo-shared/src/   ← Code shared between all platforms
  demo-android/src/  ← Configuration specific to Android
  demo-desktop/src/  ← Configuration specific to the Desktop
  demo-web/src/      ← Configuration specific to the web version
```

In this tutorial, we will only write multiplatform code. For now, we will execute it on the Desktop (because it needs less configuration), but we will execute the other platforms later.

Open the [Home.kt](../../demo/src/commonMain/kotlin/Home.kt) file in `demo/src/commonMain/kotlin`. Follow the instructions in the file to enable the basic counter. After uncommenting, follow the highlighted warnings by the IDE to import the relevant functions.

When running the Desktop app again, you should see a simple counter with two buttons. However, clicking the buttons does nothing.

## Components are functions

Let's take a second to explain what we currently have.

Compose components are functions annotated with the `@Composable` annotation.
As a convention, UI-emitting `@Composable` functions start with an upper-case letter.
Our counter component therefore looks like this:

```kotlin
@Composable
fun Counter() {
  // Here, we can call any component
}
```

First, we display our counter horizontally using the `Row` component:

```kotlin
@Composable
fun Counter() {
  Row {
    // All components called here will be aligned horizontally 
  }
}
```

To avoid accidentally calling components outside the `Row`, and to reduce the indentation levels, we call the `Row` component directly at the end of the first line:

```kotlin
@Composable
fun Counter() = Row {
          // All components called here will be aligned horizontally
        }
```

We can now introduce two new components: `Button` and `Text`, which should be self-explanatory.

## State management

So-called "reactive" frameworks detect changes to the UI and update it automatically. There are two ways of doing so:

- The Angular family observes various events (button clicks, network requests…) and recalculates all values of an application to search for changes. This is easier to learn for developers, but is hard to optimize when performance is necessary.
- The React family encapsulates all state changes inside a wrapper object which notifies the framework of modifications to its contained value. This wrapper is often called "state" or "signal". These frameworks encourage immutability by only allowing mutation between frames of rendering.

Compose is part of the React family: all value changes must happen within a [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State) or [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState) instance. At compile-time, Compose tracks which parts of the codebase reads which state objects. At run-time, the state objects communicate changes in their values to Compose, which therefore knows exactly which parts of the UI needs to be updated.

Unlike in frameworks like Angular or React, where updates are always done at the component-level, Compose detects updates at the code block level, so if the values that changed are only read inside an `if` statement, only the inside of that statement is recomputed.

In our example, we want to edit the counter's value whenever a button is clicked. For now, we'll use a global state.

```kotlin
// Create a mutable State value with 0 as an initial value
// Whenever it is modified, Compose will detect which components needs to be redrawn automatically
val counter = mutableStateOf(0)

@Composable
fun Counter() = Row {
  Button(onClick = { counter.value-- }) {
    Text("-")
  }

  Text("${counter.value}")

  Button(onClick = { counter.value++ }) {
    Text("+")
  }
}
```

If we run the application again, we will see that clicking on the buttons does indeed update the value.
Whenever the state changes, Compose executes the `Counter` function again (we call it _recomposing_).
However, because we declared the state as a global variable, it is shared between all instances of `Counter`: if we display two counters, they will always display the same value.

## Storing state through recomposition

Let's state by moving the state declaration inside the function:

```kotlin
@Composable
fun Counter() = Row {
          val counter = mutableStateOf(0)

          Button(onClick = { counter.value-- }) {
            Text("-")
          }

          Text("${counter.value}")

          Button(onClick = { counter.value++ }) {
            Text("+")
          }
        }
```

Rerun the app, and notice that… it doesn't work anymore. Yet, if we add a `println` call inside the component, we will see that it is re-executed each time a button is pressed, meaning Compose does detect that the UI needs to be updated.

This is one of the most common mistakes made by beginners when using Compose for the first time, so it is worth understanding exactly what is going on. We first declare a mutable state with value 0, then display all the buttons. When a button is pressed, it changes the value of the state, which Compose detects. Compose then recomposes `Counter`: the functions starts by creating a new mutable state with value 0, then displays all the buttons.

Conceptually, the above code is a similar mistake to this C code:

```c
int i;

while (someCondition) {
	i = 0;
	
	someOperation();
	
	++i;
}
```

This code will always end with `i` having a value of `1` no matter the number of executions of the loop, because the mutation does not survive between loops.

In the same way, variables declared in a composable function do not survive recomposition. When we want to keep the value between recompositions, we must `remember` what the previous value was:

```kotlin
@Composable
fun Counter() = Row {
          val counter = remember { mutableStateOf(0) }

          Button(onClick = { counter.value-- }) {
            Text("-")
          }

          Text("${counter.value}")

          Button(onClick = { counter.value++ }) {
            Text("+")
          }
        }
```

Now, the counter works again, and if we display multiple counters they do have different values.

> Earlier, we said UI-emitting composable functions should start with an upper-case letter.
> In fact, `remember` is a composable function, but it doesn't display anything, so it is named like a regular function.

To finish this example, we'll just clean up our code a bit using [delegated properties](https://kotlinlang.org/docs/delegated-properties.html). Notice how we replaced `=` by `by`, and all `.value` calls disappear:

```kotlin
@Composable
fun Counter() = Row {
          val counter by remember { mutableStateOf(0) }

          Button(onClick = { counter-- }) {
            Text("-")
          }

          Text("$counter")

          Button(onClick = { counter++ }) {
            Text("+")
          }
        }
```

In this tutorial, we have learned:

- How to create composable functions.
- How to manage state.
- How to store state through recompositions.

These are enough to be able to write Compose code. Unlike template-based frameworks, Compose doesn't need any more syntax:

- To display a component conditionally: use a regular Kotlin `if` statement
- To display multiple components: use a regular Kotlin `for` or `while` statement
- To create a component that takes a value as input: add it as a parameter to the composable function (e.g. `Button`'s `enabled` parameter)
- To create a component that emits events: add a lambda parameter to the composable function (e.g. `Button`'s `onClick` parameter)
- To create a component that has subcomponents: add a lambda parameter annotated with `@Composable` to the composable function (e.g. `Button`'s `content` parameter)

The code we wrote is multiplatform, we can now run it on other platforms supported by Decouple.

## Executing on Android

To learn how to execute an Android app, follow the official Android tutorials. The preconfigured run configuration is "Demo (Android)".

## Executing on Web

To open the generated website, execute the run configuration "Demo (web) » Compile". When it is done, execute the run configuration "Demo (web) » Host". When it is done, click on the link it prints to open the website in your browser.

The web version of the project automatically reloads when the code is changed.
