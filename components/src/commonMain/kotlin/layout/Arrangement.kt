package opensavvy.decouple.components.layout

/**
 * Children arrangement on the main axis.
 *
 * Arrangements that change the size of elements:
 * - [Stretch]
 *
 * Arrangements that introduce no gap between elements, but may create empty space before or after them:
 * - [Start]
 * - [Center]
 * - [End]
 *
 * Arrangements that change the gap between elements:
 * - [SpaceBetween]
 * - [SpaceAround]
 * - [SpaceEvenly]
 */
enum class Arrangement {
	/**
	 * Elements should grow such that the entire space is filled.
	 */
	Stretch,

	/**
	 * Elements keep their default size with no gaps, bunching up from the starting position.
	 *
	 * Some space may be left at the end of the container.
	 */
	Start,

	/**
	 * Elements keep their default size with no gaps, bunching up in the center.
	 *
	 * Some space may be left at the start and end of the container.
	 */
	Center,

	/**
	 * Elements keep their default size with no gaps, bunching up at the end position.
	 *
	 * Some space may be left at the start of the container.
	 */
	End,

	/**
	 * Elements keep their default size, gaps of equal size are created around them, but not between elements and the edges.
	 *
	 * The first element is placed at the start of the container.
	 * The last element is placed at the end of the container.
	 * Gaps are created between elements such that elements are equidistant.
	 */
	SpaceBetween,

	/**
	 * Elements keep their default size, gaps of equal size are created around them, with half gaps towards the edges.
	 *
	 * Using this arrangement, all elements' center are exactly in the same position as they would
	 * be with [Stretch]. However, instead of stretching elements to fill the space, a gap is added
	 * instead.
	 *
	 * This is similar to adding padding around elements: the distance between elements is twice the distance
	 * between an element and an edge (because gaps are added between elements, and the edge is not an element).
	 */
	SpaceAround,

	/**
	 * Elements keep their default size, gaps of equal size are created between all of them and the edges.
	 *
	 * Using this arrangement, all elements are placed equidistant from each other and from the edge.
	 */
	SpaceEvenly,
}

/**
 * Children alignment on the secondary axis.
 *
 * Alignments that change the size of elements:
 * - [Stretch]
 *
 * Alignments that introduce no gap between elements, but may create empty space before or after them:
 * - [Start]
 * - [Center]
 * - [End]
 */
enum class Alignment {
	/**
	 * Elements should grow such that the entire space is filled.
	 */
	Stretch,

	/**
	 * Elements keep their default size with no gaps, bunching up from the starting position.
	 *
	 * Some space may be left at the end of the container.
	 */
	Start,

	/**
	 * Elements keep their default size with no gaps, bunching up in the center.
	 *
	 * Some space may be left at the start and end of the container.
	 */
	Center,

	/**
	 * Elements keep their default size with no gaps, bunching up at the end position.
	 *
	 * Some space may be left at the start of the container.
	 */
	End,
}
