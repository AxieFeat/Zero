package net.zero.core.registry

import net.kyori.adventure.key.Key
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi

/**
 * All the built-in registry roots.
 */
@SidedApi(Side.BOTH)
object RegistryRoots {

    /**
     * The built-in Zero root.
     *
     * The key of this root is "zero:root".
     */
    @JvmField
    val ZERO: Key = Key.key("zero", "root")
}
