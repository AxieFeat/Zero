package net.zero.entity

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi

/**
 * A slot that a piece of equipment may be in.
 */
@SidedApi(Side.BOTH)
interface EquipmentSlot {

    /**
     * The type of equipment that this slot is for.
     */
    val type: Type

    /**
     * A type of equipment slot.
     */
    @SidedApi(Side.BOTH)
    enum class Type {

        HAND,
        ARMOR
    }
}
