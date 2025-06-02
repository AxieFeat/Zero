package net.zero.world.chunk

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.block.BlockContainer
import net.zero.entity.Entity
import net.zero.world.World

import net.zero.fluid.FluidContainer
import net.zero.entity.EntityContainer

/**
 * Represents a chunk, or a 16 x 16 x world height area of blocks.
 */
@SidedApi(Side.BOTH)
interface Chunk : BlockContainer, FluidContainer, EntityContainer {

    /**
     * The world this chunk is in.
     */
    val world: World

    /**
     * The X position of this chunk.
     */
    val x: Int

    /**
     * The Z position of this chunk.
     */
    val z: Int

    /**
     * The time that this chunk was last updated. This is set when the chunk is
     * saved to disk.
     */
    val lastUpdate: Long

    /**
     * All of the entities currently in this chunk.
     */
    override val entities: Collection<Entity>
}
