package net.zero.common.registry.holder

import net.zero.core.registry.Registry
import net.zero.core.resource.ResourceKey
import net.zero.core.tags.TagKey

/**
 * An object that holders and tag values (holder sets) can be retrieved from.
 */
interface HolderGetter<T> {

    fun get(key: ResourceKey<T>): Holder.Reference<T>?

    fun getOrThrow(key: ResourceKey<T>): Holder.Reference<T> = get(key) ?: error("Missing required element for key $key!")

    fun get(key: TagKey<T>): HolderSet.Named<T>?

    fun getOrThrow(key: TagKey<T>): HolderSet.Named<T> = get(key) ?: error("Missing required tag values for key $key!")

    /**
     * A provider for holder getters.
     */
    interface Provider {

        fun <T> lookup(key: ResourceKey<out Registry<out T>>): HolderGetter<T>?

        fun <T> lookupOrThrow(key: ResourceKey<out Registry<out T>>): HolderGetter<T> =
            lookup(key) ?: error("Required registry with key $key not found!")
    }
}
