package net.zero.core.user

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import java.util.UUID

/**
 * A user. This is a player that can be offline, and mostly exists for that
 * purpose.
 */
@SidedApi(Side.BOTH)
interface User : BaseUser {

    /**
     * The name of this user.
     */
    val name: String

    /**
     * The UUID of this user.
     */
    val uuid: UUID

}
