package net.zero.common.registry

import net.zero.core.resource.ResourceKey
import net.zero.common.registry.holder.Holder
import net.zero.common.registry.holder.HolderGetter

/**
 * A registry that may be written to. This subclass exists to limit the scope of registry writing, as registries must be downcasted
 * to this type in order to access the register methods in this class.
 */
interface WritableRegistry<T> : CraftRegistry<T> {

    fun register(id: Int, key: ResourceKey<T>, value: T): Holder.Reference<T>

    fun register(key: ResourceKey<T>, value: T): Holder.Reference<T>

    fun isEmpty(): Boolean

    fun createRegistrationLookup(): HolderGetter<T>
}
