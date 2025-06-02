package net.zero.block.entity

import net.kyori.adventure.key.Keyed
import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.block.Block

/**
 * A type of block entity.
 */
@ImmutableType
@SidedApi(Side.BOTH)
interface BlockEntityType<T : BlockEntity> : Keyed {

    /**
     * All of the blocks that block entities of this type can be bound to.
     */
    val applicableBlocks: Set<Block>

    /**
     * Returns true if the given [block] is applicable to block entities of
     * this type, or false otherwise.
     *
     * @param block The block.
     *
     * @return `true` if the block is applicable, `false` otherwise.
     */
    fun isApplicable(block: Block): Boolean
}
