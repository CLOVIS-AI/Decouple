package opensavvy.decouple.material3.html

import androidx.compose.runtime.Composable
import opensavvy.decouple.components.DesignSystem
import opensavvy.material3.css.InstallColorScheme
import opensavvy.material3.tailwind.ExperimentalComponent
import opensavvy.material3.tailwind.UnfinishedComponent

/**
 * A single interface that contains all components provided by this module.
 */
interface Material3 : DesignSystem {

	override val name: String
		get() = "Material3 (HTML implementation)"

	@Composable
	override fun initializeFor(content: @Composable () -> Unit) {
		InstallColorScheme {
			content()
		}
	}

	/**
	 * Interface with all the stable components whose visual designed is not finalized.
	 *
	 * These components are safe to use in regard to source and binary compatibility, but may not look great in all situations.
	 */
	@UnfinishedComponent
	interface UnfinishedDesign : Material3

	/**
	 * Interface with all the unstable components.
	 *
	 * These components may change in source and binary incompatible ways in the future.
	 */
	@UnfinishedComponent
	@ExperimentalComponent
	interface Unstable : UnfinishedDesign
}
