package net.zero.item

import net.kyori.adventure.text.format.NamedTextColor
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi

/**
 * The rarity of an item. This determines what color the lore text appears as
 * when the tooltip is read.
 */
@SidedApi(Side.BOTH)
interface ItemRarity {

    /**
     * The color of this rarity.
     */
    val color: NamedTextColor

}
