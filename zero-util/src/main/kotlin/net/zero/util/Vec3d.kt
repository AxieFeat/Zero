package net.zero.util

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.sqrt

/**
 * A double vector with an X, Y, and Z component.
 *
 * @property x The X component of this vector.
 * @property y The Y component of this vector.
 * @property z The Z component of this vector.
 */
@JvmRecord
@Suppress("TooManyFunctions")
@SidedApi(Side.BOTH)
data class Vec3d(val x: Double, val y: Double, val z: Double) : Comparable<Vec3d> {

    /**
     * Gets the floored X component of this vector.
     *
     * @return The floored X component.
     */
    fun floorX(): Int = floor(x).toInt()

    /**
     * Gets the floored Y component of this vector.
     *
     * @return The floored Y component.
     */
    fun floorY(): Int = floor(y).toInt()

    /**
     * Gets the floored Z component of this vector.
     *
     * @return The floored Z component.
     */
    fun floorZ(): Int = floor(z).toInt()

    /**
     * Creates a new vector with the given [x] component.
     *
     * @param x The new X component.
     *
     * @return The new vector.
     */
    fun withX(x: Double): Vec3d = Vec3d(x, this.y, this.z)

    /**
     * Creates a new vector with the given [y] component.
     *
     * @param y The new Y component.
     *
     * @return The new vector.
     */
    fun withY(y: Double): Vec3d = Vec3d(this.x, y, this.z)

    /**
     * Creates a new vector with the given [z] component.
     *
     * @param z The new Z component.
     *
     * @return The new vector.
     */
    fun withZ(z: Double): Vec3d = Vec3d(this.x, this.y, z)

    /**
     * Adds the given [x], [y], and [z] values to this vector and returns the
     * result.
     *
     * @param x The X amount to add.
     * @param y The Y amount to add.
     * @param z The Z amount to add.
     *
     * @return The resulting vector.
     */
    fun add(x: Double, y: Double, z: Double): Vec3d = Vec3d(this.x + x, this.y + y, this.z + z)

    /**
     * Adds the given [amount] to this vector and returns the result.
     *
     * @param amount The amount to add.
     *
     * @return The resulting vector.
     */
    fun add(amount: Double): Vec3d = add(amount, amount, amount)

    /**
     * Adds the given [other] vector to this vector and returns the result.
     *
     * @param other The vector to add.
     *
     * @return The resulting vector.
     */
    fun add(other: Vec3d): Vec3d = add(other.x, other.y, other.z)

    /**
     * Adds the given [other] vector to this vector and returns the result.
     *
     * @param other The vector to add.
     *
     * @return The resulting vector.
     */
    fun add(other: Vec3i): Vec3d = add(other.x.toDouble(), other.y.toDouble(), other.z.toDouble())

    /**
     * Subtracts the given [x], [y], and [z] values from this vector and
     * returns the result.
     *
     * @param x The X amount to subtract.
     * @param y The Y amount to subtract.
     * @param z The Z amount to subtract.
     *
     * @return The resulting vector.
     */
    fun subtract(x: Double, y: Double, z: Double): Vec3d = Vec3d(this.x - x, this.y - y, this.z - z)

    /**
     * Subtracts the given [amount] from this vector and returns the result.
     *
     * @param amount The amount to subtract.
     *
     * @return The resulting vector.
     */
    fun subtract(amount: Double): Vec3d = subtract(amount, amount, amount)

    /**
     * Subtracts the given [other] vector from this vector and returns the
     * result.
     *
     * @param other The vector to subtract.
     *
     * @return The resulting vector.
     */
    fun subtract(other: Vec3d): Vec3d = subtract(other.x, other.y, other.z)

    /**
     * Subtracts the given [other] vector from this vector and returns the
     * result.
     *
     * @param other The vector to subtract.
     *
     * @return The resulting vector.
     */
    fun subtract(other: Vec3i): Vec3d = subtract(other.x.toDouble(), other.y.toDouble(), other.z.toDouble())

    /**
     * Multiplies this vector by the given [x], [y], and [z] values and
     * returns the result.
     *
     * @param x The X amount.
     * @param y The Y amount.
     * @param z The Z amount.
     *
     * @return The resulting vector.
     */
    fun multiply(x: Double, y: Double, z: Double): Vec3d = Vec3d(this.x * x, this.y * y, this.z * z)

    /**
     * Multiplies this vector by the given [other] vector and returns the
     * result.
     *
     * @param other The vector to multiply by.
     *
     * @return The resulting vector.
     */
    fun multiply(other: Vec3d): Vec3d = multiply(other.x, other.y, other.z)

    /**
     * Multiplies this vector by the given [other] vector and returns the
     * result.
     *
     * @param other The vector to multiply by.
     *
     * @return The resulting vector.
     */
    fun multiply(other: Vec3i): Vec3d = multiply(other.x.toDouble(), other.y.toDouble(), other.z.toDouble())

