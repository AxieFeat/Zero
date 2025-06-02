package net.zero.core.resource

import net.kyori.adventure.key.Key
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.core.registry.Registry
import net.zero.core.registry.RegistryRoots
import org.jetbrains.annotations.Contract

/**
 * Creates a new registry key with the given [key] as its base key.
 *
 * This will use [RegistryRoots.ZERO] as its root.
 *
 * @param T The resource key type.
 * @param key The key.
 *
 * @return A new registry key.
 */
@JvmSynthetic
@Contract("_ -> new", pure = true)
@SidedApi(Side.BOTH)
fun <T> zero(key: String): ResourceKey<out Registry<T>> = ResourceKey.of(RegistryRoots.ZERO, Key.key(key))