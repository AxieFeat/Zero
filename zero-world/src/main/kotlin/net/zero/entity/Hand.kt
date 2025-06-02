package net.zero.entity

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi

/**
 * Represents values for the current hand of some object (e.g. a player).
 */
@SidedApi(Side.BOTH)
enum class Hand {

    MAIN,
    OFF

}
