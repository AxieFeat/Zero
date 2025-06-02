package net.zero.command

import net.kyori.adventure.audience.Audience
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi

/**
 * A sender is an interface representing the sender of a command.
 */
@SidedApi(Side.BOTH)
interface Sender : Audience {

    /**
     * The name of the sender.
     *
     * How this is defined is entirely dependent on the subtype.
     */
    val name: String

}