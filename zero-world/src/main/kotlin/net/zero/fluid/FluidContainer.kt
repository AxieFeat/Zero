package net.zero.fluid

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.util.Vec3i

/**
 * Something that contains fluids.
 *
 * The default value that will be returned instead of null if no fluid is
 * found is EMPTY.
 */
@SidedApi(Side.BOTH)
interface FluidContainer {

    /**
     * Gets the fluid at the given [x], [y], and [z] coordinates.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     * @param z The Z coordinate.
     *
     * @return The fluid at the given coordinates.
     */
    fun getFluid(x: Int, y: Int, z: Int): FluidState

    /**
     * Gets the fluid at the given [position].
     *
     * @param position The position.
     *
     * @return The fluid at the given position.
     */
    fun getFluid(position: Vec3i): FluidState
}
