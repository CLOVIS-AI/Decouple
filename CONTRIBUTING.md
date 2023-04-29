# Contribution guide

Thanks for wanting to contribute to the project!

There are many things we appreciate help with. The simplest is to [report problems](https://gitlab.com/opensavvy/decouple/-/issues/new) you see in the documentation or while using the project.

## Issue tracking

We use labels to organize issues.

> If you are reading this page in the GitLab UI, you can click on any label to see related issues.
> If you are seeing this file in another tool, you can manually search by label in the [issue page](https://gitlab.com/opensavvy/decouple/-/issues).

Issue categories:

- ~"feature": High-level feature description (implementation is split into multiple sub-issues).
- ~"discussion": More discussion is required before classification or implementation can be started.
- ~"deployment": Deployment or CI/CD modification.
- ~"documentation": Documentation modification or problem.
- ~"bug": Something is not working correctly (accompanied by a severity).
- ~"security": Security issue, risk to users.
- ~"incident": Deployment issue (e.g. the demo website is down, accompanied by a severity).
- ~"wontfix": We decided this issue is out of scope of the project, or cannot be done.

Priority of issues:

- ~"priority::low": Implementation is not time-sensitive.
- ~"priority::medium": Normal priority.
- ~"priority::high": Should be worked on soon.
- ~"priority::urgent": Should be worked on as soon as possible.

Severity of issues (only for bugs and incidents):

- ~"severity::cosmetic": Some parts of the project are not documented properly or aren't as beautiful as expected, but usage is not impacted.
- ~"severity::minor": Some parts of the project are inconvenient to use.
- ~"severity::moderate": Some parts of the project cannot be used.
- ~"severity::major": Some important parts of the project cannot be used.
- ~"severity::critical": The entire project cannot be used.

Development state (should NOT be applied to merge requests; these labels are not always applied, also look at the assignee field or linked merge requests):

- ~"issue::doing": Someone is currently working on it.
- ~"issue::review": A merge request exists that will close this issue, and it's not in draft status.

Impacted module:

- ~"core": The `core` module.
- ~"extra:navigation": The `navigation` extra module.
- ~"extra:persist": The `persist` extra module.
- ~"style:material": Material You/Material 3 implementation.
- ~"style:headless": Headless implementation used for tests.
- ~"style:dom": Pure HTML implementation that is entirely styled through user-provided CSS.

Impacted Kotlin platform:

- ~"platform:jvm": Kotlin/JVM
- ~"platform:js": Kotlin/JS
- ~"platform:android": Android
- ~"platform:ios": All iOS variants

Interoperability with other projects:

- ~"interop:androidx": Jetpack Compose and JetBrains Compose modules which share the same components as Jetpack Compose
- ~"interop:compose-dom": JetBrains Compose for Web (DOM only)

## Helping with development

The details about our workflow, our code style and our commit style
are [available in our wiki](https://gitlab.com/opensavvy/wiki/-/blob/main/README.md).
