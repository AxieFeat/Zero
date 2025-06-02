package net.zero.common.registry

import net.kyori.adventure.key.Key
import net.zero.core.registry.Registry
import net.zero.core.resource.ResourceKey
import net.zero.core.tags.TagKey
import net.zero.core.tags.TagSet
import net.zero.common.registry.holder.Holder
import net.zero.common.registry.holder.HolderLookup
import net.zero.common.registry.holder.HolderOwner
import net.zero.common.registry.holder.HolderSet
import net.zero.common.tags.CraftTagSet
import net.zero.common.util.ImmutableLists
import net.zero.common.util.IntBiMap
import java.util.stream.Stream
import java.util.stream.StreamSupport

/**
 * The base registry class that specifies the public API of the backend registries.
 */
interface CraftRegistry<T> : Registry<T>, IntBiMap<T> {

    override fun containsKey(key: Key): Boolean

    override fun containsKey(key: ResourceKey<T>): Boolean

    override fun getKey(value: T): Key?

    override fun getResourceKey(value: T): ResourceKey<T>?

    override fun getId(value: T): Int

    override fun get(key: Key): T?

    override fun get(key: ResourceKey<T>): T?

    fun getOrThrow(key: ResourceKey<T>): T = checkNotNull(get(key)) { "Could not find key $key in registry ${this.key}!" }

    fun holders(): Collection<Holder.Reference<T>>

    fun getHolder(id: Int): Holder.Reference<T>?

    fun getHolder(key: ResourceKey<T>): Holder.Reference<T>?

    fun getHolderOrThrow(key: ResourceKey<T>): Holder.Reference<T> =
        checkNotNull(getHolder(key)) { "Could not find key $key in registry ${this.key}!" }

    fun wrapAsHolder(value: T & Any): Holder<T>

    fun createIntrusiveHolder(value: T): Holder.Reference<T>

    fun getTag(key: TagKey<T>): HolderSet.Named<T>?

    fun getTagOrEmpty(key: TagKey<T>): Iterable<Holder<T>> = getTag(key) ?: ImmutableLists.of()

    override fun getTagValues(key: TagKey<T>): TagSet<T>? = getTag(key)?.let { CraftTagSet(this, it) }

    fun getOrCreateTag(key: TagKey<T>): HolderSet.Named<T>

    fun isKnownTagKey(key: TagKey<T>): Boolean

    fun resetTags()

    fun bindTags(tags: Map<TagKey<T>, List<Holder<T>>>)

    fun tagEntries(): Map<TagKey<T>, HolderSet.Named<T>>

    fun tagNames(): Stream<TagKey<T>>

    fun freeze(): CraftRegistry<T>

    fun holderOwner(): HolderOwner<T>

    fun asLookup(): HolderLookup.ForRegistry<T>

    fun asTagAddingLookup(): HolderLookup.ForRegistry<T> = object : HolderLookup.ForRegistry.Forwarding<T>() {
        override fun delegate(): HolderLookup.ForRegistry<T> = asLookup()

        override fun get(key: TagKey<T>): HolderSet.Named<T> = getOrThrow(key)

        override fun getOrThrow(key: TagKey<T>): HolderSet.Named<T> = getOrCreateTag(key)
    }

    fun asHolderIdMap(): IntBiMap<Holder<T>> = object : IntBiMap<Holder<T>> {

        override fun size(): Int = this@CraftRegistry.size()

        override fun get(id: Int): Holder<T>? = getHolder(id)

        override fun getId(value: Holder<T>): Int = this@CraftRegistry.getId(value.value())

        override fun iterator(): Iterator<Holder<T>> = holders().iterator()
    }

    override fun stream(): Stream<T> = StreamSupport.stream(spliterator(), false)
}
