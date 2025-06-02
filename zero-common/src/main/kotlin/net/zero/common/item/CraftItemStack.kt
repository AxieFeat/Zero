package net.zero.common.item

import net.kyori.adventure.text.event.HoverEvent
import net.zero.item.ItemStack
import net.zero.item.ItemType
import net.zero.item.meta.ItemMeta
import java.util.function.UnaryOperator

class CraftItemStack(
    override val type: ItemType,
    override val amount: Int,
    override val meta: ItemMeta
) : ItemStack {
    override fun <I : ItemMeta> meta(type: Class<I>): I? {
        TODO("Not yet implemented")
    }

    override fun withType(type: ItemType): ItemStack {
        TODO("Not yet implemented")
    }

    override fun withAmount(amount: Int): ItemStack {
        TODO("Not yet implemented")
    }

    override fun withMeta(meta: ItemMeta): ItemStack {
        TODO("Not yet implemented")
    }

    override fun asHoverEvent(op: UnaryOperator<HoverEvent.ShowItem>): HoverEvent<HoverEvent.ShowItem> {
        TODO("Not yet implemented")
    }
}