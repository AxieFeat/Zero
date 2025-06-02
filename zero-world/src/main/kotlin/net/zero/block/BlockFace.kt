package net.zero.block

import net.zero.util.Direction

/**
 * This interface represents [Direction] of [Block].
 *
 * @param direction Direction of face.
 */
enum class BlockFace(val direction: Direction) {

    BOTTOM(Direction.DOWN),
    TOP(Direction.UP),
    NORTH(Direction.NORTH),
    SOUTH(Direction.SOUTH),
    WEST(Direction.WEST),
    EAST(Direction.EAST);

    /**
     * Opposite facing for current.
     */
    val oppositeFace: BlockFace
        get() {
            return when (this) {
                BOTTOM -> TOP
                TOP -> BOTTOM
                NORTH -> SOUTH
                SOUTH -> NORTH
                WEST -> EAST
                EAST -> WEST
            }
        }

    /**
     * Is faces are similar.
     *
     * @return Is current face and [other] similar.
     */
    fun isSimilar(other: BlockFace): Boolean {
        return this == other || this == other.oppositeFace
    }

    /**
     * Gets the horizontal BlockFace from the given yaw angle
     *
     * @param yaw The yaw angle.
     *
     * @return A horizontal BlockFace.
     */
    fun fromYaw(yaw: Float): BlockFace {
        var degrees = (yaw - 90) % 360
        if (degrees < 0) {
            degrees += 360f
        }
        return if (0 <= degrees && degrees < 45) {
            WEST
        } else if (45 <= degrees && degrees < 135) {
            NORTH
        } else if (135 <= degrees && degrees < 225) {
            EAST
        } else if (225 <= degrees && degrees < 315) {
            SOUTH
        } else { // 315 <= degrees && degrees < 360
            WEST
        }
    }
}