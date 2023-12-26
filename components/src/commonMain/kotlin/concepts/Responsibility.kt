package opensavvy.decouple.components.concepts

import kotlinx.coroutines.CoroutineScope

/**
 * When a user interacts with a system, the system triggers and monitors operations that can span multiple
 * machines. Humans are not able to observe these operations, and are seldom patient with systems that appear
 * unresponsive.
 *
 * To avoid this, it is important to communicate ongoing operations to the user, so they do not think the system is
 * unresponsive.
 *
 * ### The concept
 *
 * Decouple introduces a convention to help ensure this happens: each possible action identifies a single component which has __responsibility__ of it.
 * This component is responsible for communicating the progress of the action to the user.
 * We recommend always choosing the component that is most closely scoped to the action triggering (usually some kind of button).
 *
 * ### Technical implementation
 *
 * Synchronous actions, by nature, cannot take enough time to be visible by a user. Therefore, they are not concerned by responsibility.
 *
 * Components which are able to start an operation requiring responsibility take a `suspend` function as parameter:
 * the entire execution of that function is considered as the execution of the action.
 * When an action is executing, a component will often become un-actionable, or change its appearance to provide a cancel button.
 *
 * Because long-running operations require some kind of cancellation control, to ensure no operation continues when its
 * results are not needed anymore, responsible components also expose a [CoroutineScope] argument which defaults to the
 * lifetime of the component itself (if the component decomposes, the action is automatically cancelled).
 *
 * A typical responsible component will therefore be similar to:
 * ```kotlin
 * @Composable
 * fun Foo(
 *     onClick: suspend () -> Unit,
 *     scope: CoroutineScope = rememberCoroutineScope(),
 * ) {
 *     // …
 * }
 * ```
 *
 * In this example, the `Foo` component declares being responsible for its `onClick` operation. Depending on the design
 * system, progress may be communicated to the user in various ways (including not communicated at all, if this action
 * is not important enough).
 *
 * Because `Foo` is responsible for `onClick`, and only one component may be responsible for an operation,
 * the caller knows that they aren't responsible for it. The caller therefore needs not write any special code to
 * communicate progress.
 *
 * #### Opt-ing out of responsibility
 *
 * Sometimes, a component advertises responsibility for an operation, but the caller wants that responsibility for
 * themselves. In these cases, the caller can "steal" it by starting the operation in their own scope;
 * the child's operation therefore ends immediately.
 *
 * ```kotlin
 * @Composable
 * fun Controller() {
 *     val scope = rememberCoroutineScope()
 *
 *     Foo(
 *         onClick = {
 *             scope.launch {   // Stealing
 *                 delay(1000)
 *                 println("Done")
 *             }
 *         }
 *     )
 * }
 * ```
 *
 * In this example, the `Controller` composable has "stolen" the responsibility from the `Foo` component.
 * The `Foo` component will therefore not display progress indicators, and the `Controller` component should.
 *
 * > Another way to see it: the `Foo` component has become responsible for the operation of "telling the `Controller`
 * > component to start the main operation", which ends immediately, thus no indicator is necessary. The `Controller` component
 * > is then responsible for the main operation, and communicates progress for that.
 *
 * #### Controlling the lifetime and context of the operation
 *
 * Sometimes, we want the original component to keep responsibility for an operation, but we want the operation to
 * continue even if the component disappears. In this case, we can override the scope used to run the operation:
 * ```kotlin
 * @Composable
 * fun FireAndForget() {
 *     Foo(
 *         onClick = {
 *             delay(1000)
 *             println("Done")
 *         },
 *         scope = GlobalScope,
 *     )
 * }
 * ```
 *
 * In this case, `Foo` will start the operation, and will be responsible for communicating progress to the user.
 * However, if `Foo` goes off-screen, the operation will continue.
 *
 * This pattern is useful for large forms. For example, a large card could replace the submit button's scope by its own
 * scope, such that the submitting operation continues for as long as any part of the card is visible, even if the submit
 * button itself isn't.
 *
 * ### Things to keep in mind
 *
 * Do not overuse this concept! If the entire UI is covered by progress indicators, it will feel crowded, making
 * information harder to find at a glance.
 */
typealias ActionResponsibility = Nothing
