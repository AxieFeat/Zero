package net.zero.core.registry

import net.kyori.adventure.key.Key
import net.kyori.adventure.key.Keyed
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.core.Zero
import org.jetbrains.annotations.ApiStatus
import net.zero.core.resource.ResourceKey

/**
 * A dynamic reference to a value in a registry.
 *
 * This differs from [RegistryReference] in that the value may be in any
 * registry, or none at all, and retrieving the value requires the holder
 * to look in.
 */
@SidedApi(Side.BOTH)
interface DynamicRegistryReference<T> : Keyed {

    /**
     * The key the value is mapped to.
     */
    val key: ResourceKey<T>

    /**
     * Gets the value this reference points to in the registry held by the
     * given [holder].
     *
     * @param holder The holder containing the registry.
     * @return The referenced value.
     *
     * @throws IllegalArgumentException if the value could not be found in the holder.
     */
    fun get(holder: RegistryHolder): T

    override fun key(): Key = key.location

    @ApiStatus.Internal
    @SidedApi(Side.BOTH)
    interface Factory {

        fun <T> of(registry: ResourceKey<out Registry<T>>, key: Key): DynamicRegistryReference<T>
    }

    companion object {

        @JvmSynthetic
        @SidedApi(Side.BOTH)
        fun <T> of(registry: ResourceKey<out Registry<T>>, key: Key): DynamicRegistryReference<T> {
            return Zero.factory<Factory>().of(registry, key)
        }
    }
}
