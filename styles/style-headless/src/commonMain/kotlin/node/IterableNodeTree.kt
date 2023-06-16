package opensavvy.decouple.headless.node

internal class IterableNodeTree(
	private val nodes: Iterable<Node>,
) : NodeTree {
	override fun nodes(): Sequence<Node> = nodes
		.asSequence()
		.flatMap { it.nodes() }

	override fun directChildren(): Sequence<Node> = nodes
		.asSequence()

	override fun toString() = buildString {
		appendLine("NodeTree of")
		for (node in directChildren()) {
			for (line in node.toPrettyString().lines()) {
				if (line.isNotBlank()) {
					append("  ")
					appendLine(line)
				}
			}
		}
	}
}
