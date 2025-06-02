package net.zero.entity

import net.kyori.adventure.identity.Identified
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.event.HoverEventSource
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.command.Sender
import net.zero.core.scheduling.Scheduler
import net.zero.util.BoundingBox
import net.zero.util.Position
import net.zero.util.Vec3d
import net.zero.world.World
import net.zero.world.damage.DamageSource
import java.util.UUID

/**
 * An entity somewhere in a world.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
@SidedApi(Side.BOTH)
interface Entity : Sender, HoverEventSource<HoverEvent.ShowEntity>, Sound.Emitter, Sound.Source.Provider, Identified {

    /**
     * The ID of this entity.
     *
     * This should be unique whilst the server is running, however it should
     * not be persisted in any way, shape, or form, as it is retrieved when the
     * entity object is first created, and discarded when the entity is
     * removed.
     */
    val id: Int

    /**
     * The UUID of this entity.
     *
     * This will always be unique, and exists specifically to provide
     * uniqueness across server runs (persistent uniqueness).
     */
    val uuid: UUID

    /**
     * The world this entity is currently in.
     */
    val world: World

    /**
     * The type of this entity.
     */
    val type: EntityType<Entity>

    /**
     * The custom name of the entity. May be null if the custom name has not
     * been set.
     */
    @set:SidedApi(Side.SERVER)
    var customName: Component?

    /**
     * If the current custom name is visible or not.
     */
    @get:JvmName("isCustomNameVisible")
    @set:JvmName("setCustomNameVisible")
    @set:SidedApi(Side.SERVER)
    var isCustomNameVisible: Boolean

    /**
     * The display name of this entity in their current team, if they are in
     * a team.
     *
     * If this entity is not in a team, this will return the name of the
     * entity.
     */
    val displayName: Component

    /**
     * The current position of this entity.
     *
     * This can be updated using [teleport].
     */
    val position: Position

    /**
     * The current delta X, Y, and Z values of this entity, in metres per tick.
     */
    @set:SidedApi(Side.SERVER)
    var velocity: Vec3d

    /**
     * The current bounding box of this entity. This is used to determine the
     * area in which an entity can be interacted with, also known as a hitbox.
     */
    val boundingBox: BoundingBox

    /**
     * If this entity should not take damage from any source.
     *
     * When this is true, the following statements are also true:
     * - If this entity is living, it cannot be moved by fishing rods, attacks,
     * explosions, or projectiles.
     * - Objects such as vehicles and item frames cannot be destroyed unless
     * their support is also removed.
     * - If this entity is a player, it will also be ignored by any hostile
     * mobs.
     *
     * Setting this to true, however, will not prevent this entity from being
     * damaged by a player in creative mode.
     */
    @set:SidedApi(Side.SERVER)
    var isInvulnerable: Boolean

    /**
     * If this entity is currently on fire.
     */
    @set:SidedApi(Side.SERVER)
    var isOnFire: Boolean

    /**
     * If this entity is on terra firma.
     */
    @get:JvmName("isOnGound")
    val isOnGround: Boolean

    /**
     * If this entity is sneaking.
     */
    @get:JvmName("isSneaking")
    @set:JvmName("setSneaking")
    @set:SidedApi(Side.SERVER)
    var isSneaking: Boolean

    /**
     * If this entity is sprinting.
     */
    @get:JvmName("isSprinting")
    @set:JvmName("setSprinting")
    @set:SidedApi(Side.SERVER)
    var isSprinting: Boolean

    /**
     * If this entity is swimming.
     */
    @get:JvmName("isSwimming")
    @set:JvmName("setSwimming")
    @set:SidedApi(Side.SERVER)
    var isSwimming: Boolean

    /**
     * If this entity is invisible.
     */
    @get:JvmName("isInvisible")
    @set:JvmName("setInvisible")
    @set:SidedApi(Side.SERVER)
    var isInvisible: Boolean

    /**
     * If this entity has a glowing outline.
     */
    @get:JvmName("isGlowing")
    @set:JvmName("setGlowing")
    @set:SidedApi(Side.SERVER)
    var isGlowing: Boolean

    /**
     * If this entity is silenced, meaning it does not produce any sounds.
     *
     * This also means that any attempts to play a sound with this entity as
     * its emitter will also fail.
     */
    @get:JvmName("isSilent")
    @set:JvmName("setSilent")
    @set:SidedApi(Side.SERVER)
    var isSilent: Boolean

    /**
     * If this entity is affected by gravity.
     *
     * When this value is false, this entity will not naturally fall when it
     * has no blocks under its feet to support it.
     */
    @get:JvmName("hasGravity")
    @set:JvmName("setGravity")
    @set:SidedApi(Side.SERVER)
    var hasGravity: Boolean

    /**
     * The amount of ticks this entity has existed for.
     *
     * This will be increased by 1 for every tick this entity exists for.
     */
    @SidedApi(Side.SERVER)
    val ticksExisted: Int

    /**
     * How much air this entity has, in ticks.
     *
     * This will fill to a maximum of 300 in air, giving the entity a total of
     * 15 seconds underwater before the entity begins to drown and die.
     *
     * If this value reaches 0, and this entity is underwater, it will
     * take 1 health point (half a heart) of damage for every second
     * it remains underwater.
     */
    val airSupply: Int

    /**
     * This value can mean one of two things, depending on if the value is
     * positive or negative.
     *
     * When the value is positive, this represents the number of ticks until
     * the entity is no longer [on fire][isOnFire].
     *
     * When the value is negative, this represents the number of ticks this
     * entity can survive in fire for before burning.
     */
    val remainingFireTicks: Int

    /**
     * The amount of ticks this entity has been freezing for.
     *
     * This value will increase by 1 for every tick this entity is in powder
     * snow, to a maximum of 300, and decrease by 2 for every tick this entity
     * is not in powder snow.
     */
    val frozenTicks: Int

    /**
     * The distance this entity has fallen.
     *
     * The larger the value of this, the more damage the entity will take when
     * it lands.
     */
    val fallDistance: Float

    /**
     * The scheduler for this entity.
     *
     * Useful for scheduling tasks that should only exist for the lifetime of
     * the entity, as all tasks that are scheduler with this scheduler will
     * stop running after the entity is removed.
     */
    @SidedApi(Side.SERVER)
    val scheduler: Scheduler

    /**
     * If this entity is currently touching water.
     */
    fun isInWater(): Boolean

    /**
     * If this entity is currently touching lava.
     */
    fun isInLava(): Boolean

    /**
     * If this entity is currently fully submerged under water, meaning its
     * entire hitbox must be under water.
     */
    fun isUnderwater(): Boolean

    /**
     * Teleports this entity to the given [position].
     *
     * @param position The position to teleport to.
     */
    @SidedApi(Side.SERVER)
    fun teleport(position: Position)

    /**
     * Teleports this entity to the given other [entity].
     *
     * @param entity The entity to teleport to.
     */
    @SidedApi(Side.SERVER)
    fun teleport(entity: Entity) {
        teleport(entity.position)
    }

    /**
     * Damages this entity with the given [source].
     *
     * @param source The source of the damage.
     * @param damage The damage amount.
     *
     * @return `true` if damaging this entity was successful, `false` otherwise.
     */
    @SidedApi(Side.SERVER)
    fun damage(source: DamageSource, damage: Float): Boolean

    /**
     * Removes this entity from the world it is currently in.
     *
     * This removal may happen immediately, or be queued up and happen in the
     * near future, depending on how it is implemented.
     */
    @SidedApi(Side.SERVER)
    fun remove()
}
