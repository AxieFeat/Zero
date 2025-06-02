package net.zero.core.registry

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.core.resource.ResourceKey

/**
 * Something that holds registries.
 */
@SidedApi(Side.BOTH)
interface RegistryHolder {

    /**
     * All the registries contained within this registry holder.
     */
    val registries: Collection<Registry<*>>

    /**
     * Gets a registry from this registry holder with the given [key].
     *
     * @param E The registry type.
     * @param key The registry key.
     *
     * @return The registry, if present.
     */
    fun <E> getRegistry(key: ResourceKey<out Registry<E>>): Registry<E>?

    /**
     * Gets a defaulted registry from this registry holder with the
     * given [key].
     *
     * @param E The registry type.
     * @param key The registry key.
     *
     * @return The defaulted registry, if present.
     */
    fun <E> getDefaultedRegistry(key: ResourceKey<out Registry<E>>): DefaultedRegistry<E>?
}
