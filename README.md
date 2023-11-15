# Decouple

> _Decouple_ your design system from your application

We frequently consider the entire user interface as a single layer, often called 'UI' or 'View'—however, it is composed of two very distinct layers:

- the **design system** is the visual identity of the project,
- the **domain UI** builds upon the design system to implement the functional requirements of the app.

These two layers have different responsibilities and rate of change throughout the application's lifetime, and should therefore be split for better modularity.

- The design system is primarily driven by the designers, whereas the domain UI is driven by the domain team, who request changes at different rates,
- The design system may be reused between multiple projects,
- The same project may need to be adapted to multiple clients' design systems,
- The same project on multiple platforms may have very different design systems.

**OpenSavvy Decouple** is a multiplatform shim to abstract over design systems.
It makes possible the creation of design-agnostic applications that can be easily deployed on each platform for which a Decouple implementation exists.
Implementations can be easily created for any platform where Kotlin runs, and mixed to allow developers to build an application in parallel of the final design system.

To discuss the project with us, join the `#decouple` channel in the [official Kotlin Slack](https://kotl.in/slack).

> **Decouple is currently ongoing a major restructuring.**
> Links may be broken, and information may be incorrect.
> [Learn more](https://gitlab.com/opensavvy/decouple/-/issues/192).

## License

This project is licensed under the [Apache 2.0 license](LICENSE).

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md).
- To learn more about our coding conventions and workflow, see the [OpenSavvy Wiki](https://gitlab.com/opensavvy/wiki/-/blob/main/README.md#wiki).
- This project is based on the [OpenSavvy Playground](docs/playground/README.md), a collection of preconfigured project templates.
