@file:JvmSynthetic
package net.zero.core.state

import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.annotation.TypeFactory
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Unmodifiable

/**
 * Represents a property key.
 */
@ImmutableType
@SidedApi(Side.BOTH)
interface Property<T : Comparable<T>> {

    /**
     * The name of the property key.
     */
    val name: String

    /**
     * The type of this property key.
     */
    val type: Class<T>

    /**
     * The set of values this property key allows.
     */
    val values: @Unmodifiable Collection<T>

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        @SidedApi(Side.SERVER)
        fun forBoolean(name: String): Property<Boolean>

        @SidedApi(Side.SERVER)
        fun forInt(name: String): Property<Int>

        @SidedApi(Side.SERVER)
        fun <E : Enum<E>> forEnum(name: String): Property<E>
    }
}
