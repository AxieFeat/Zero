@file:JvmSynthetic
package net.zero.core.factory

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi

/**
 * Used to provide various factories from the backend for static factory
 * functions.
 */
@SidedApi(Side.BOTH)
interface FactoryProvider {

    /**
     * Provides the factory with the given type [type], or throws a
     * [TypeNotFoundException] if there is no factory registered for the
     * given type.
     *
     * @param T The factory type.
     * @param type The class of the type.
     *
     * @return The factory.
     */
    fun <T> provide(type: Class<T>): T

    /**
     * Registers the given [factory] of the given [type] to this factory
     * provider.
     *
     * @param T The factory type.
     * @param type The class of the type.
     * @param factory The factory to register.
     *
     * @throws IllegalStateException If the factory is already registered.
     */
    fun <T> register(type: Class<T>, factory: T)
}
