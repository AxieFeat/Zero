package net.zero.world

import net.kyori.adventure.audience.ForwardingAudience
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.block.BlockContainer
import net.zero.block.BlockState
import net.zero.block.entity.BlockEntityContainer
import net.zero.core.registry.RegistryHolder
import net.zero.core.scheduling.Scheduler
import net.zero.entity.Entity
import net.zero.entity.EntityContainer
import net.zero.entity.EntityType
import net.zero.fluid.FluidContainer
import net.zero.fluid.FluidState
import net.zero.util.Position
import net.zero.util.Vec3i
import net.zero.world.chunk.BlockChangeFlags
import net.zero.world.chunk.Chunk
import java.util.function.Consumer

/**
 * Represents a loaded world.
 */
@SidedApi(Side.BOTH)
interface World : BlockContainer, FluidContainer, BlockEntityContainer, EntityContainer, ForwardingAudience {

    /**
     * The name of this world.
     */
    val name: String

    /**
     * The spawn location of this world.
     */
    val spawnLocation: Vec3i

    /**
     * All of the chunks currently loaded in this world.
     */
    val chunks: Collection<Chunk>

    /**
     * All of the entities currently in this world.
     */
    override val entities: Collection<Entity>

    /**
     * The seed of this world.
     */
    val seed: Long

    /**
     * The current time in this world.
     */
    val time: Long

    /**
     * The level of the current rain.
     */
    var rainLevel: Float

    /**
     * The level of the current thunderstorm (0 if there is no thunderstorm
     * going on).
     */
    var thunderLevel: Float

    /**
     * The scheduler for this world.
     *
     * Useful for scheduling tasks that should only exist for the lifetime of
     * the world, as all tasks that are scheduler with this scheduler will
     * stop running after the world is removed.
     */
    @SidedApi(Side.SERVER)
    val scheduler: Scheduler

    /**
     * The registry holder for this world.
     *
     * This contains registries that are specific to this world, and not
     * shared across the whole server.
     */
    val registryHolder: RegistryHolder

    /**
     * Gets the block at the given coordinates.
     *
     * This method will return the following in specific cases:
     * - If the given [y] coordinate is greater than the maximum height of this
     * world, this will return the default state of `VOID_AIR`.
     * - If there is no chunk loaded at the given coordinates ([getChunk] was
     * null), this will return the default state of `AIR`.
     * - Else it will return the block at the given coordinates.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     * @param z The Z coordinate.
     *
     * @return See above.
     */
    override fun getBlock(x: Int, y: Int, z: Int): BlockState

    /**
     * Gets the block at the given [position].
     *
     * This method has semantics identical to that of [getBlock] with
     * individual components (X, Y, and Z values).
     *
     * @param position The position.
     *
     * @return See above.
     */
    override fun getBlock(position: Vec3i): BlockState

    /**
     * Sets the block at the given coordinates to the given [block].
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     * @param z The Z coordinate.
     * @param block The block.
     * @param flags The flags to use when updating the block.
     *
     * @return `true` if the block was set, `false` otherwise.
     */
    @SidedApi(Side.SERVER)
    fun setBlock(x: Int, y: Int, z: Int, block: BlockState, flags: BlockChangeFlags): Boolean

    /**
     * Sets the block at the given [position] to the given [block].
     *
     * @param position The position.
     * @param block The block.
     * @param flags The flags to use when updating the block.
     *
     * @return `true` if the block was set, `false` otherwise.
     */
    @SidedApi(Side.SERVER)
    fun setBlock(position: Vec3i, block: BlockState, flags: BlockChangeFlags): Boolean

    /**
     * Gets the fluid at the given coordinates.
     *
     * This method will return the following in specific cases:
     * - If the given [y] coordinate is greater than the maximum height of this
     * world, or there is no chunk loaded at the given coordinates
     * ([getChunk] was null), this will return `EMPTY`.
     * - Else it will return the fluid at the given coordinates.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     * @param z The Z coordinate.
     *
     * @return See above.
     */
    override fun getFluid(x: Int, y: Int, z: Int): FluidState

    /**
     * Gets the fluid at the given [position].
     *
     * This method has semantics identical to that of [getFluid] with
     * individual components (X, Y, and Z values).
     *
     * @param position The position.
     *
     * @return See above.
     */
    override fun getFluid(position: Vec3i): FluidState

    /**
     * Gets a chunk from its **chunk** coordinates, or returns null if there is
     * no chunk **loaded** at the given coordinates.
     *
     * That is, to calculate the chunk coordinate from a given block
     * coordinate, the block coordinate is shifted right by 4 (divided by 16
     * and floored).
     *
     * @param x The chunk X coordinate.
     * @param z The chunk Z coordinate.
     *
     * @return The chunk at the given coordinates, or null if there isn't one loaded.
     */
    fun getChunk(x: Int, z: Int): Chunk?

    /**
     * Gets or loads the chunk at the given **chunk** coordinates, or returns
     * null if there is no chunk at the given chunk coordinates.
     *
     * That is, to calculate the chunk coordinate from a given block
     * coordinate, the block coordinate is shifted right by 4 (divided by 16
     * and floored).
     *
     * Beware that chunks loaded using this function will not be automatically
     * unloaded!
     *
     * @param x The X coordinate.
     * @param z The Z coordinate.
     *
     * @return The loaded chunk, or null if not present.
     */
    @SidedApi(Side.SERVER)
    fun loadChunk(x: Int, z: Int): Chunk?

    /**
     * Unloads the chunk at the specified [x] and [z] coordinates if there is
     * a chunk loaded. If there is no chunk loaded at the coordinates, this
     * function simply returns.
     *
     * Like [loadChunk], these coordinates are **chunk** coordinates.
     *
     * @param x The X coordinate.
     * @param z The Z coordinate.
     */
    @SidedApi(Side.SERVER)
    fun unloadChunk(x: Int, z: Int)

    /**
     * Spawns an entity with the given [type] in this world at the
     * given [position].
     *
     * @param T The entity type.
     * @param type The type of the entity.
     * @param position The position to spawn the entity at.
     */
    @SidedApi(Side.SERVER)
    fun <T : Entity> spawnEntity(type: EntityType<T>, position: Position): T?

    /**
     * Gets all entities of the given [type] that are within the given [range]
     * of the given [position], calling the given [callback] for each entity
     * found.
     *
     * @param E The entity type.
     * @param position The centre position to look around.
     * @param range The range to look for entities in.
     * @param type The type of entities to find.
     * @param callback The callback called for each entity found.
     */
    fun <E : Entity> getNearbyEntitiesOfType(position: Position, range: Double, type: Class<E>, callback: Consumer<E>)

    /**
     * Gets all entities of the given [type] that are within the given [range]
     * of the given [position].
     *
     * @param E The entity type.
     * @param position The centre position to look around.
     * @param range The range to look for entities in.
     * @param type The type of entities to find.
     *
     * @return All found entities of the given type.
     */
    fun <E : Entity> getNearbyEntitiesOfType(position: Position, range: Double, type: Class<E>): Collection<E>

    /**
     * Gets all entities that are within the given [range] of the
     * given [position], calling the given [callback] for each entity found.
     *
     * @param position The centre position to look around.
     * @param range The range to look for entities in.
     * @param callback The callback called for each entity found.
     */
    fun getNearbyEntities(position: Position, range: Double, callback: Consumer<Entity>)

    /**
     * Gets all entities that are within the given [range] of the
     * given [position].
     *
     * @param position The centre position to look around.
     * @param range The range to look for entities in.
     * @return All found entities.
     */
    fun getNearbyEntities(position: Position, range: Double): Collection<Entity>
}
