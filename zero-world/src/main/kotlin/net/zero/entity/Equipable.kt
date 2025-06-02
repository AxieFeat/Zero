package net.zero.entity

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.item.ItemStack

/**
 * Something that can be equipped with hand and armour items.
 */
@SidedApi(Side.BOTH)
interface Equipable {

    /**
     * Gets the item that this equipable holds in the given [hand].
     *
     * This may return [ItemStack.empty] if this equipable is not holding an item in
     * the given [hand].
     *
     * @param hand The hand.
     *
     * @return The item this mob is holding in the hand.
     */
    fun getHeldItem(hand: Hand): ItemStack

    /**
     * Sets the item that this mob holds in the given [hand] to the given
     * [item].
     *
     * @param hand The hand.
     * @param item The item.
     */
    @SidedApi(Side.SERVER)
    fun setHeldItem(hand: Hand, item: ItemStack)

    /**
     * Gets the armour item that this mob has equipped in the given [slot].
     *
     * This may return [ItemStack.empty] if this mob does not have any armour
     * equipped in the given [slot].
     *
     * @param slot The slot.
     */
    fun getArmor(slot: ArmorSlot): ItemStack

    /**
     * Sets the armour item that this mob has equipped in the given [slot] to
     * the given [item].
     *
     * @param slot The slot.
     * @param item The item.
     */
    @SidedApi(Side.SERVER)
    fun setArmor(slot: ArmorSlot, item: ItemStack)

    /**
     * Gets the equipment item that this mob has equipped in the given [slot].
     *
     * This may return [ItemStack.empty] if this mob does not have any armour
     * equipped in the given [slot].
     *
     * @param slot The slot.
     *
     * @return The item in the given slot.
     */
    fun getEquipment(slot: EquipmentSlot): ItemStack

    /**
     * Sets the equipment item that this mob has equipped in the given [slot] to
     * the given [item].
     *
     * @param slot The slot.
     * @param item The item.
     */
    @SidedApi(Side.SERVER)
    fun setEquipment(slot: EquipmentSlot, item: ItemStack)
}
