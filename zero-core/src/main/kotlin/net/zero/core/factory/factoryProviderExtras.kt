@file:JvmSynthetic
package net.zero.core.factory

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi

/**
 * Provides the factory with the given type [T], or throws a
 * [TypeNotFoundException] if there is no factory registered for the given
 * type.
 *
 * @param T the factory type.
 */
@JvmSynthetic
@SidedApi(Side.BOTH)
inline fun <reified T> FactoryProvider.provide(): T = provide(T::class.java)

/**
 * Registers the given [factory] of the given type [T] to this factory
 * provider.
 *
 * @param T The factory type.
 * @param factory The factory to register.
 *
 * @throws IllegalStateException if the factory is already registered.
 */
@JvmSynthetic
@SidedApi(Side.BOTH)
inline fun <reified T> FactoryProvider.register(factory: T) {
    register(T::class.java, factory)
}
