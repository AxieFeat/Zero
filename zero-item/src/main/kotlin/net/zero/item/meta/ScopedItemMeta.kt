package net.zero.item.meta

import net.kyori.adventure.text.Component
import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import org.jetbrains.annotations.Contract
import java.util.function.Consumer

/**
 * An item meta subtype that changes all of the returns of functions in item
 * meta to a generic type to avoid all subtypes having to override all of the
 * functions.
 */
@ImmutableType
@SidedApi(Side.SERVER)
sealed interface ScopedItemMeta<B : ItemMetaBuilder<B, I>, I : ItemMeta> : ItemMeta,
    ItemMetaBuilder.Provider<B> {

    /**
     * Creates new item metadata from the result of applying the given
     * [builder].
     *
     * @param builder The builder function to apply.
     *
     * @return New item metadata.
     */
    @JvmSynthetic
    @Contract("_ -> new", pure = true)
    fun with(builder: B.() -> Unit): I = toBuilder().apply(builder).build()

    /**
     * Creates new item metadata from the result of applying the given
     * [builder].
     *
     * @param builder The builder function to apply.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun with(builder: Consumer<B>): I = with { builder.accept(this) }

    override fun withDamage(damage: Int): I

    override fun withUnbreakable(unbreakable: Boolean): I

    override fun withCustomModelData(data: Int): I

    override fun withName(name: Component?): I

    override fun withLore(lore: List<Component>): I

    override fun withLore(lore: Component): I

    override fun withoutLore(index: Int): I

    override fun withoutLore(lore: Component): I

    override fun withoutAttributeModifiers(): I
}
