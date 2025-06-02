package net.zero.entity

import net.kyori.adventure.key.Key
import net.kyori.adventure.key.Keyed
import net.kyori.adventure.translation.Translatable
import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.block.Block
import net.zero.block.BlockState
import net.zero.world.World

/**
 * A type of entity.
 *
 * @param T the type of entity
 */
@Suppress("INAPPLICABLE_JVM_NAME")
@ImmutableType
@SidedApi(Side.BOTH)
interface EntityType<out T : Entity> : Keyed, Translatable {

    /**
     * The category entities of this type are part of.
     */
    val category: EntityCategory

    /**
     * If this entity type can be summoned, with the /summon command, or by
     * spawning the entity through [World.spawnEntity].
     */
    @get:JvmName("isSummonable")
    val isSummonable: Boolean

    /**
     * If entities of this type are immune to all types of fire damage.
     */
    @get:JvmName("isImmuneToFire")
    val isImmuneToFire: Boolean

    /**
     * The radius of the circle in which the client will track the movement of
     * entities of this type.
     */
    val clientTrackingRange: Int

    /**
     * The interval between when entities of this type will be updated.
     */
    val updateInterval: Int

    /**
     * The base width of entities of this type.
     */
    val width: Float

    /**
     * The base height of entities of this type.
     */
    val height: Float

    /**
     * All blocks that entities of this type will not take damage from.
     */
    val immuneTo: Set<Block>

    /**
     * The identifier for the loot table that entities of this type will use to
     * determine what drops they will have when they are killed.
     */
    val lootTable: Key

    /**
     * Checks if entities of this type are immune (they will not be damaged by)
     * the given [block].
     *
     * @param block The block to check.
     *
     * @return `true` if entities are immune to the block, `false` otherwise.
     */
    fun isImmuneTo(block: BlockState): Boolean
}
