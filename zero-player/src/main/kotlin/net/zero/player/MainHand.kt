package net.zero.player

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi

/**
 * Represents values for the primary hand of some object (e.g. a player).
 */
@SidedApi(Side.BOTH)
enum class MainHand {

    LEFT,
    RIGHT

}
