package net.zero.block

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi

/**
 * The reaction something will have when it is pushed.
 */
@SidedApi(Side.SERVER)
enum class PushReaction {

    /**
     * The block will be moved by the piston as normal, and will not change.
     */
    NORMAL,

    /**
     * The block being pushed is not strong enough for this cruel world, and
     * will be destroyed when pushed.
     */
    DESTROY,

    /**
     * The block being pushed is too strong for a mere piston to move it, and
     * it blocks all attempts made by pistons to push it.
     */
    BLOCK,

    /**
     * The block will ignore any attempts made by the piston to interact with
     * it.
     */
    IGNORE,

    /**
     * The block can be pushed as normal by pistons, but no piston wields the
     * strength required to pull it back towards itself.
     */
    PUSH_ONLY
}
