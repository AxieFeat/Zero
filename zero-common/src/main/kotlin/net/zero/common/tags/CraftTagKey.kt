package net.zero.common.tags

import net.kyori.adventure.key.Key
import net.zero.core.registry.Registry
import net.zero.core.resource.ResourceKey
import net.zero.core.tags.TagKey

class CraftTagKey<T>(
    override val registry: ResourceKey<out Registry<T>>,
    override val location: Key
) : TagKey<T> {

    object Factory : TagKey.Factory {

        override fun <T> of(registry: ResourceKey<out Registry<T>>, location: Key): TagKey<T> = CraftTagKey(registry, location)
    }

}