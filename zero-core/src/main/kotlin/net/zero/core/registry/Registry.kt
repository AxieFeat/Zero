package net.zero.core.registry

import net.kyori.adventure.key.Key
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.core.resource.ResourceKey
import net.zero.core.tags.TagKey
import net.zero.core.tags.TagSet
import java.util.stream.Stream

/**
 * A holder for registry entries.
 *
 * @param T The registry entry type.
 */
@SidedApi(Side.BOTH)
interface Registry<T> : Iterable<T> {

    /**
     * The registry key for this registry.
     */
    val key: ResourceKey<out Registry<T>>

    /**
     * Gets the set of keys registered in this registry.
     *
     * The returned set is unmodifiable, meaning it will be updated by changes
     * in the registry, but cannot be modified directly.
     *
     * @return The registered key set.
     */
    fun keys(): Set<Key>

    /**
     * Gets the set of registry keys registered in this registry.
     *
     * The returned set is unmodifiable, meaning it will be updated by changes
     * in the registry, but cannot be modified directly.
     *
     * @return The registered registry key set.
     */
    fun registryKeys(): Set<ResourceKey<T>>

    /**
     * Gets the set of entries registered in this registry.
     *
     * The returned set is unmodifiable, meaning it will be updated by changes
     * in the registry, but cannot be modified directly.
     *
     * @return The registered entry set.
     */
    fun entries(): Set<Map.Entry<ResourceKey<T>, T>>

    /**
     * Gets the size of this registry, which is the number of registered
     * elements.
     *
     * @return The size of this registry.
     */
    fun size(): Int

    /**
     * Checks if the given [key] has a registered value in this registry.
     *
     * @param key The key.
     *
     * @return `true` if the key has a registered value, `false` otherwise.
     */
    fun containsKey(key: Key): Boolean

    /**
     * Checks if the given [key] has a registered value in this registry.
     *
     * @param key The resource key.
     *
     * @return `true` if the key has a registered value, `false` otherwise.
     */
    fun containsKey(key: ResourceKey<T>): Boolean

    /**
     * Gets a value by its namespaced [key], or null if there is no value
     * associated with the given [key].
     *
     * @param key The key.
     *
     * @return The value, or null if not present.
     */
    fun get(key: Key): T?

    /**
     * Gets a value by its resource [key], or null if there is no value
     * associated with the given [key].
     *
     * @param key The resource key.
     *
     * @return The value, or null if not present.
     */
    fun get(key: ResourceKey<T>): T?

    /**
     * Gets a namespaced [Key] by its [value], or null if there is no key
     * associated with the given [value].
     *
     * @param value The value.
     *
     * @return The key, or null if not present.
     */
    fun getKey(value: T): Key?

    /**
     * Gets the [ResourceKey] for the given [value], or null if there is no key
     * associated with the given [value].
     *
     * @param value Yhe value.
     *
     * @return Yhe resource key, or null if not present.
     */
    fun getResourceKey(value: T): ResourceKey<T>?

    /**
     * Gets the set of keys for all the registered tags in this registry.
     *
     * The returned set is unmodifiable, meaning it will be updated by changes
     * in the registry, but cannot be modified directly.
     *
     * @return The registered tag key set.
     */
    fun tagKeys(): Set<TagKey<T>>

    /**
     * Gets the set of keys for all the registered tags in this registry.
     *
     * The returned set is unmodifiable, meaning it will be updated by changes
     * in the registry, but cannot be modified directly.
     *
     * @return The registered tag key set
     */
    fun tags(): Collection<TagSet<T>>

    /**
     * Gets the tag values for the given [key], or returns null if there are no
     * tag values associated with the given [key].
     *
     * @param key The tag key.
     *
     * @return The tag set encapsulating the values for the tag.
     */
    fun getTagValues(key: TagKey<T>): TagSet<T>?

    /**
     * Creates a new stream of the elements in this registry.
     *
     * @return A new stream of this registry.
     */
    fun stream(): Stream<T>
}
