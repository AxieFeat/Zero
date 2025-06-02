@file:JvmSynthetic
package net.zero.item.meta

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.annotation.dsl.MetaDsl
import org.jetbrains.annotations.Contract

/**
 * Creates new item metadata from the result of applying the given [builder]
 * function.
 *
 * @param builder The builder.
 *
 * @return New item metadata.
 */
@MetaDsl
@JvmSynthetic
@Contract("_ -> new", pure = true)
@SidedApi(Side.SERVER)
inline fun itemMeta(builder: ItemMeta.Builder.() -> Unit): ItemMeta = ItemMeta.builder().apply(builder).build()

/**
 * Creates new item metadata of the given type [P] from the result of applying
 * the given [builder] function to a new builder of type [B].
 *
 * @param B The type of the builder.
 * @param P The type of the metadata.
 * @param builder The builder.
 *
 * @return New item metadata.
 */
@MetaDsl
@JvmSynthetic
@JvmName("itemMetaGeneric")
@Contract("_ -> new", pure = true)
@SidedApi(Side.SERVER)
inline fun <B : ItemMetaBuilder<B, P>, reified P : ItemMetaBuilder.Provider<B>> itemMeta(builder: B.() -> Unit): P =
    ItemMeta.builder(P::class.java).apply(builder).build()