package net.zero.block

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.util.Vec3i

/**
 * Something that contains blocks.
 *
 * The default value that will be returned instead of null if no block is
 * found is the block state representing air.
 */
@SidedApi(Side.BOTH)
interface BlockContainer {

    /**
     * Gets the block at the given [x], [y], and [z] coordinates.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param z The z coordinate.
     *
     * @return The block at the given coordinates.
     */
    fun getBlock(x: Int, y: Int, z: Int): BlockState

    /**
     * Gets the block at the given [position].
     *
     * @param position The position.
     *
     * @return The block at the given position.
     */
    fun getBlock(position: Vec3i): BlockState
}
