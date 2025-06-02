package net.zero.core.resource

import net.kyori.adventure.key.Key
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.core.Zero
import net.zero.core.registry.Registry
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract

/**
 * A key pointing to some form of resource.
 *
 * @param T the type of this key.
 */
@SidedApi(Side.BOTH)
interface ResourceKey<T> {

    /**
     * The key of the parent registry.
     */
    val registry: Key

    /**
     * The location of the registry.
     */
    val location: Key

    @ApiStatus.Internal
    @SidedApi(Side.BOTH)
    interface Factory {

        fun <T> of(registry: Key, location: Key): ResourceKey<T>
    }

    companion object {

        /**
         * Creates a new resource key, or returns an existing one if one with
         * the given parameters has already been created, with the given
         * [registry] as its parent name, and the given [location] as the
         * location of the resource.
         *
         * @param T The resource key type.
         * @param registry The parent registry name.
         * @param location The location of the resource.
         *
         * @return A resource key.
         */
        @JvmStatic
        @Contract("_, _ -> new", pure = true)
        @SidedApi(Side.BOTH)
        fun <T> of(registry: Key, location: Key): ResourceKey<T> = Zero.factory<Factory>().of(registry, location)

        /**
         * Creates a new resource key, or returns an existing one if one with
         * the given parameters has already been created, with the given
         * [parent] as its parent, and the given [location] as the location of
         * the resource.
         *
         * @param T The resource key type.
         * @param parent The parent key.
         * @param location The location of the resource.
         *
         * @return A resource key.
         */
        @JvmStatic
        @Contract("_, _ -> new", pure = true)
        @SidedApi(Side.BOTH)
        fun <T> of(parent: ResourceKey<out Registry<T>>, location: Key): ResourceKey<T> = of(parent.location, location)
    }
}
