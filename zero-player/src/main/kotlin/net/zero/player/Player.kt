package net.zero.player

import net.kyori.adventure.text.Component
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.entity.Equipable
import net.zero.entity.LivingEntity
import net.zero.inventory.Inventory
import net.zero.inventory.PlayerInventory
import net.zero.util.Direction
import net.zero.world.GameMode
import java.net.SocketAddress

/**
 * A player that is connected to the server and playing the game.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
@SidedApi(Side.BOTH)
interface Player : LivingEntity, Equipable {

    /**
     * The address that the player is currently connected from.
     */
    val address: SocketAddress

    /**
     * If this player can fly.
     */
    @get:JvmName("canFly")
    @set:JvmName("setFly")
    @set:SidedApi(Side.SERVER)
    var canFly: Boolean

    /**
     * If this player can build (place/break blocks).
     */
    @get:JvmName("canBuild")
    @set:JvmName("setBuild")
    @set:SidedApi(Side.SERVER)
    var canBuild: Boolean

    /**
     * If this player can place and break blocks with no delay.
     */
    @get:JvmName("canInstantlyBuild")
    @set:JvmName("setInstantlyBuild")
    @set:SidedApi(Side.SERVER)
    var canInstantlyBuild: Boolean

    /**
     * The current speed at which this player can walk at.
     */
    @set:SidedApi(Side.SERVER)
    var walkingSpeed: Float

    /**
     * The current speed at which this player can fly at.
     */
    @set:SidedApi(Side.SERVER)
    var flyingSpeed: Float

    /**
     * If this player is currently flying.
     */
    @get:JvmName("isFlying")
    @set:JvmName("setFlying")
    @set:SidedApi(Side.SERVER)
    var isFlying: Boolean

    /**
     * The settings for the player.
     */
    val settings: PlayerSettings

    /**
     * This player's current game mode.
     */
    @set:SidedApi(Side.SERVER)
    var gameMode: GameMode

    /**
     * The direction this player is currently facing.
     */
    val facing: Direction

    /**
     * The tab list for this player.
     */
    val tabList: TabList

    /**
     * The inventory of this player.
     *
     * This holds information on all of the items that are currently held by this player.
     */
    val inventory: PlayerInventory

    /**
     * The inventory that this player currently has open.
     */
    @set:SidedApi(Side.SERVER)
    var openInventory: Inventory?

    /**
     * The food level of this player.
     */
    @set:SidedApi(Side.SERVER)
    var foodLevel: Int

    /**
     * The food exhaustion level of this player.
     */
    @set:SidedApi(Side.SERVER)
    var foodExhaustionLevel: Float

    /**
     * The food saturation level of this player.
     */
    @set:SidedApi(Side.SERVER)
    var foodSaturationLevel: Float

    /**
     * The current ping of this player.
     */
    val ping: Int

    /**
     * Kicks the player with the given [text] shown.
     */
    @SidedApi(Side.SERVER)
    fun disconnect(text: Component)
}
