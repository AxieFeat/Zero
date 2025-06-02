package net.zero.core.registry

import net.kyori.adventure.key.Key
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.core.resource.ResourceKey

/**
 * A registry with a default key-value pair.
 */
@SidedApi(Side.BOTH)
interface DefaultedRegistry<T> : Registry<T> {

    /**
     * The default key for this defaulted registry.
     */
    val defaultKey: Key

    override fun get(key: Key): T

    override fun get(key: ResourceKey<T>): T

    override fun getKey(value: T): Key
}
