package net.zero.common.registry.loader

import net.kyori.adventure.key.Key
import net.zero.common.registry.CraftRegistries

/**
 * A loader that is used to store values that will be registered to a registry.
 *
 * This is used to decouple the registration of values from the registry loading itself, which declutters the registry constants
 * class, and also, this replaces the need to create an implementation object to register all of the implementation values.
 */
class RegistryLoader<T> {

    private val mappings = HashMap<Key, T>()

    /**
     * This method exists to avoid having to store a separate Key object for each value to reuse it between the key in the
     * mappings and the key that may be required for the registry object, which would be a mess.
     */
    inline fun add(key: Key, function: (Key) -> T) {
        put(key, function(key))
    }

    /**
     * This method purely exists to avoid having to make mappings internal, as `add` is inline and needs something
     * public to call. The only place this method is called is in `add`.
     */
    fun put(key: Key, value: T) {
        mappings[key] = value
    }

    /**
     * Performs the action on every value present in the mappings for this loader.
     *
     * This method is used by the bootstrap loaders created by [CraftRegistries] in order to register all the
     * registry values to the target registry from this loader, and avoids the need to directly access the mappings,
     * which encapsulates the internals of the loader.
     */
    internal inline fun forEach(action: (Key, T) -> Unit) {
        mappings.forEach { action(it.key, it.value) }
    }
}
