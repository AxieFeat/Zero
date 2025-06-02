package net.zero.entity.attribute

import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.annotation.TypeFactory
import net.zero.core.Zero
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import java.util.UUID

/**
 * A modifier that can be applied to an [Attribute].
 */
@ImmutableType
@SidedApi(Side.SERVER)
interface AttributeModifier {

    /**
     * The unique ID of the modifier.
     */
    val uuid: UUID

    /**
     * The name of the modifier.
     */
    val name: String

    /**
     * The amount to modify attribute values by.
     */
    val amount: Double

    /**
     * The operation to perform on the modifier.
     */
    val operation: ModifierOperation

    @ApiStatus.Internal
    @TypeFactory
    @SidedApi(Side.SERVER)
    interface Factory {

        fun of(uuid: UUID, name: String, amount: Double, operation: ModifierOperation): AttributeModifier
    }

    companion object {

        /**
         * Creates a new attribute modifier with the given values.
         *
         * @param uuid The unique ID of the modifier.
         * @param name The name of the modifier.
         * @param amount The amount to modify attribute values by.
         * @param operation The operation to perform on the modifier.
         *
         * @return A new attribute modifier.
         */
        @JvmStatic
        @Contract("_, _, _, _ -> new", pure = true)
        @SidedApi(Side.SERVER)
        fun of(uuid: UUID, name: String, amount: Double, operation: ModifierOperation): AttributeModifier =
            Zero.factory<Factory>().of(uuid, name, amount, operation)
    }
}
