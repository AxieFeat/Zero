package net.zero.common.registry.dynamic

import net.zero.core.registry.Registry

interface RegistryContainer {

    fun registries(): Collection<Registry<*>>
}
