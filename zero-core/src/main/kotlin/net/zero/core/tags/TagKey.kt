package net.zero.core.tags

import net.kyori.adventure.key.Key
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.core.Zero
import net.zero.core.registry.Registry
import net.zero.core.resource.ResourceKey
import org.jetbrains.annotations.ApiStatus

/**
 * A key for registry tags.
 */
@SidedApi(Side.BOTH)
interface TagKey<T> {

    /**
     * The key for the registry that this tag key is for.
     */
    val registry: ResourceKey<out Registry<T>>

    /**
     * The location of this tag key.
     */
    val location: Key

    @ApiStatus.Internal
    @SidedApi(Side.BOTH)
    interface Factory {

        fun <T> of(registry: ResourceKey<out Registry<T>>, location: Key): TagKey<T>
    }

    companion object {

        /**
         * Creates a new tag key for the given [registry] and [location].
         *
         * @param T The tag type.
         * @param registry The registry key.
         * @param location The location.
         *
         * @return A new tag key.
         */
        @JvmStatic
        @SidedApi(Side.BOTH)
        fun <T> of(registry: ResourceKey<out Registry<T>>, location: Key): TagKey<T> = Zero.factory<Factory>().of(registry, location)
    }
}
