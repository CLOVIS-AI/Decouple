package opensavvy.decouple.headless.node

import opensavvy.decouple.headless.Component
import opensavvy.decouple.headless.Component.Companion.viewAs

/**
 * An abstract tree of node.
 *
 * This interface is mostly composed of the [nodes] function that visits the entire tree,
 * the [directChildren] function that visits the direct children of this node, and convenience functions
 * to use them more easily.
 *
 * As a convention, functions with a name starting with `find` search through the entire tree, and functions starting
 * with `get` search through direct children. In case of a doubt, each function explicitly documents which algorithm it
 * uses.
 */
interface NodeTree {

	/**
	 * Visits the tree of nodes and returns all of them (including slots) in a depth-first postfix search.
	 *
	 * For example, the tree
	 * ```
	 * a
	 *   b
	 *     c
	 *     d
	 *   e
	 * ```
	 * is returned in the order
	 * ```
	 * c d b e a
	 * ```
	 */
	fun nodes(): Sequence<Node>

	/**
	 * Returns the direct children of this tree.
	 *
	 * For example, the tree
	 * ```
	 * a
	 *   b
	 *     c
	 *     d
	 *   e
	 * ```
	 * has the direct children
	 * ```
	 * b e
	 * ```
	 */
	fun directChildren(): Sequence<Node>

	// region Transitive utilities

	/**
	 * Finds the first occurrence of a node of [type] in the tree.
	 *
	 * The returned component is a copy of the node in tree, meaning that it will not change
	 * if the application continues to run.
	 *
	 * If no component matches [type], returns `null`.
	 *
	 * The search order is defined by the method [nodes].
	 */
	fun <C : Component> findOrNull(type: Component.Meta<C>): C? = nodes()
		.firstOrNull(type::canBuildFrom)
		?.viewAs(type)

	/**
	 * Finds the first occurrence of a node of [type] in the tree.
	 *
	 * The returned component is a copy of the node in tree, meaning that it will not change
	 * if the application continues to run.
	 *
	 * If not component matches [type], throws [NoSuchElementException].
	 *
	 * The search order is defined by the method [nodes]
	 */
	fun <C : Component> find(type: Component.Meta<C>): C = findOrNull(type)
		?: throw NoSuchElementException("Could not find any component of type $type")

	/**
	 * Checks if a node of [type] exists in the tree.
	 *
	 * To access it, use [find] or [findOrNull] instead.
	 *
	 * To only search in the first layer, use [has] instead.
	 */
	operator fun contains(type: Component.Meta<*>) = findOrNull(type) != null

	// endregion
	// region Non-transitive utilities

	/**
	 * Finds the first occurrence of a node of [type] in the first layer of this tree.
	 *
	 * The returned component is a copy of the node in tree, meaning that it will not change
	 * if the application continues to run.
	 *
	 * If no component matches [type], returns `null`.
	 *
	 * The search order is defined by the method [directChildren].
	 */
	fun <C : Component> getOrNull(type: Component.Meta<C>): C? = directChildren()
		.firstOrNull(type::canBuildFrom)
		?.viewAs(type)

	/**
	 * Finds the first occurrence of a node of [type] in the first layer of this tree.
	 *
	 * The returned component is a copy of the node in tree, meaning that it will not change
	 * if the application continues to run.
	 *
	 * If no component matches [type], returns [NoSuchElementException].
	 *
	 * The search order is defined by the method [directChildren].
	 */
	operator fun <C : Component> get(type: Component.Meta<C>): C = getOrNull(type)
		?: throw NoSuchElementException("Could not find any direct children ")

	/**
	 * Checks if a node of [type] exists in the first layer of this tree.
	 *
	 * To access it, use [get] or [getOrNull].
	 *
	 * To search in the entire tree, use [contains] instead.
	 */
	infix fun <C : Component> has(type: Component.Meta<C>) = getOrNull(type) != null

	// endregion

	/**
	 * Returns `true` if at least one component is part of this tree.
	 *
	 * This is the opposite of [absent].
	 */
	val present get() = nodes().count() > 0

	/**
	 * Returns `true` if no component is part of this tree.
	 *
	 * This is the opposite of [present].
	 */
	val absent get() = nodes().count() == 0

	/**
	 * Singleton empty implementation of [NodeTree].
	 */
	object Empty : NodeTree {
		override fun nodes(): Sequence<Node> = emptySequence()
		override fun directChildren(): Sequence<Node> = emptySequence()
		override fun toString() = "NodeTree.Empty"
	}
}
