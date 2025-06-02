package net.zero.block.entity

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.block.Block
import net.zero.util.Vec3i
import net.zero.world.World

/**
 * A block entity is an entity that exists as a companion to a block, so that
 * block can store information that would violate the functionality of blocks
 * on their own.
 *
 * Despite the name, however, these do not behave anything like regular
 * entities. They do not actually exist to the end user, cannot be seen, and
 * only exist to hold data that blocks cannot.
 *
 * These used to be known as tile entities, for all of you folks who remember
 * those days.
 */
@SidedApi(Side.BOTH)
interface BlockEntity {

    /**
     * The type of this block entity.
     */
    val type: BlockEntityType<*>

    /**
     * The world this block entity is in.
     */
    val world: World

    /**
     * The block that this entity is bound to.
     */
    val block: Block

    /**
     * The position of this block entity.
     *
     * This will be identical to the position of the associated block.
     */
    val position: Vec3i
}
