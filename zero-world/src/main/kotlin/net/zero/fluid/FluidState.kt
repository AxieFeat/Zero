package net.zero.fluid

import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.block.BlockState
import net.zero.core.state.State

/**
 * A state of a fluid.
 */
@ImmutableType
@SidedApi(Side.BOTH)
interface FluidState : State<FluidState> {

    /**
     * The fluid this is a state of.
     */
    val fluid: Fluid

    /**
     * The level of this fluid state.
     *
     * Should be either a constant value, such as 0 for the empty fluid, or 8
     * for source fluids, or the value of the LIQUID_LEVEL property for flowing fluids.
     */
    val level: Int

    /**
     * If this fluid state is a source fluid.
     *
     * @return `true` if this fluid state is a source fluid.
     */
    fun isSource(): Boolean

    /**
     * Converts this fluid state in to its corresponding block state.
     *
     * @return This fluid state as a block state.
     */
    fun asBlock(): BlockState
}
