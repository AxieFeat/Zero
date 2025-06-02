package net.zero.core.registry

import net.kyori.adventure.key.Key
import org.jetbrains.annotations.ApiStatus
import net.kyori.adventure.key.Keyed
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.core.Zero
import net.zero.core.resource.ResourceKey
import java.util.function.Supplier

/**
 * A reference to a value in a registry.
 *
 * This allows underlying registry values to be modified dynamically without
 * needing to update all the old values.
 */
@SidedApi(Side.BOTH)
interface RegistryReference<T> : Supplier<T>, Keyed {

    /**
     * The key the value is mapped to.
     */
    val key: ResourceKey<T>

    /**
     * Gets the value this reference points to in the registry.
     *
     * @return The referenced value.
     */
    override fun get(): T

    override fun key(): Key = key.location

    @ApiStatus.Internal
    @SidedApi(Side.BOTH)
    interface Factory {

        fun <T, V : T> of(registry: Registry<T>, key: Key): RegistryReference<V>
    }

    companion object {

        /*
         * Notes for those using this within the API:
         *
         * It is up to the caller to ensure that the registry contains values
         * of type V, and also, that the value mapped to the key is of type V.
         *
         * This will NOT be verified by platforms, and WILL result in a
         * ClassCastException when `get` is called on the returned reference.
         */
        @JvmSynthetic
        @SidedApi(Side.BOTH)
        internal fun <T, V : T> of(registry: Registry<T>, key: Key): RegistryReference<V> = Zero.factory<Factory>().of(registry, key)
    }
}
