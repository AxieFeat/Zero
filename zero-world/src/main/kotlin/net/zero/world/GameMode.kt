package net.zero.world

import net.kyori.adventure.translation.Translatable
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.world.damage.type.DamageType

/**
 * A game mode that a player may be in that determines specific things to do
 * with what the player is able to do, such as being able to build, fly, not
 * take damage, or fly through walls.
 */
@SidedApi(Side.BOTH)
enum class GameMode : Translatable {

    /**
     * Survival mode is the default game mode. In it, you can access most
     * gameplay features, but you will take damage, and cannot fly.
     */
    SURVIVAL,

    /**
     * Creative mode grants you access to spawn in any block in the game. It
     * also grants you the ability to fly around the world freely, break
     * blocks instantly, and you can only take damage from types that
     * [bypass invulnerability][DamageType.bypassesInvulnerability].
     */
    CREATIVE,

    /**
     * Adventure mode is designed for custom maps. In it, your block breaking
     * and placing are restricted, you still take damage like normal, and you
     * cannot fly.
     */
    ADVENTURE,

    /**
     * Spectator mode is designed for spectating things. In it, you will take
     * no damage from anything, not even types that bypass invulnerability, and
     * you can fly through walls. You cannot interact with anything in the
     * world, including breaking and placing blocks, attacking entities, and
     * opening containers.
     */
    SPECTATOR;

    override fun translationKey(): String = "gameMode.${name.lowercase()}"
}
