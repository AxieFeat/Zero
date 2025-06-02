package net.zero.common.registry.dynamic

import net.zero.core.registry.DefaultedRegistry
import net.zero.core.registry.Registry
import net.zero.core.registry.RegistryHolder
import net.zero.core.resource.ResourceKey

class SimpleRegistryHolder(
    private val entries: Map<out ResourceKey<out Registry<*>>, Registry<*>>
) : RegistryHolder {

    override val registries: Collection<Registry<*>>
        get() = entries.values

    @Suppress("UNCHECKED_CAST")
    override fun <E> getRegistry(key: ResourceKey<out Registry<E>>): Registry<E>? = entries.get(key) as? Registry<E>

    override fun <E> getDefaultedRegistry(key: ResourceKey<out Registry<E>>): DefaultedRegistry<E>? = getRegistry(key) as? DefaultedRegistry<E>
}
