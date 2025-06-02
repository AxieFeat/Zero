package net.zero.common.item

import net.kyori.adventure.key.Key
import net.zero.core.sound.SoundEvent
import net.zero.item.ItemRarity
import net.zero.item.ItemType

data class CraftIemType(
    override val rarity: ItemRarity,
    override val maximumStackSize: Int,
    override val canBreak: Boolean,
    override val durability: Int,
    override val isEdible: Boolean,
    override val isFireResistant: Boolean,
    override val eatingSound: SoundEvent,
    override val drinkingSound: SoundEvent
) : ItemType {

    override fun translationKey(): String {
        TODO("Not yet implemented")
    }

    override fun key(): Key {
        TODO("Not yet implemented")
    }
}