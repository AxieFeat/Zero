package net.zero.common.registry.dynamic

import com.google.common.collect.ImmutableMap
import net.zero.common.registry.CraftRegistry
import net.zero.core.registry.Registry
import net.zero.core.resource.ResourceKey
import net.zero.common.registry.holder.HolderLookup
import net.zero.common.util.ImmutableMaps
import java.util.stream.Collectors
import java.util.stream.Stream

interface RegistryAccess : HolderLookup.Provider {

    fun <E> getRegistry(key: ResourceKey<out Registry<out E>>): CraftRegistry<E>?

    override fun <T> lookup(key: ResourceKey<out Registry<out T>>): HolderLookup.ForRegistry<T>? = getRegistry(key)?.asLookup()

    fun <E> registryOrThrow(key: ResourceKey<out Registry<out E>>): CraftRegistry<E> = getRegistry(key) ?: error("Missing required registry $key!")

    fun registries(): Stream<RegistryEntry<*>>

    fun freeze(): Frozen {
        class FrozenAccess(entries: Stream<RegistryEntry<*>>) : ImmutableImpl(entries), Frozen
        return FrozenAccess(registries().map { it.freeze() })
    }

    interface Frozen : RegistryAccess

    open class ImmutableImpl : RegistryAccess {

        private val registries: Map<out RegistryKey<*>, CraftRegistry<*>>

        constructor(map: Map<out RegistryKey<*>, CraftRegistry<*>>) {
            registries = ImmutableMaps.copyOf(map)
        }

        constructor(registries: List<CraftRegistry<*>>) {
            this.registries = registries.stream().collect(Collectors.toUnmodifiableMap({ it.key }, { it }))
        }

        constructor(entries: Stream<RegistryEntry<*>>) {
            registries = entries.collect(ImmutableMap.toImmutableMap({ it.key }, { it.value }))
        }

        @Suppress("UNCHECKED_CAST")
        override fun <E> getRegistry(key: RegistryKey<out E>): CraftRegistry<E>? = registries.get(key) as? CraftRegistry<E>

        override fun registries(): Stream<RegistryEntry<*>> = registries.entries.stream().map {
            RegistryEntry.fromMapEntry(
                it
            )
        }
    }

    @JvmRecord
    data class RegistryEntry<T>(val key: ResourceKey<out Registry<T>>, val value: CraftRegistry<T>) {

        fun freeze(): RegistryEntry<T> = RegistryEntry(key, value.freeze())

        companion object {

            @JvmStatic
            @Suppress("UNCHECKED_CAST")
            fun <T, R : CraftRegistry<out T>> fromMapEntry(entry: Map.Entry<RegistryKey<*>, R>): RegistryEntry<T> =
                RegistryEntry(entry.key as RegistryKey<T>, entry.value as CraftRegistry<T>)
        }
    }

    companion object {

        @JvmField
        val EMPTY: Frozen = ImmutableImpl(ImmutableMaps.of()).freeze()

        @JvmStatic
        fun fromRegistryOfRegistries(registry: CraftRegistry<out CraftRegistry<*>>): Frozen = object : Frozen {
            @Suppress("UNCHECKED_CAST")
            override fun <E> getRegistry(key: ResourceKey<out Registry<out E>>): CraftRegistry<E>? {
                val temp = registry as CraftRegistry<CraftRegistry<E>>
                return temp.get(key as ResourceKey<CraftRegistry<E>>)
            }

            override fun registries(): Stream<RegistryEntry<*>> = registry.entries().stream().map {
                RegistryEntry.fromMapEntry(
                    it
                )
            }

            override fun freeze(): Frozen = this
        }
    }
}

private typealias RegistryKey<T> = ResourceKey<out Registry<T>>
