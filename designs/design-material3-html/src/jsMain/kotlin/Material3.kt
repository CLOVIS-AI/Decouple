package opensavvy.decouple.material3.html

import androidx.compose.runtime.Composable
import opensavvy.decouple.components.DesignSystem
import opensavvy.decouple.material3.html.components.actions.Buttons
import opensavvy.decouple.material3.html.components.display.Texts
import opensavvy.decouple.material3.html.components.layouts.LinearLayouts
import opensavvy.material3.html.ExperimentalComponent
import opensavvy.material3.html.UnfinishedComponent
import opensavvy.material3.theme.InstallColorScheme

/**
 * A single interface that contains all components provided by this module.
 */
interface Material3 : DesignSystem,
	LinearLayouts,
	Texts {

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
	@OptIn(UnfinishedComponent::class)
	interface UnfinishedDesign : Material3

	/**
	 * Interface with all the unstable components.
	 *
	 * These components may change in source and binary incompatible ways in the future.
	 */
	@OptIn(UnfinishedComponent::class, ExperimentalComponent::class)
	interface Unstable : UnfinishedDesign,
		Buttons
}
