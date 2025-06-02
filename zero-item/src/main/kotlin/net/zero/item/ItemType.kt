package net.zero.item

import net.kyori.adventure.key.Keyed
import net.kyori.adventure.translation.Translatable
import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.core.sound.SoundEvent

/**
 * Represents a type of item.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
@ImmutableType
@SidedApi(Side.BOTH)
interface ItemType : Translatable, Keyed {

    /**
     * The rarity of the item.
     */
    val rarity: ItemRarity

    /**
     * The maximum amount of this item type that can be stacked in a single [item stack][ItemStack].
     */
    val maximumStackSize: Int

    /**
     * If items of this type actually take damage when they use or break blocks.
     */
    @get:JvmName("canBreak")
    val canBreak: Boolean

    /**
     * The maximum amount of things items of this type can use/break before they break.
     */
    val durability: Int

    /**
     * If items of this type can be eaten.
     */
    @get:JvmName("isEdible")
    val isEdible: Boolean

    /**
     * If items of this type are resistant to fire, meaning they won't burn up
     * when thrown in to any source of fire, such as standard fire or lava.
     */
    @get:JvmName("isFireResistant")
    val isFireResistant: Boolean

    /**
     * The sound that items of this type will make when they are eaten.
     *
     * This should only be used for edible items, and clients should already
     * be expected to output this sound, so it is unlikely that you will need
     * to use this.
     */
    val eatingSound: SoundEvent

    /**
     * The sound that items of this type will make when they are drank.
     *
     * This should only be used for drinkable items, and clients should already
     * be expected to output this sound, so it is unlikely that you will need
     * to use this.
     */
    val drinkingSound: SoundEvent

}
