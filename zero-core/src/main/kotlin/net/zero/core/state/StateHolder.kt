package net.zero.core.state

import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import org.jetbrains.annotations.Unmodifiable

/**
 * Something that can be in some state.
 */
@ImmutableType
@SidedApi(Side.BOTH)
interface StateHolder<out S : State<S>> {

    /**
     * All of the states that this holder can be in.
     */
    val states: @Unmodifiable List<S>

    /**
     * All of the properties that can be set on the states of this holder.
     */
    val availableProperties: @Unmodifiable Collection<Property<*>>

    /**
     * The default state that this holder will appear in.
     */
    val defaultState: S

    /**
     * Gets the property with the given [name] for this holder, or returns null
     * if there is no property with the given [name] for this holder.
     *
     * @param name The name of the property.
     *
     * @return The property, or null if not present.
     */
    fun getProperty(name: String): Property<*>?
}
