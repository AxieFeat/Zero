package net.zero.common.resource

import net.kyori.adventure.key.Key
import net.zero.core.resource.ResourceKey

class CraftResourceKey<T>(
    override val registry: Key,
    override val location: Key
) : ResourceKey<T> {

    override fun toString(): String = "ResourceKey[$registry / $location]"

    object Factory : ResourceKey.Factory {

        override fun <T> of(registry: Key, location: Key): ResourceKey<T> = CraftResourceKey(registry, location)
    }

}