    /**
     * Multiplies this vector by the given [factor] and returns the result.
     *
     * This is equivalent to calling [multiply] with the same factor for each
     * component.
     *
     * @param factor The factor to multiply by.
     *
     * @return The resulting vector.
     */
    fun multiply(factor: Double): Vec3d = multiply(factor, factor, factor)

    /**
     * Divides this vector by the given [x], [y], and [z] values and returns
     * the result.
     *
     * @param x The X amount.
     * @param y The Y amount.
     * @param z The Z amount.
     *
     * @return The resulting vector.
     */
    fun divide(x: Double, y: Double, z: Double): Vec3d = Vec3d(this.x / x, this.y / y, this.z / z)

    /**
     * Divides this vector by the given [other] vector and returns the result.
     *
     * @param other The vector to divide by.
     *
     * @return The resulting vector.
     */
    fun divide(other: Vec3d): Vec3d = divide(other.x, other.y, other.z)

    /**
     * Divides this vector by the given [other] vector and returns the result.
     *
     * @param other The vector to divide by.
     *
     * @return The resulting vector.
     */
    fun divide(other: Vec3i): Vec3d = divide(other.x.toDouble(), other.y.toDouble(), other.z.toDouble())

    /**
     * Divides this vector by the given [factor] and returns the result.
     *
     * This is equivalent to calling [divide] with the same factor for each
     * component.
     *
     * @param factor The factor to divide by.
     *
     * @return The resulting vector.
     */
    fun divide(factor: Double): Vec3d = divide(factor, factor, factor)

    /**
     * Calculates the dot product of this vector and the given [other] vector.
     *
     * @param other The other vector.
     *
     * @return The dot product.
     */
    fun dot(other: Vec3d): Double = x * other.x + y * other.y + z * other.z

    /**
     * Calculates the cross product of this vector and the given [other]
     * vector.
     *
     * @param other The other vector.
     *
     * @return The cross product.
     */
    fun cross(other: Vec3d): Vec3d = Vec3d(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x)

    /**
     * Returns a vector with the absolute values of the components of this
     * vector.
     *
     * @return An absolute vector.
     */
    fun abs(): Vec3d = Vec3d(abs(x), abs(y), abs(z))

    /**
     * Returns a vector with the components of this vector negated.
     *
     * @return A negated vector.
     */
    fun negate(): Vec3d = Vec3d(-x, -y, -z)

    /**
     * Returns a normalized version of this vector.
     *
     * A normalized vector is defined as a vector with a length of 1.
     *
     * @return A normalized vector.
     */
    fun normalize(): Vec3d {
        val length = length()
        return Vec3d(x / length, y / length, z / length)
    }

    /**
     * Calculates the squared distance between this vector and the given [x],
     * [y], and [z] values.
     *
     * @param x The X distance.
     * @param y The Y distance.
     * @param z The Z distance.
     *
     * @return The squared distance.
     */
    fun distanceSquared(x: Double, y: Double, z: Double): Double {
        val dx = this.x - x
        val dy = this.y - y
        val dz = this.z - z
        return dx * dx + dy * dy + dz * dz
    }

    /**
     * Calculates the squared distance between this vector and the
     * given [other] vector.
     *
     * @param other The other vector.
     *
     * @return The squared distance.
     */
    fun distanceSquared(other: Vec3d): Double = distanceSquared(other.x, other.y, other.z)

    /**
     * Calculates the squared distance between this vector and the
     * given [other] vector.
     *
     * @param other The other vector.
     *
     * @return The squared distance.
     */
    fun distanceSquared(other: Vec3i): Double = distanceSquared(other.x.toDouble(), other.y.toDouble(), other.z.toDouble())

    /**
     * Calculates the distance between this vector and the given [x], [y],
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
     * Calculates the distance between this vector and the given [other]
     * vector.
     *
     * @param other The other vector.
     *
     * @return The distance.
     */
    fun distance(other: Vec3d): Double = distance(other.x, other.y, other.z)

    /**
     * Calculates the distance between this vector and the given [other]
     * vector.
     *
     * @param other The other vector.
     *
     * @return The distance.
     */
    fun distance(other: Vec3i): Double = distance(other.x.toDouble(), other.y.toDouble(), other.z.toDouble())

    /**
     * Computes the length of this vector.
     *
     * @return The length.
     */
    fun lengthSquared(): Double = x * x + y * y + z * z

    /**
     * Calculates the length of this vector.
     *
     * @return The length.
     */
    fun length(): Double = sqrt(lengthSquared())

    /**
     * Converts this double vector to an equivalent integer vector.
     *
     * @return The converted vector.
     */
    fun asVec3i(): Vec3i = Vec3i(floorX(), floorY(), floorZ())

    /**
     * Converts this double vector to an equivalent position.
     *
     * @return The converted position.
     */
    fun asPosition(): Position = Position(x, y, z)

    override fun compareTo(other: Vec3d): Int = lengthSquared().compareTo(other.lengthSquared())

    override fun toString(): String = "($x, $y, $z)"

    companion object {

        /**
         * The zero vector.
         */
        @JvmField
        val ZERO: Vec3d = Vec3d(0.0, 0.0, 0.0)
    }
}
