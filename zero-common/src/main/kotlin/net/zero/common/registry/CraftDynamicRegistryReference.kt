package net.zero.common.registry

import net.kyori.adventure.key.Key
import net.zero.core.registry.DynamicRegistryReference
import net.zero.core.registry.Registry
import net.zero.core.registry.RegistryHolder
import net.zero.core.resource.ResourceKey

class CraftDynamicRegistryReference<T>(
    private val registry: ResourceKey<out Registry<T>>,
    override val key: ResourceKey<T>
) : DynamicRegistryReference<T> {

    override fun get(holder: RegistryHolder): T {
        return requireNotNull(holder.getRegistry(registry)?.get(key)) { "Could not find value for key $key in holder $holder!" }
    }

    object Factory : DynamicRegistryReference.Factory {

        override fun <T> of(registry: ResourceKey<out Registry<T>>, key: Key): DynamicRegistryReference<T> {
            return CraftDynamicRegistryReference(registry, ResourceKey.of(registry, key))
        }
    }
}
