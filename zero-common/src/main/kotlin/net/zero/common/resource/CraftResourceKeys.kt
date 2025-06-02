package net.zero.common.resource

import net.kyori.adventure.key.Key
import net.zero.common.registry.WritableRegistry
import net.zero.core.registry.Registry
import net.zero.core.registry.RegistryRoots
import net.zero.core.resource.ResourceKey

object CraftResourceKeys {

    @JvmField
    val PARENT: ResourceKey<out Registry<WritableRegistry<*>>> = zero("root")

    @JvmStatic
    private fun <T> zero(key: String): ResourceKey<out Registry<T>> = ResourceKey.of(RegistryRoots.ZERO, Key.key(key))

}
