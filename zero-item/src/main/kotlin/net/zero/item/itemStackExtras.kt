@file:JvmSynthetic
package net.zero.item

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.annotation.dsl.ItemDsl
import org.jetbrains.annotations.Contract
import net.zero.item.meta.ItemMeta
import net.zero.item.meta.ItemMetaBuilder

/**
 * Gets the metadata for this item stack as the given type [I], or returns
 * null if the metadata could not be casted to the given type [I].
 *
 * @param I The metadata type.
 *
 * @return The metadata as the type, or null if the metadata is not of the type.
 */
@JvmSynthetic
@SidedApi(Side.SERVER)
inline fun <reified I : ItemMeta> ItemStack.meta(): I? = meta(I::class.java)

/**
 * Creates a new item stack with meta retrieved applying the given
 * [builder] to a new item metadata builder.
 *
 * @param builder The builder to apply.
 *
 * @return A new item stack.
 */
@JvmSynthetic
@Contract("_ -> new", pure = true)
@SidedApi(Side.SERVER)
inline fun ItemStack.withMeta(builder: ItemMeta.Builder.() -> Unit): ItemStack = withMeta(ItemMeta.builder().apply(builder).build())

/**
 * Creates a new item stack with meta retrieved applying the given
 * [builder] to a new meta builder created with the given type [P].
 *
 * @param B The builder type.
 * @param P The metadata type.
 * @param builder The builder to apply.
 *
 * @return A new item stack.
 */
@JvmSynthetic
@Contract("_ -> new", pure = true)
@JvmName("withMetaGeneric")
@SidedApi(Side.SERVER)
inline fun <B : IMB<B, P>, reified P> ItemStack.withMeta(builder: B.() -> Unit): ItemStack where P : IMP<B>, P : ItemMeta =
    withMeta(ItemMeta.builder(P::class.java).apply(builder).build())

/**
 * Applies the given [builder] function to the metadata builder for
 * this builder.
 *
 * @param builder The builder function to apply.
 *
 * @return This builder.
 */
@ItemDsl
@JvmSynthetic
@Contract("_ -> this", mutates = "this")
@SidedApi(Side.SERVER)
inline fun ItemStack.Builder.meta(builder: ItemMeta.Builder.() -> Unit): ItemStack.Builder = meta(ItemMeta.builder().apply(builder).build())

/**
 * Applies the given [builder] function to the metadata builder for
 * this builder.
 *
 * @param B The builder type.
 * @param P The metadata type.
 * @param builder The builder function to apply.
 *
 * @return This builder.
 */
@ItemDsl
@JvmSynthetic
@Contract("_ -> this", mutates = "this")
@JvmName("metaGeneric")
@SidedApi(Side.SERVER)
inline fun <B : IMB<B, P>, reified P> ItemStack.Builder.meta(builder: B.() -> Unit): ItemStack.Builder where P : IMP<B>, P : ItemMeta =
    meta(ItemMeta.builder(P::class.java).apply(builder).build())

private typealias IMB<B, P> = ItemMetaBuilder<B, P>
private typealias IMP<T> = ItemMetaBuilder.Provider<T>
