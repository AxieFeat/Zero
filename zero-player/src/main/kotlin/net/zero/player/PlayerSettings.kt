package net.zero.player

import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import java.util.Locale

/**
 * The settings for a player indicated by the client.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
@ImmutableType
@SidedApi(Side.BOTH)
interface PlayerSettings {

    /**
     * The locale of the player.
     *
     * If this has not been specified by the client, this will be null.
     */
    val locale: Locale?

    /**
     * The amount of chunks that the player will see in front of them,
     * excluding the chunk the player is in.
     */
    val viewDistance: Int

    /**
     * The chat visibility of the player.
     */
    val chatVisibility: ChatVisibility

    /**
     * If the player accepts colours in chat messages.
     */
    @get:JvmName("hasChatColors")
    val hasChatColors: Boolean

    /**
     * The skin parts that the player has shown.
     */
    val skinParts: SkinParts

    /**
     * The primary hand of the player.
     */
    val mainHand: MainHand

    /**
     * If the player allows appearing in the player sample on the server
     * status.
     */
    @get:JvmName("allowsServerListing")
    val allowsServerListing: Boolean
}
