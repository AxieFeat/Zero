package net.zero.common.tags

import com.google.common.collect.Iterators
import net.zero.common.registry.CraftRegistry
import net.zero.common.registry.holder.HolderSet
import net.zero.core.tags.TagKey
import net.zero.core.tags.TagSet
import java.util.stream.Stream

class CraftTagSet<T>(
    override val registry: CraftRegistry<T>,
    private val delegate: HolderSet.Named<T>
) : TagSet<T> {

    override val key: TagKey<T>
        get() = delegate.key

    override fun size(): Int = delegate.size()

    override fun contains(value: T): Boolean = delegate.contains(registry.wrapAsHolder(value!!))

    override fun get(index: Int): T = delegate.get(index).value()

    override fun iterator(): Iterator<T> = Iterators.transform(delegate.iterator()) { it.value() }

    override fun stream(): Stream<T> = delegate.stream().map { it.value() }

}