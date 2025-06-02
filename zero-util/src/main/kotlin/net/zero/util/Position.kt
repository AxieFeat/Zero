package net.zero.util

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * A position with coordinates and rotation.
 *
 * This is primarily used to represent the position of an entity in a world.
 *
 * @property x The X component.
 * @property y The Y component.
 * @property z The Z component.
 * @property yaw The rotation on the Y axis.
 * @property pitch The rotation on the X axis.
 */
@JvmRecord
@SidedApi(Side.BOTH)
data class Position(
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Float = 0f,
    val pitch: Float = 0f
) {

    /**
     * Gets the block X coordinate of this position.
     *
     * @return The block X.
     */
    fun blockX(): Int = floor(x).toInt()

    /**
     * Gets the block Y coordinate of this position.
     *
     * @return The block Y.
     */
    fun blockY(): Int = floor(y).toInt()

    /**
     * Gets the block Z coordinate of this position.
     *
     * @return The block Z.
     */
    fun blockZ(): Int = floor(z).toInt()

    /**
     * Gets the chunk X coordinate of this position.
     *
     * @return The chunk X.
     */
    fun chunkX(): Int = blockX() shr 4

    /**
     * Gets the chunk Z coordinate of this position.
     *
     * @return The chunk Z.
     */
    fun chunkZ(): Int = blockZ() shr 4

    /**
     * Creates a new position with the given [x] component.
     *
     * @param x The new X component.
     *
     * @return The new position.
     */
    fun withX(x: Double): Position = Position(x, this.y, this.z, this.yaw, this.pitch)

    /**
     * Creates a new position with the given [y] component.
     *
     * @param y The new Y component.
     *
     * @return The new position.
     */
    fun withY(y: Double): Position = Position(this.x, y, this.z, this.yaw, this.pitch)

    /**
     * Creates a new position with the given [z] component.
     *
     * @param z The new Z component.
     *
     * @return The new position.
     */
    fun withZ(z: Double): Position = Position(this.x, this.y, z, this.yaw, this.pitch)

    /**
     * Creates a new position with the given [x], [y], and [z] coordinates and
     * the rotation of this position.
     *
     * @param x the new X coordinate.
     * @param y the new Y coordinate.
     * @param z the new Z coordinate.
     *
     * @return The new position.
     */
    fun withCoordinates(x: Double, y: Double, z: Double): Position = Position(x, y, z, this.yaw, this.pitch)

    /**
     * Creates a new position with the given [yaw].
     *
     * @param yaw The new yaw.
     *
     * @return The new position.
     */
    fun withYaw(yaw: Float): Position = Position(this.x, this.y, this.z, yaw, this.pitch)

    /**
     * Creates a new position with the given [pitch].
     *
     * @param pitch The new pitch.
     *
     * @return The new position.
     */
    fun withPitch(pitch: Float): Position = Position(this.x, this.y, this.z, this.yaw, pitch)

    /**
     * Creates a new position with the given [yaw] and [pitch] and the
     * coordinates of this position.
     *
     * @param yaw The new yaw.
     * @param pitch The new pitch.
     *
     * @return The new position.
     */
    fun withRotation(yaw: Float, pitch: Float): Position = Position(this.x, this.y, this.z, yaw, pitch)

    /**
     * Creates a new position with the rotation such that they are looking at
     * the given [target] vector.
     *
     * @param target The target vector to look at.
     *
     * @return The resulting position.
     */
    fun withLookAt(target: Vec3d): Position {
        if (x.compareTo(target.x) == 0 && y.compareTo(target.y) == 0 && z.compareTo(target.z) == 0) return this
        val delta = Vec3d(target.x - x, target.y - y, target.z - z).normalize()
        return withRotation(calculateLookYaw(delta.x, delta.z), calculateLookPitch(delta.x, delta.y, delta.z))
    }

    /**
     * Gets a unit vector pointing in the direction that this position is
     * facing.
     *
     * @return The direction vector.
     */
    fun direction(): Vec3d {
        val rotY = yaw.toDouble()
        val rotX = pitch.toDouble()
        val xz = cos(Math.toRadians(rotX))
        return Vec3d(-xz * sin(Math.toRadians(rotY)), -sin(Math.toRadians(rotX)), xz * cos(Math.toRadians(rotY)))
    }

    /**
     * Adds the given [x], [y], and [z] values to this position and returns
     * the result.
     *
     * @param x The X amount to add.
     * @param y The Y amount to add.
     * @param z The Z amount to add.
     *
     * @return The resulting position.
     */
    fun add(x: Double, y: Double, z: Double): Position = Position(this.x + x, this.y + y, this.z + z, this.yaw, this.pitch)

    /**
     * Adds the given [amount] to this position and returns the result.
     *
     * @param amount The amount to add.
     *
     * @return The resulting position.
     */
    fun add(amount: Double): Position = add(amount, amount, amount)

    /**
     * Adds the given [other] position to this position and returns the result.
     *
     * Only the coordinates of the other position will be added to this
     * position, **not** the rotation.
     *
     * @param other The position to add.
     *
     * @return The resulting position.
     */
    fun add(other: Position): Position = add(other.x, other.y, other.z)

    /**
     * Adds the given [other] vector to this position and returns the result.
     *
     * @param other The vector to add.
     *
     * @return The resulting position.
     */
    fun add(other: Vec3d): Position = add(other.x, other.y, other.z)

    /**
     * Adds the given [other] vector to this position and returns the result.
     *
     * @param other The vector to add.
     *
     * @return The resulting position.
     */
    fun add(other: Vec3i): Position = add(other.x.toDouble(), other.y.toDouble(), other.z.toDouble())

    /**
     * Subtracts the given [x], [y], and [z] values from this vector and
     * returns the result.
     *
     * @param x The X amount to subtract.
     * @param y The Y amount to subtract.
     * @param z The Z amount to subtract.
     *
     * @return The resulting position.
     */
    fun subtract(x: Double, y: Double, z: Double): Position = Position(this.x - x, this.y - y, this.z - z)

    /**
     * Subtracts the given [amount] from this position and returns the result.
     *
     * @param amount tThe amount to subtract.
     *
     * @return The resulting position.
     */
    fun subtract(amount: Double): Position = subtract(amount, amount, amount)

    /**
     * Subtracts the given [other] position from this position and returns the
     * result.
     *
     * Only the coordinates of the other position will be subtracted from this
     * position, **not** the rotation.
     *
     * @param other The position to subtract.
     *
     * @return The resulting position.
     */
    fun subtract(other: Position): Position = subtract(other.x, other.y, other.z)

    /**
     * Subtracts the given [other] vector from this position and returns the
     * result.
     *
     * @param other The vector to subtract.
     *
     * @return The resulting position.
     */
    fun subtract(other: Vec3d): Position = subtract(other.x, other.y, other.z)

    /**
     * Subtracts the given [other] vector from this position and returns the
     * result.
     *
     * @param other The vector to subtract.
     *
     * @return The resulting position.
     */
    fun subtract(other: Vec3i): Position = subtract(other.x.toDouble(), other.y.toDouble(), other.z.toDouble())

    /**
     * Multiplies this position by the given [x], [y], and [z] values and
     * returns the result.
     *
     * @param x The X amount.
     * @param y The Y amount.
     * @param z The Z amount.
     *
     * @return The resulting position.
     */
    fun multiply(x: Double, y: Double, z: Double): Position = Position(this.x * x, this.y * y, this.z * z, this.yaw, this.pitch)

    /**
     * Multiplies this position by the given [other] position and returns the
     * result.
     *
     * Only the coordinates of the other position will be multiplied by this
     * position, **not** the rotation.
     *
     * @param other The position to multiply by.
     *
     * @return The resulting position.
     */
    fun multiply(other: Position): Position = multiply(other.x, other.y, other.z)

    /**
     * Multiplies this position by the given [other] vector and returns the
     * result.
     *
     * @param other The vector to multiply by.
     *
     * @return The resulting position.
     */
    fun multiply(other: Vec3d): Position = multiply(other.x, other.y, other.z)

    /**
     * Multiplies this position by the given [other] vector and returns the
     * result.
     *
     * @param other The vector to multiply by.
     *
     * @return The resulting position.
     */
    fun multiply(other: Vec3i): Position = multiply(other.x.toDouble(), other.y.toDouble(), other.z.toDouble())

    /**
     * Multiplies this position by the given [factor] and returns the result.
     *
     * This is equivalent to calling [multiply] with the same factor for each
     * component.
     *
     * @param factor The factor to multiply by.
     *
     * @return The resulting position.
     */
    fun multiply(factor: Double): Position = multiply(factor, factor, factor)

    /**
     * Divides this position by the given [x], [y], and [z] values and returns
     * the result.
     *
     * @param x The X amount.
     * @param y The Y amount.
     * @param z The Z amount.
     *
     * @return The resulting vector.
     */
    fun divide(x: Double, y: Double, z: Double): Position = Position(this.x / x, this.y / y, this.z / z, this.yaw, this.pitch)

    /**
     * Divides this position by the given [other] position and returns the
     * result.
     *
     * Only the coordinates of the other position will be multiplied by this
     * position, **not** the rotation.
     *
     * @param other The position to divide by.
     *
     * @return The resulting position.
     */
    fun divide(other: Position): Position = divide(other.x, other.y, other.z)

    /**
     * Divides this position by the given [other] vector and returns the result.
     *
     * @param other The vector to divide by.
     *
     * @return The resulting position.
     */
    fun divide(other: Vec3d): Position = divide(other.x, other.y, other.z)

    /**
     * Divides this position by the given [other] vector and returns the result.
     *
     * @param other The vector to divide by.
     *
     * @return The resulting position.
     */
    fun divide(other: Vec3i): Position = divide(other.x.toDouble(), other.y.toDouble(), other.z.toDouble())

    /**
     * Divides this position by the given [factor] and returns the result.
     *
     * This is equivalent to calling [divide] with the same factor for each
     * component.
     *
     * @param factor The factor to divide by.
     *
     * @return The resulting position.
     */
    fun divide(factor: Double): Position = divide(factor, factor, factor)

    /**
     * Calculates the squared distance between this position and the
     * given [x], [y], and [z] values.
     *
     * @param x The X distance.
     * @param y The Y distance.
     * @param z The Z distance.
     * @return The squared distance.
     */
    fun distanceSquared(x: Double, y: Double, z: Double): Double {
        val dx = this.x - x
        val dy = this.y - y
        val dz = this.z - z
        return dx * dx + dy * dy + dz * dz
    }

    /**
     * Calculates the squared distance between this position and the
     * given [other] position.
     *
     * @param other The other position.
     * @return The squared distance.
     */
    fun distanceSquared(other: Position): Double = distanceSquared(other.x, other.y, other.z)

    /**
     * Calculates the squared distance between this position and the
     * given [other] vector.
     *
     * @param other The other vector.
     *
     * @return The squared distance.
     */
    fun distanceSquared(other: Vec3d): Double = distanceSquared(other.x, other.y, other.z)

    /**
     * Calculates the squared distance between this position and the
     * given [other] vector.
     *
     * @param other The other vector.
     *
     * @return The squared distance.
     */
    fun distanceSquared(other: Vec3i): Double = distanceSquared(other.x.toDouble(), other.y.toDouble(), other.z.toDouble())

    /**
     * Calculates the distance between this position and the given [x], [y],
     * and [z] values.
     *
     * @param x The X distance.
     * @param y The Y distance.
     * @param z The Z distance.
     *
     * @return The distance.
     */
    fun distance(x: Double, y: Double, z: Double): Double = sqrt(distanceSquared(x, y, z))

    /**
     * Calculates the distance between this position and the given [other]
     * position.
     *
     * @param other The other position.
     *
     * @return The distance.
     */
    fun distance(other: Position): Double = distance(other.x, other.y, other.z)

    /**
     * Calculates the distance between this position and the given [other]
     * vector.
     *
     * @param other The other vector.
     *
     * @return The distance.
     */
    fun distance(other: Vec3d): Double = distance(other.x, other.y, other.z)

    /**
     * Calculates the distance between this position and the given [other]
     * vector.
     *
     * @param other The other vector.
     *
     * @return The distance.
     */
    fun distance(other: Vec3i): Double = distance(other.x.toDouble(), other.y.toDouble(), other.z.toDouble())

    /**
     * Converts this position to an equivalent double vector.
     *
     * @return The converted vector.
     */
    fun asVec3d(): Vec3d = Vec3d(x, y, z)

    /**
     * Converts this position to an equivalent integer vector.
     *
     * @return The converted vector.
     */
    fun asVec3i(): Vec3i = Vec3i(blockX(), blockY(), blockZ())

    override fun toString(): String = "($x, $y, $z, $yaw, $pitch)"

    companion object {

        /**
         * The zero position.
         */
        @JvmField
        val ZERO: Position = Position(0.0, 0.0, 0.0, 0F, 0F)

        @JvmStatic
        private fun calculateLookYaw(dx: Double, dz: Double): Float {
            val radians = atan2(dz, dx)
            val degrees = Math.toDegrees(radians).toFloat() - 90
            if (degrees < -180) return degrees + 360
            if (degrees > 180) return degrees - 360
            return degrees
        }

        @JvmStatic
        private fun calculateLookPitch(dx: Double, dy: Double, dz: Double): Float {
            val radians = -atan2(dy, max(abs(dx), abs(dz)))
            return Math.toDegrees(radians).toFloat()
        }
    }
}
