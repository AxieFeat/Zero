package net.zero.entity.attribute

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.core.registry.RegistryReference

/**
 * A holder of attributes.
 */
@SidedApi(Side.BOTH)
interface AttributeHolder {

    /**
     * Gets the attribute for the specified [type], or returns null if there
     * is no attribute for the given [type].
     *
     * @param type The type of the attribute.
     *
     * @return The attribute, or null if not present.
     */
    fun getAttribute(type: AttributeType): Attribute?

    /**
     * Gets the attribute for the specified [type], or returns null if there
     * is no attribute for the given [type].
     *
     * @param type The type of the attribute.
     *
     * @return The attribute, or null if not present.
     */
    fun getAttribute(type: RegistryReference<AttributeType>): Attribute? = getAttribute(type.get())
}
