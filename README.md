# OpenSavvy Decouple

> _Decouple_ your design system from your view layer

We frequently consider the entire user interface as a single layer, often called 'UI' or 'View'—in reality, however, it is composed of two very distinct layers:

- the **design system** is the visual identity of the project,
- the **domain UI** builds upon the design system to implement the functional requirements of the app.

These two layers have different responsibilities and rate of change throughout the application's lifetime, and should therefore be split for better modularity.

- The design system is primarily driven by the designers, whereas the domain UI is driven by the functional team, who request changes at different rates,
- The design system may be reused between multiple projects,
- The same project may need to be adapted to multiple clients' design systems,
- The same project on multiple platforms may have very different design systems.

**OpenSavvy Decouple** is a multiplatform shim to abstract over design systems.
It makes possible the creation of design-agnostic applications that can be easily deployed on each platform for which a Decouple implementation exists.
Implementations can be easily created for any platform where Kotlin runs, and mixed to allow developers to build an application in parallel of the final design system.

To discuss the project with us, you can join the `#decouple` channel in the [official Kotlin Slack](https://kotl.in/slack).

## Documentation

> The documentation is currently a work-in-progress as the project is still experimental.
> We will gladly accept reports of missing or out-of-date information, as well as help with redaction.

The documentation is split into four sections, following the [Grand Unified Theory of Documentation](https://documentation.divio.com/):

- [Tutorials](https://opensavvy.gitlab.io/decouple/documentation/tutorials/index.html) demonstrate the usage of the library, skipping the setup steps,
- [Explanations](https://opensavvy.gitlab.io/decouple/documentation/explanations/index.html) present the underlying guidelines and our goals in high-level goals, as well as explain technical decisions,
- [How-to guides](https://opensavvy.gitlab.io/decouple/documentation/howto/index.html) are small commented examples of how to solve specific problems (including project setup),
- [The reference](https://opensavvy.gitlab.io/decouple/documentation/index.html) is a low-level precise explanation of the API elements and how to use them.

## Contribution

The project is licensed under Apache 2.0, allowing usage in most projects with minimal constraints.
The full text is available in the [LICENSE](LICENSE) file.

Development takes place in the [OpenSavvy GitLab repository](https://gitlab.com/opensavvy/decouple).
This is also the recommended place to [report problems and feature requests](https://gitlab.com/opensavvy/decouple/-/issues).

We follow the various conventions:

- Git commits follow the [BrainDot style](https://gitlab.com/braindot/legal/-/blob/master/coding-style/STYLE_Git.md).
- The development is organized using the [OpenSavvy Flow](https://gitlab.com/opensavvy/documents/-/blob/main/Documents/flow.md).
