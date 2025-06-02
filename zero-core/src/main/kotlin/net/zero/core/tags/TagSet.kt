package net.zero.core.tags

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.core.registry.Registry
import java.util.stream.Stream

/**
 * A set of tags from a specific registry for a specific tag key.
 */
@SidedApi(Side.BOTH)
interface TagSet<T> : Iterable<T> {

    /**
     * The key that this tag set is mapped to.
     */
    val key: TagKey<T>

    /**
     * The registry that contains this tag set.
     */
    val registry: Registry<T>

    /**
     * Gets the size of this tag set.
     *
     * @return The size of this tag set.
     */
    fun size(): Int

    /**
     * Checks if this tag set contains the given [value].
     *
     * @param value The value.
     *
     * @return `true` if this tag set contains the value, `false` otherwise.
     */
    fun contains(value: T): Boolean

    /**
     * Gets the value at the given [index] in this tag set.
     *
     * @param index The index.
     *
     * @return The value.
     */
    fun get(index: Int): T

    /**
     * Creates a new stream of the values in this tag set.
     *
     * @return A new stream of this tag set.
     */
    fun stream(): Stream<T>
}
