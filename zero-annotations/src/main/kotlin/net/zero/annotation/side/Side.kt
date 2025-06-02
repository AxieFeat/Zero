package net.zero.annotation.side

/**
 * This annotation represents all the different sides of the API
 */
enum class Side {

    /**
     * It API available only on Client.
     */
    CLIENT,

    /**
     * It API available only on Server.
     */
    SERVER,

    /**
     * It API available on Both sides - Client and Server.
     */
    BOTH

}