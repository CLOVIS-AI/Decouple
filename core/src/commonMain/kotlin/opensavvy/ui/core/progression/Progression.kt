package opensavvy.ui.core.progression

import opensavvy.ui.core.progression.Progression.Done
import opensavvy.ui.core.progression.Progression.Loading

/**
 * Is work currently happening in a component?
 *
 * Modern applications are often highly asynchronous.
 * However, UI frameworks most often than not are oblivious to these background processes.
 * This causes UIs to feel slow and unresponsive, as the user is not aware of what is going on.
 *
 * OpenSavvy UI attempts to solve this problem at the component level, the closest place to the information.
 * A component that starts an asynchronous operation (e.g. a button) is called _responsible_ of that operation.
 * Depending on the UI implementation, this may be displayed to the user in a variety of ways (displaying a notification,
 * replacing the button by a loading spinner…).
 *
 * The [Progression] sealed class is used to represent whether a component is responsible for an ongoing task.
 * Stateless variants of components that may the source of a long-running operation will accept a `progression: Progression` parameter.
 * Usually, the stateful variant of a component will not expose this parameter, and replace it by an asynchronous event lambda.
 *
 * There are two possible values to a [Progression] instance:
 * - [Done]: the component is not responsible for an ongoing task.
 * - [Loading]: the component is responsible for an ongoing task.
 *
 * Components that can be responsible for long-running operations will often accept callback parameters similar to
 * `suspend ReportProgression.() -> Unit`.
 * For more information about those, see [ReportProgression].
 */
sealed class Progression {

	/**
	 * There is currently no work taking place.
	 *
	 * To easily report this state in [ReportProgression], call [done].
	 *
	 * @see Progression
	 */
	object Done : Progression()

	/**
	 * There is currently some work taking place in this component.
	 *
	 * Depending on the component, this may temporarily disable its action.
	 * For example, a button cannot be clicked as long as the previous operation has not finished.
	 *
	 * To easily report this state in [ReportProgression], call [loading].
	 *
	 * @see Progression
	 */
	sealed class Loading : Progression() {

		/**
		 * There is currently some work taking place, but we have no information on its progress.
		 *
		 * @see Loading
		 * @see Progression
		 */
		object Unquantified : Loading()

		/**
		 * There is currently some work taking place, and we have an estimation of its progression.
		 *
		 * @see Loading
		 * @see Progression
		 */
		data class Quantified(
			/**
			 * The current progression of this task.
			 *
			 * Allowed values range from `0.0` to `1.0` (inclusive).
			 * `0.0` means "the operation has not started yet" (no progress has been made).
			 * `1.0` means "the operation has finished" (no progress left to be made).
			 *
			 * `1.0` is a legal value because it simplifies usage.
			 * `Quantified(1.0)` should be treated as an in-progress state, even if it mathematically means the operation is done.
			 * For example, a progress bar may display '100%' without disappearing.
			 * If the caller doesn't want '100%' to appear, it is their responsibility to not instantiate this class with a value of `1.0`.
			 *
			 * To access this value as a percentage, see [percent].
			 */
			val progression: Double,
		) : Loading() {
			init {
				require(progression in 0.0..1.0) { "The progression should be a value between 0 and 1, found $progression" }
			}

			/**
			 * The current progression of this task.
			 *
			 * Allowed values range from `0` to `100` (inclusive).
			 * `0` means "the operation has not started yet" (no progress has been made).
			 * `100` means "the operation has finished" (no progress left to be made).
			 *
			 * For more information, see [progression].
			 */
			val percent: Int
				get() = (progression * 100).toInt()
		}

	}
}
