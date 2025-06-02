package net.zero.item.meta

import net.kyori.adventure.text.Component
import net.zero.annotation.ImmutableType
import net.zero.annotation.TypeFactory
import net.zero.annotation.dsl.MetaDsl
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.core.Zero
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import org.jetbrains.annotations.Unmodifiable

/**
 * Holder for various item metadata values for an item stack.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
@ImmutableType
@SidedApi(Side.BOTH)
interface ItemMeta {

    /**
     * The current damage on the item.
     */
    val damage: Int

    /**
     * If the item cannot be broken.
     */
    @get:JvmName("isUnbreakable")
    val isUnbreakable: Boolean

    /**
     * The custom model data for the item.
     *
     * The meaning of this may vary, but it is usually used by resource packs
     * to determine what variant of an item should be displayed, in the case
     * of adding custom items to the game only using a resource pack.
     */
    val customModelData: Int

    /**
     * The display name of the item.
     *
     * If null, the default display name of the item will be used.
     */
    val name: Component?

    /**
     * The lore of the item.
     */
    val lore: @Unmodifiable List<Component>

    /**
     * Creates new item metadata with the given [damage].
     *
     * @param damage The new damage.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    @SidedApi(Side.SERVER)
    fun withDamage(damage: Int): ItemMeta

    /**
     * Creates new item metadata with the given [unbreakable] setting.
     *
     * @param unbreakable The new unbreakable setting.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    @SidedApi(Side.SERVER)
    fun withUnbreakable(unbreakable: Boolean): ItemMeta

    /**
     * Creates new item metadata with the given custom model [data].
     *
     * @param data The new custom model data.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    @SidedApi(Side.SERVER)
    fun withCustomModelData(data: Int): ItemMeta

    /**
     * Creates new item metadata with the given [name].
     *
     * @param name The new name.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    @SidedApi(Side.SERVER)
    fun withName(name: Component?): ItemMeta

    /**
     * Creates new item metadata with the given [lore].
     *
     * @param lore The new lore.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    @SidedApi(Side.SERVER)
    fun withLore(lore: List<Component>): ItemMeta

    /**
     * Creates new item metadata with the given [lore] line added to the bottom
     * of the lore text.
     *
     * @param lore the lore line to add.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    @SidedApi(Side.SERVER)
    fun withLore(lore: Component): ItemMeta

    /**
     * Creates new item metadata with the lore line at the given [index]
     * removed from the lore.
     *
     * @param index the index of the lore line to remove.
     *
     * @return New item metadata.
     *
     * @throws IllegalArgumentException if the index would result in an out of bounds exception, i.e. when it is too small or too big.
     */
    @Contract("_ -> new", pure = true)
    @SidedApi(Side.SERVER)
    fun withoutLore(index: Int): ItemMeta

    /**
     * Creates new item metadata with the given [lore] line removed from the
     * lore.
     *
     * @param lore The lore line to remove.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    @SidedApi(Side.SERVER)
    fun withoutLore(lore: Component): ItemMeta

    /**
     * Creates new item metadata without any modifiers.
     *
     * @return New item metadata.
     */
    @Contract("-> new", pure = true)
    @SidedApi(Side.SERVER)
    fun withoutAttributeModifiers(): ItemMeta

    /**
     * A builder for building item metadata.
     */
    @MetaDsl
    @SidedApi(Side.SERVER)
    interface Builder : ItemMetaBuilder<Builder, ItemMeta>

    @ApiStatus.Internal
    @TypeFactory
    @SidedApi(Side.SERVER)
    interface Factory {

        fun builder(): Builder

        fun <B : ItemMetaBuilder<B, P>, P : ItemMetaBuilder.Provider<B>> builder(type: Class<P>): B
    }

    companion object {

        /**
         * Creates a new builder for building item metadata.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("-> new", pure = true)
        @SidedApi(Side.SERVER)
        fun builder(): Builder = Zero.factory<Factory>().builder()

        @JvmSynthetic
        @PublishedApi
        @SidedApi(Side.SERVER)
        internal fun <B : ItemMetaBuilder<B, P>, P : ItemMetaBuilder.Provider<B>> builder(type: Class<P>): B =
            Zero.factory<Factory>().builder(type)
    }
}
