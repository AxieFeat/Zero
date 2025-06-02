package net.zero.core.user

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.core.auth.GameProfile
import java.time.Instant

/**
 * Common data associated with both users and players.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
@SidedApi(Side.BOTH)
interface BaseUser {

    /**
     * The cached game profile associated with this user.
     */
    val profile: GameProfile

    /**
     * If this user has joined this server before.
     */
    @get:JvmName("hasJoinedBefore")
    val hasJoinedBefore: Boolean

    /**
     * The time that this user first joined the server.
     */
    val firstJoined: Instant

    /**
     * The latest time when this user last joined the server.
     */
    val lastJoined: Instant

    /**
     * If this user is online or not.
     *
     * @return `true` if this user is online.
     */
    fun isOnline(): Boolean
}
