package net.zero.entity

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.entity.attribute.AttributeHolder

/**
 * Represents an entity that lives in a world.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
@SidedApi(Side.BOTH)
interface LivingEntity : Entity, AttributeHolder {

    /**
     * The current health of this entity.
     */
    @set:SidedApi(Side.SERVER)
    var health: Float

    /**
     * The maximum health of this entity.
     */
    val maxHealth: Float

    /**
     * The amount of absorption this living entity has.
     */
    @set:SidedApi(Side.SERVER)
    var absorption: Float

    /**
     * If this entity is currently using an item.
     */
    @get:JvmName("isUsingItem")
    @set:JvmName("setUsingItem")
    @set:SidedApi(Side.SERVER)
    var isUsingItem: Boolean

    /**
     * The hand the entity is currently using.
     */
    @set:SidedApi(Side.SERVER)
    var hand: Hand

    /**
     * If this entity is dead or not.
     */
    @get:JvmName("isDead")
    val isDead: Boolean

    /**
     * The number of ticks this entity has been dead for.
     * This is used to control death animations.
     *
     * Will be 0 whilst this entity is alive.
     */
    val deathTime: Int

    /**
     * The number of ticks this entity will turn red for after being hit.
     *
     * Will be 0 when not recently hit.
     */
    val hurtTime: Int

    /**
     * The last time, in ticks, this entity was damaged.
     *
     * Calculated as the [number of ticks since the entity's creation][ticksExisted].
     */
    val lastHurtTimestamp: Int
}
