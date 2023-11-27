package opensavvy.decouple.material.androidx.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import opensavvy.decouple.core.layout.Alignment
import opensavvy.decouple.core.layout.Arrangement
import opensavvy.decouple.core.layout.LinearLayouts
import androidx.compose.foundation.layout.Arrangement as M3Arrangement
import androidx.compose.foundation.layout.Box as M3Box
import androidx.compose.foundation.layout.Column as M3Column
import androidx.compose.foundation.layout.Row as M3Row
import androidx.compose.ui.Alignment as M3Alignment

object MALinearLayouts : LinearLayouts {
	private fun Arrangement.toVerticalM3(): M3Arrangement.Vertical = when (this) {
		Arrangement.Stretch -> M3Arrangement.Top
		Arrangement.Start -> M3Arrangement.Top
		Arrangement.Center -> M3Arrangement.Center
		Arrangement.End -> M3Arrangement.Bottom
		Arrangement.SpaceBetween -> M3Arrangement.SpaceBetween
		Arrangement.SpaceAround -> M3Arrangement.SpaceAround
		Arrangement.SpaceEvenly -> M3Arrangement.SpaceEvenly
	}

	private fun Arrangement.toHorizontalM3(): M3Arrangement.Horizontal = when (this) {
		Arrangement.Stretch -> M3Arrangement.Start
		Arrangement.Start -> M3Arrangement.Start
		Arrangement.Center -> M3Arrangement.Center
		Arrangement.End -> M3Arrangement.End
		Arrangement.SpaceBetween -> M3Arrangement.SpaceBetween
		Arrangement.SpaceAround -> M3Arrangement.SpaceAround
		Arrangement.SpaceEvenly -> M3Arrangement.SpaceEvenly
	}

	private fun Alignment.toVerticalM3(): M3Alignment.Vertical = when (this) {
		Alignment.Stretch -> M3Alignment.Top
		Alignment.Start -> M3Alignment.Top
		Alignment.Center -> M3Alignment.CenterVertically
		Alignment.End -> M3Alignment.Bottom
	}

	private fun Alignment.toHorizontalM3(): M3Alignment.Horizontal = when (this) {
		Alignment.Stretch -> M3Alignment.Start
		Alignment.Start -> M3Alignment.Start
		Alignment.Center -> M3Alignment.CenterHorizontally
		Alignment.End -> M3Alignment.End
	}

	@Composable
	override fun ColumnSpec(
		vertical: Arrangement,
		alignment: Alignment,
		content: @Composable LinearLayouts.ColumnScope.() -> Unit,
	) {
		M3Column(
			verticalArrangement = vertical.toVerticalM3(),
			horizontalAlignment = alignment.toHorizontalM3(),
		) { content(MAColumnScope) }
	}

	@Composable
	override fun RowSpec(
		horizontal: Arrangement,
		alignment: Alignment,
		content: @Composable LinearLayouts.RowScope.() -> Unit,
	) {
		M3Row(
			horizontalArrangement = horizontal.toHorizontalM3(),
			verticalAlignment = alignment.toVerticalM3(),
		) { content(MARowScope) }
	}

	@Composable
	override fun BoxSpec(
		alignment: Alignment,
		content: @Composable LinearLayouts.BoxScope.() -> Unit,
	) {
		// TODO in #80: rework the alignment

		M3Box { content(MABoxScope) }
	}

	@Composable
	override fun GridSpec(
		horizontal: IntRange,
		vertical: IntRange,
		content: @Composable LinearLayouts.GridScope.(Int, Int) -> Unit,
	) {
		M3Column {
			for (y in vertical) {
				M3Row {
					for (x in horizontal) {
						M3Box(Modifier.weight(1f)) { content(MAGridScope, x, y) }
					}
				}
			}
		}
	}

	private object MAColumnScope : LinearLayouts.ColumnScope
	private object MARowScope : LinearLayouts.RowScope
	private object MABoxScope : LinearLayouts.BoxScope
	private object MAGridScope : LinearLayouts.GridScope
}
