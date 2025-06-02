package net.zero.player

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi

/**
 * Settings for the visibility of chat messages for a player.
 */
@SidedApi(Side.BOTH)
enum class ChatVisibility {

    /**
     * In this mode, the client wants to see all messages sent by the server.
     */
    FULL,

    /**
     * In this mode, the client only wants to see system messages, such as
     * those sent as an output to a command, or when a player joins or leaves,
     * not messages that originate from a player.
     */
    SYSTEM,

    /**
     * In this mode, the client does not want to see any chat messages. They
     * will still see game state updates however, which are action bars that
     * inform the player of certain things, such as "You may not rest now,
     * there are monsters nearby."
     */
    HIDDEN
}
