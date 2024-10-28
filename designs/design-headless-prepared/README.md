# Module Design system: Headless (Prepared compatibility)

Design system that emits no UI, used with the [Prepared test framework](https://gitlab.com/opensavvy/prepared).

<a href="https://search.maven.org/search?q=dev.opensavvy.decouple.design-headless-prepared"><img src="https://img.shields.io/maven-central/v/dev.opensavvy.decouple/design-headless-prepared.svg?label=Maven%20Central"></a>
<a href="https://opensavvy.dev/open-source/stability.html"><img src="https://badgen.net/static/Stability/alpha/purple"></a>
<a href="https://javadoc.io/doc/dev.opensavvy.decouple/design-headless-prepared"><img src="https://badgen.net/static/Other%20versions/javadoc.io/blue"></a>

To execute a headless UI with Prepared, use the [headlessUI](opensavvy.decouple.headless.prepared.headlessUI) provider:

```kotlin
val ui by headlessUI {
	Button(onClick = { println("Clicked!") }) {
		// …
	}
}

test("A test") {
	val ui = ui()

	ui.find(Button).click()
}
```

For more information on how to write headless tests, see the main Headless Design module.
