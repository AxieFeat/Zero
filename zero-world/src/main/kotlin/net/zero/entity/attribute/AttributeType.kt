package net.zero.entity.attribute

import net.kyori.adventure.key.Keyed
import net.kyori.adventure.translation.Translatable
import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi

/**
 * The type of an attribute.
 */
@ImmutableType
@SidedApi(Side.BOTH)
interface AttributeType : Translatable, Keyed {

    /**
     * The default value for attributes of this type.
     */
    val defaultValue: Double

    /**
     * Ensures that the given [value] satisfies the constraints of this
     * attribute type.
     *
     * For example, with ranged attribute types, this will ensure that the
     * value is between the minimum and maximum value.
     *
     * @param value The value to sanitize.
     *
     * @return The sanitized result.
     */
    fun sanitizeValue(value: Double): Double

}
