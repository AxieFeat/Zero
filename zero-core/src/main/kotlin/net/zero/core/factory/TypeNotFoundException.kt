package net.zero.core.factory

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi

/**
 * Thrown when a factory requested from the factory provider was not found.
 */
@SidedApi(Side.BOTH)
class TypeNotFoundException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)