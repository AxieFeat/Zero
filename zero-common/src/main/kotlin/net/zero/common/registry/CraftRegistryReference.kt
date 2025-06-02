package net.zero.common.registry

import net.kyori.adventure.key.Key
import net.zero.core.registry.Registry
import net.zero.core.registry.RegistryReference
import net.zero.core.resource.ResourceKey

class CraftRegistryReference<T, V : T>(
    private val registry: Registry<T>,
    override val key: ResourceKey<V>
) : RegistryReference<V> {

    @Suppress("UNCHECKED_CAST")
    override fun get(): V = registry.get(key as ResourceKey<T>) as V

    object Factory : RegistryReference.Factory {

        override fun <T, V : T> of(registry: Registry<T>, key: Key): RegistryReference<V> {
            return CraftRegistryReference(registry, ResourceKey.of(registry.key.location, key))
        }
    }
}
