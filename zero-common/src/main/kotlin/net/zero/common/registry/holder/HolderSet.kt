package net.zero.common.registry.holder

import net.zero.common.util.ImmutableLists
import net.zero.common.util.ImmutableSets
import net.zero.core.tags.TagKey
import java.util.Optional
import java.util.Spliterator
import java.util.stream.Stream

interface HolderSet<T> : Iterable<Holder<T>> {

    fun size(): Int

    fun contains(holder: Holder<T>): Boolean

    fun get(index: Int): Holder<T>

    fun unwrapKey(): Optional<TagKey<T>>

    fun canSerializeIn(owner: HolderOwner<T>): Boolean

    fun stream(): Stream<Holder<T>>

    abstract class ListBacked<T> : HolderSet<T> {

        protected abstract fun contents(): List<Holder<T>>

        override fun size(): Int = contents().size

        override fun get(index: Int): Holder<T> = contents().get(index)

        override fun canSerializeIn(owner: HolderOwner<T>): Boolean = true

        override fun stream(): Stream<Holder<T>> = contents().stream()

        override fun iterator(): Iterator<Holder<T>> = contents().iterator()

        override fun spliterator(): Spliterator<Holder<T>> = contents().spliterator()
    }

    class Direct<T>(private val contents: List<Holder<T>>) : ListBacked<T>() {

        private var contentsSet: Set<Holder<T>>? = null

        override fun contents(): List<Holder<T>> = contents

        override fun contains(holder: Holder<T>): Boolean {
            if (contentsSet == null) contentsSet = ImmutableSets.copyOf(contents)
            return contentsSet!!.contains(holder)
        }

        override fun unwrapKey(): Optional<TagKey<T>> = Optional.empty()

        override fun toString(): String = "DirectSet(contents=$contents)"
    }

    class Named<T>(private val owner: HolderOwner<T>, val key: TagKey<T>) : ListBacked<T>() {

        private var contents: List<Holder<T>> = ImmutableLists.of()

        fun bind(contents: List<Holder<T>>) {
            this.contents = ImmutableLists.copyOf(contents)
        }

        override fun contents(): List<Holder<T>> = contents

        override fun contains(holder: Holder<T>): Boolean = holder.eq(key)

        override fun unwrapKey(): Optional<TagKey<T>> = Optional.of(key)

        override fun canSerializeIn(owner: HolderOwner<T>): Boolean = this.owner.canSerializeIn(owner)

        override fun toString(): String = "NamedSet(key=$key, contents=$contents)"
    }

    companion object {

        @JvmStatic
        fun <T> direct(contents: List<Holder<T>>): Direct<T> = Direct(ImmutableLists.copyOf(contents))

        @JvmStatic
        fun <T> direct(vararg contents: Holder<T>): Direct<T> = Direct(ImmutableLists.ofArray(contents))
    }
}
