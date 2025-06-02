package net.zero.core.auth

import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.annotation.TypeFactory
import net.zero.core.Zero
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract

/**
 * A property of a game profile.
 */
@ImmutableType
@SidedApi(Side.BOTH)
interface ProfileProperty {

    /**
     * The name of the property.
     */
    val name: String

    /**
     * The value of the property.
     */
    val value: String

    /**
     * The signature for this property.
     *
     * May be null if this property isn't signed.
     */
    val signature: String?

    /**
     * Creates a new profile property with the given [signature].
     *
     * @param signature The new signature.
     *
     * @return The new profile property.
     */
    @Contract("_ -> new", pure = true)
    fun withSignature(signature: String?): ProfileProperty

    /**
     * Creates a new profile property without a signature.
     *
     * @return The new profile property.
     */
    @Contract("_ -> new", pure = true)
    fun withoutSignature(): ProfileProperty

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun of(name: String, value: String, signature: String?): ProfileProperty
    }

    companion object {

        /**
         * Creates a new profile property with the given [name], [value], and
         * [signature].
         *
         * @param name The name.
         * @param value The value.
         * @param signature The signature, or null for no signature.
         *
         * @return A new profile property.
         */
        @JvmStatic
        @Contract("_, _, _ -> new", pure = true)
        fun of(name: String, value: String, signature: String?): ProfileProperty = Zero.factory<Factory>().of(name, value, signature)

        /**
         * Creates a new profile property with the given [name] and [value].
         *
         * @param name The name.
         * @param value The value.
         *
         * @return A new profile property.
         */
        @JvmStatic
        @Contract("_, _ -> new", pure = true)
        fun of(name: String, value: String): ProfileProperty = of(name, value, null)
    }
}
