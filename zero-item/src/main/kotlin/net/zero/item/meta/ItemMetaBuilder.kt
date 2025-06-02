package net.zero.item.meta

import net.kyori.adventure.text.Component
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.annotation.dsl.MetaDsl
import org.jetbrains.annotations.Contract

/**
 * The base builder for item metadata.
 */
@MetaDsl
@SidedApi(Side.SERVER)
interface ItemMetaBuilder<B : ItemMetaBuilder<B, I>, I : ItemMeta> {

    /**
     * Sets the damage of the item to the given [damage].
     *
     * @param damage The damage.
     *
     * @return This builder.
     */
    @MetaDsl
    @Contract("_ -> this", mutates = "this")
    fun damage(damage: Int): B

    /**
     * Sets whether the item is unbreakable to the given [value].
     *
     * @param value Whether the item is unbreakable.
     *
     * @return This builder.
     */
    @MetaDsl
    @Contract("_ -> this", mutates = "this")
    fun unbreakable(value: Boolean): B

    /**
     * Makes the item unbreakable.
     *
     * @return This builder.
     */
    @MetaDsl
    @Contract("-> this", mutates = "this")
    fun unbreakable(): B = unbreakable(true)

    /**
     * Sets the custom model data for the item to the given [data].
     *
     * @param data The custom model data.
     *
     * @return This builder.
     */
    @MetaDsl
    @Contract("_ -> this", mutates = "this")
    fun customModelData(data: Int): B

    /**
     * Sets the name of the item to the given [name].
     *
     * @param name The name.
     *
     * @return This builder.
     */
    @MetaDsl
    @Contract("_ -> this", mutates = "this")
    fun name(name: Component?): B

    /**
     * Sets the lore of the item to the given [lore].
     *
     * @param lore The lore.
     *
     * @return This builder.
     */
    @MetaDsl
    @Contract("_ -> this", mutates = "this")
    fun lore(lore: Collection<Component>): B

    /**
     * Sets the lore of the item to the given [lore].
     *
     * @param lore The lore.
     *
     * @return This builder.
     */
    @MetaDsl
    @Contract("_ -> this", mutates = "this")
    fun lore(vararg lore: Component): B = lore(lore.asList())

    /**
     * Adds the given [lore] line to the lore of the item.
     *
     * @param lore The lore line to add.
     *
     * @return This builder.
     */
    @MetaDsl
    @Contract("_ -> this", mutates = "this")
    fun addLore(lore: Component): B

    /**
     * Builds the resulting item metadata.
     *
     * @return The built item metadata.
     */
    @MetaDsl
    @Contract("-> new", pure = true)
    fun build(): I

    /**
     * A provider of an item meta builder.
     */
    @SidedApi(Side.SERVER)
    interface Provider<T> {

        /**
         * Converts this object to a builder with all of the properties of this
         * object set by default.
         *
         * @return A new builder.
         */
        @Contract("-> new", pure = true)
        fun toBuilder(): T
    }
}
