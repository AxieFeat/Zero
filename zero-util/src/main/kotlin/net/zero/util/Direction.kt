package net.zero.util

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi

/**
 * Represents a three-dimensional
 * [Cardinal direction](https://en.wikipedia.org/wiki/Cardinal_direction).
 *
 * @param oppositeIndex The index of the opposite direction.
 * @param axis The axis of this direction.
 * @param axisDirection The direction of the axis of this direction.
 * @param normal The normal of this direction.
 */
@SidedApi(Side.BOTH)
enum class Direction(
    private val oppositeIndex: Int,
    val axis: Axis,
    val axisDirection: AxisDirection,
    val normal: Vec3i
) {

    DOWN(1, Axis.Y, AxisDirection.NEGATIVE, Vec3i(0, -1, 0)),
    UP(0, Axis.Y, AxisDirection.POSITIVE, Vec3i(0, 1, 0)),
    NORTH(3, Axis.Z, AxisDirection.NEGATIVE, Vec3i(0, 0, -1)),
    SOUTH(2, Axis.Z, AxisDirection.POSITIVE, Vec3i(0, 0, 1)),
    WEST(5, Axis.X, AxisDirection.NEGATIVE, Vec3i(-1, 0, 0)),
    EAST(4, Axis.X, AxisDirection.POSITIVE, Vec3i(1, 0, 0));

    /**
     * The normal on the X axis.
     */
    val normalX: Int
        get() = normal.x

    /**
     * The normal on the Y axis.
     */
    val normalY: Int
        get() = normal.y

    /**
     * The normal on the Z axis.
     */
    val normalZ: Int
        get() = normal.z

    /**
     * The opposite of this direction.
     *
     * Initialized lazily to avoid a circular dependency.
     */
    val opposite: Direction
        get() = VALUES[oppositeIndex]

    /**
     * Axes that a direction may be on.
     */
    @SidedApi(Side.BOTH)
    enum class Axis(private val intSelector: IntSelector, private val doubleSelector: DoubleSelector) {

        X({ x, _, _ -> x }, { x, _, _ -> x }),
        Y({ _, y, _ -> y }, { _, y, _ -> y }),
        Z({ _, _, z -> z }, { _, _, z -> z });

        /**
         * Checks if this axis tiles vertically.
         *
         * @return `true` if this axis is vertical, `false` otherwise.
         */
        fun isVertical(): Boolean = this == Y

        /**
         * Checks if this axis tiles horizontally.
         *
         * @return `true` if this axis is horizontal, `false` otherwise.
         */
        fun isHorizontal(): Boolean = this == X || this == Z

        /**
         * Selects the appropriate [x], [y], or [z] coordinate, depending on
         * what the axis is, and returns it.
         *
         * @param x The X coordinate.
         * @param y The Y coordinate.
         * @param z he Z coordinate.
         *
         * @return The chosen coordinate.
         */
        fun select(x: Int, y: Int, z: Int): Int = intSelector.select(x, y, z)

        /**
         * Selects the appropriate [x], [y], or [z] coordinate, depending on
         * what the axis is, and returns it.
         *
         * @param x The X coordinate.
         * @param y The Y coordinate.
         * @param z The Z coordinate.
         *
         * @return The chosen coordinate.
         */
        fun select(x: Double, y: Double, z: Double): Double = doubleSelector.select(x, y, z)
    }

    /**
     * The direction of an [Axis] on a plane.
     *
     * @param step The step.
     */
    @SidedApi(Side.BOTH)
    enum class AxisDirection(val step: Int) {

        POSITIVE(1),
        NEGATIVE(-1);

        /**
         * The opposite direction.
         *
         * Initialized lazily to avoid circular dependencies.
         */
        val opposite: AxisDirection
            get() = if (this === POSITIVE) NEGATIVE else POSITIVE
    }

    private fun interface IntSelector {

        fun select(x: Int, y: Int, z: Int): Int
    }

    private fun interface DoubleSelector {

        fun select(x: Double, y: Double, z: Double): Double
    }

    companion object {
        private val VALUES = entries.toTypedArray()
    }
}
