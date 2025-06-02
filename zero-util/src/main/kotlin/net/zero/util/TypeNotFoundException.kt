package net.zero.util

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import java.io.Serial

/**
 * Thrown when a factory requested from the factory provider was not found.
 */
@SidedApi(Side.BOTH)
class TypeNotFoundException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause) {

    companion object {

        @Serial
        private const val serialVersionUID = 7032005516854864359L
    }
}
