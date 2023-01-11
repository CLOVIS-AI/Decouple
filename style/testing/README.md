# Module testing

Fake UI implementation used to test the behavior and state of our application.

## Testing the state, not the appearance

When using Decouple, the appearance of your app may differ from one UI implementation to another, or from one platform to another.
There is no multiplatform way of testing the exact appearance of the UI.

However, as Decouple abstracts all the state and behavior of the UI, it is possible to test how components interact and their internal values.
Although this is somewhat less informative than appearance testing, it is much easier to read, less likely to break with refactors, and works identically on all platforms.

As a summary, this module allows you to:
- test that pressing a button will execute a network request,
- test that a button is disabled when form fields are invalid,
- test that the data in a table corresponds to what the server requested.

This module does not help to:
- test the visual appearance of a button,
- test whether an implementation of Decouple is correct.
