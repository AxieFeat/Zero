package net.zero.common.registry

import net.kyori.adventure.key.Key
import net.zero.core.registry.Registry
import net.zero.core.resource.ResourceKey
import net.zero.common.registry.holder.Holder

/**
 * The defaulted registry implementation that stores a default key to search for when values are registered to store
 * the default value to return if no other value can be found.
 */
class CraftDefaultedSimpleRegistry<T> private constructor(
    override val defaultKey: Key,
    key: ResourceKey<out Registry<T>>,
    intrusive: Boolean
) : CraftSimpleRegistry<T>(key, intrusive), CraftDefaultedRegistry<T> {

    private var defaultValue: Holder.Reference<T>? = null

    private fun defaultValue(): T = checkNotNull(defaultValue) { "The default value was not initialized!" }.value()

    override fun register(id: Int, key: ResourceKey<T>, value: T): Holder.Reference<T> {
        val holder = super.register(id, key, value)
        if (defaultKey == key.location) defaultValue = holder
        return holder
    }

    override fun get(key: Key): T = super.get(key) ?: defaultValue()

    override fun get(key: ResourceKey<T>): T = super.get(key) ?: defaultValue()

    override fun get(id: Int): T = super.get(id) ?: defaultValue()

    override fun getKey(value: T): Key = super.getKey(value) ?: defaultKey

    override fun getId(value: T): Int {
        val id = super.getId(value)
        return if (id == -1) super.getId(defaultValue()) else id
    }

    companion object {

        @JvmStatic
        fun <T> standard(key: ResourceKey<out Registry<T>>, defaultKey: Key): CraftDefaultedSimpleRegistry<T> =
            CraftDefaultedSimpleRegistry(defaultKey, key, false)

        @JvmStatic
        fun <T> intrusive(key: ResourceKey<out Registry<T>>, defaultKey: Key): CraftDefaultedSimpleRegistry<T> =
            CraftDefaultedSimpleRegistry(defaultKey, key, true)
    }
}
