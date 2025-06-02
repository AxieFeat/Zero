package net.zero.block

import net.kyori.adventure.key.Keyed
import net.kyori.adventure.translation.Translatable
import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.core.state.StateHolder

/**
 * A block with certain properties.
 *
 * These are immutable and do not contain any state-specific
 * information, such as the world or location they are in, so
 * they can be easily reused in many places, which from a
 * technical standpoint, reduces allocations, but also makes
 * them much more thread-safe.
 */
@ImmutableType
@SidedApi(Side.BOTH)
interface Block : StateHolder<BlockState>, Translatable, Keyed {

    /**
     * The friction of this block.
     */
    val friction: Double

    /**
     * The group of sounds for this block.
     */
    val soundGroup: BlockSoundGroup

    /**
     * If this block has gravity.
     *
     * If a block has gravity, and it is placed with one or more air blocks
     * beneath it, it will become a falling block entity, and fall down
     * towards the ground with an acceleration of ~9.8 m/s^2 until it reaches
     * a solid block, in which it will stop falling and become a normal block.
     *
     * @return `true` if this block has gravity.
     */
    fun hasGravity(): Boolean

    /**
     * If this block can be respawned inside of.
     *
     * @return `true` if this block can be respawned in.
     */
    fun canRespawnIn(): Boolean

    /**
     * If this block has an associated block entity.
     *
     * @return `true` if this block has a block entity.
     */
    fun hasBlockEntity(): Boolean

}
