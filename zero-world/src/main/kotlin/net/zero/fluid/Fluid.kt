package net.zero.fluid

import net.kyori.adventure.key.Keyed
import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.core.state.StateHolder
import net.zero.item.ItemType
import net.zero.block.Block

/**
 * A fluid with certain properties.
 *
 * The design of this is very similar to that of the [Block].
 */
@ImmutableType
@SidedApi(Side.BOTH)
interface Fluid : StateHolder<FluidState>, Keyed {

    /**
     * The type of the bucket this fluid can be held in.
     */
    val bucket: ItemType
}
