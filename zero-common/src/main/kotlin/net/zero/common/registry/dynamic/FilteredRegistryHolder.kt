package net.zero.common.registry.dynamic

import com.google.common.collect.Collections2
import net.zero.core.registry.DefaultedRegistry
import net.zero.core.registry.Registry
import net.zero.core.registry.RegistryHolder
import net.zero.core.resource.ResourceKey
import java.util.function.Predicate

class FilteredRegistryHolder(
    private val backing: RegistryHolder,
    private val filter: Predicate<ResourceKey<out Registry<*>>>
) : RegistryHolder {

    override val registries: Collection<Registry<*>>
        get() = Collections2.filter(backing.registries) { filter.test(it.key) }

    override fun <E> getRegistry(key: ResourceKey<out Registry<E>>): Registry<E>? {
        if (!filter.test(key)) return null
        return backing.getRegistry(key)
    }

    override fun <E> getDefaultedRegistry(key: ResourceKey<out Registry<E>>): DefaultedRegistry<E>? {
        if (!filter.test(key)) return null
        return backing.getDefaultedRegistry(key)
    }
}
