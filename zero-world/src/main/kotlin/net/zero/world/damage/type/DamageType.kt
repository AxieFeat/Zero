package net.zero.world.damage.type

import net.kyori.adventure.key.Keyed
import net.kyori.adventure.translation.Translatable
import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi

/**
 * A type of damage to something.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
@ImmutableType
@SidedApi(Side.BOTH)
interface DamageType : Keyed, Translatable {

    /**
     * If this damage type will damage its target's helmet.
     */
    val damagesHelmet: Boolean

    /**
     * If this damage type bypasses protection from the target's armour.
     */
    val bypassesArmor: Boolean

    /**
     * If this damage type bypasses invulnerability protection, such as a
     * player being in creative.
     */
    val bypassesInvulnerability: Boolean

    /**
     * If this damage type bypasses any protection offered by magic, such as
     * potions.
     */
    val bypassesMagic: Boolean

    /**
     * The amount of exhaustion that this damage type will inflict upon a
     * target.
     */
    val exhaustion: Float

    /**
     * If this damage type causes fire damage.
     */
    @get:JvmName("isFire")
    val isFire: Boolean

    /**
     * If this damage type causes projectile damage.
     */
    @get:JvmName("isProjectile")
    val isProjectile: Boolean

    /**
     * If this damage type's effects are increased as the difficulty increases.
     */
    val scalesWithDifficulty: Boolean

    /**
     * If this damage type causes magic damage.
     */
    @get:JvmName("isMagic")
    val isMagic: Boolean

    /**
     * If this damage type causes explosion damage.
     */
    @get:JvmName("isExplosion")
    val isExplosion: Boolean

    /**
     * If this damage type causes fall damage.
     */
    @get:JvmName("isFall")
    val isFall: Boolean

    /**
     * If this damage type causes thorns damage.
     */
    @get:JvmName("isThorns")
    val isThorns: Boolean

    /**
     * If the target this damage type is applied to will be aggravated by the
     * damage caused by applying this damage type.
     */
    val aggravatesTarget: Boolean
}
