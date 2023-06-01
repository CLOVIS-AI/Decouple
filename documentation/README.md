# Decouple Documentation

The documentation is split in four categories:

- **Tutorials** demonstrate the usage of the library, skipping the setup steps,
- **Explanations** present the underlying guidelines and our high-level goals, as well as explain technical decisions,
- **How-to guides** are small commented examples of how to solve specific problems,
- **The reference** is a low-level precise explanation of the API elements and how to use them.

## Tutorials

## Explanations

Project organization:

- [How are tasks organized?](../CONTRIBUTING.md#issue-tracking)
- [How can I contribute?](https://gitlab.com/opensavvy/wiki/-/blob/main/README.md)

## How-to guides

## The reference

The reference is documented directly in the code using [KDoc](https://kotlinlang.org/docs/kotlin-doc.html).
Comments are extracted and generated into a static website using [Dokka](https://kotlinlang.org/docs/dokka-introduction.html).

To browse the generated site for the latest Decouple version, [click here](https://opensavvy.gitlab.io/decouple/documentation/index.html).

To generate the site for any other version, clone the repository, run the command `./gradlew dokkaHtmlMultiModule` and look into [build/dokka/htmlMultiModule](../build/dokka/htmlMultiModule).
