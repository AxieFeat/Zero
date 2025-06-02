package net.zero.entity

import net.kyori.adventure.key.Keyed
import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi

/**
 * A category of entity that applies certain spawning mechanics and behaviours.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
@ImmutableType
@SidedApi(Side.BOTH)
interface EntityCategory : Keyed {

    /**
     * If the mob will be friendly towards the player.
     */
    @get:JvmName("isFriendly")
    val isFriendly: Boolean

    /**
     * The distance that the mob has to be from the player to be despawned.
     */
    val despawnDistance: Int

}
