package net.zero.entity.attribute

import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi

/**
 * A type of attribute that only accepts values between a minimum and maximum
 * value.
 */
@ImmutableType
@SidedApi(Side.BOTH)
interface RangedAttributeType : AttributeType {

    /**
     * The minimum value for attributes of this type.
     */
    val minimum: Double

    /**
     * The maximum value for attributes of this type.
     */
    val maximum: Double

}
