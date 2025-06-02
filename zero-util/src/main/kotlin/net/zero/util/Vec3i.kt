package net.zero.util

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * An integer vector with an X, Y, and Z component.
 *
 * This is mostly used to represent positions of blocks in a world, as blocks
 * are axis-aligned and so have integer coordinates.
 *
 * @property x The X component of this vector.
 * @property y The Y component of this vector.
 * @property z The Z component of this vector.
 */
@JvmRecord
@Suppress("TooManyFunctions")
@SidedApi(Side.BOTH)
data class Vec3i(val x: Int, val y: Int, val z: Int) : Comparable<Vec3i> {

    /**
     * Gets the chunk X coordinate of this vector.
     *
     * @return The chunk X.
     */
    fun chunkX(): Int = x shr 4

    /**
     * Gets the chunk Z coordinate of this vector.
     *
     * @return The chunk Z.
     */
    fun chunkZ(): Int = z shr 4

    /**
     * Creates a new vector with the given [x] component.
     *
     * @param x The new X component.
     *
     * @return The new vector.
     */
    fun withX(x: Int): Vec3i = Vec3i(x, this.y, this.z)

    /**
     * Creates a new vector with the given [y] component.
     *
     * @param y The new Y component.
     *
     * @return The new vector.
     */
    fun withY(y: Int): Vec3i = Vec3i(this.x, y, this.z)

    /**
     * Creates a new vector with the given [z] component.
     *
     * @param z The new Z component.
     *
     * @return The new vector.
     */
    fun withZ(z: Int): Vec3i = Vec3i(this.x, this.y, z)

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
    fun add(x: Int, y: Int, z: Int): Vec3i = Vec3i(this.x + x, this.y + y, this.z + z)

    /**
     * Adds the given [amount] to this vector and returns the result.
     *
     * @param amount The amount to add.
     *
     * @return The resulting vector.
     */
    fun add(amount: Int): Vec3i = add(amount, amount, amount)

    /**
     * Adds the given [other] vector to this vector and returns the result.
     *
     * @param other The vector to add.
     *
     * @return The resulting vector.
     */
    fun add(other: Vec3i): Vec3i = add(other.x, other.y, other.z)

    /**
     * Adds the given [other] vector to this vector and returns the result.
     *
     * @param other The vector to add.
     *
     * @return The resulting vector.
     */
    fun add(other: Vec3d): Vec3i = add(other.floorX(), other.floorY(), other.floorZ())

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
    fun subtract(x: Int, y: Int, z: Int): Vec3i = Vec3i(this.x - x, this.y - y, this.z - z)

    /**
     * Subtracts the given [amount] from this vector and returns the result.
     *
     * @param amount The amount to subtract.
     *
     * @return The resulting vector.
     */
    fun subtract(amount: Int): Vec3i = subtract(amount, amount, amount)

    /**
     * Subtracts the given [other] vector from this vector and returns the
     * result.
     *
     * @param other The vector to subtract.
     *
     * @return The resulting vector.
     */
    fun subtract(other: Vec3i): Vec3i = subtract(other.x, other.y, other.z)

    /**
     * Subtracts the given [other] vector from this vector and returns the
     * result.
     *
     * @param other The vector to subtract.
     *
     * @return The resulting vector.
     */
    fun subtract(other: Vec3d): Vec3i = subtract(other.floorX(), other.floorY(), other.floorZ())

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
    fun multiply(x: Int, y: Int, z: Int): Vec3i = Vec3i(this.x * x, this.y * y, this.z * z)

    /**
     * Multiplies this vector by the given [other] vector and returns the
     * result.
     *
     * @param other The vector to multiply by.
     *
     * @return The resulting vector.
     */
    fun multiply(other: Vec3i): Vec3i = multiply(other.x, other.y, other.z)

    /**
     * Multiplies this vector by the given [other] vector and returns the
     * result.
     *
     * @param other The vector to multiply by.
     *
     * @return The resulting vector.
     */
    fun multiply(other: Vec3d): Vec3i = multiply(other.floorX(), other.floorY(), other.floorZ())

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
    fun multiply(factor: Int): Vec3i = multiply(factor, factor, factor)

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
    fun divide(x: Int, y: Int, z: Int): Vec3i = Vec3i(this.x / x, this.y / y, this.z / z)

    /**
     * Divides this vector by the given [other] vector and returns the result.
     *
     * @param other The vector to divide by.
     *
     * @return The resulting vector.
     */
    fun divide(other: Vec3i): Vec3i = divide(other.x, other.y, other.z)

    /**
     * Divides this vector by the given [other] vector and returns the result.
     *
     * @param other The vector to divide by.
     * @return The resulting vector.
     */
    fun divide(other: Vec3d): Vec3i = divide(other.floorX(), other.floorY(), other.floorZ())

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
    fun divide(factor: Int): Vec3i = divide(factor, factor, factor)

    /**
     * Gets the vector in the given [direction] relative to this vector.
     *
     * For example, if this vector is (3, 4, 5) and the direction is NORTH,
     * the result will be (3, 4, 4), as north is in the -Z direction.
     */
    fun relative(direction: Direction): Vec3i = add(direction.normal)

    /**
     * Calculates the dot product of this vector and the given [other] vector.
     *
     * @param other The other vector.
     *
     * @return The dot product.
     */
    fun dot(other: Vec3i): Int = x * other.x + y * other.y + z * other.z

    /**
     * Calculates the cross product of this vector and the given [other]
     * vector.
     *
     * @param other The other vector.
     *
     * @return The cross product.
     */
    fun cross(other: Vec3i): Vec3i = Vec3i(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x)

    /**
     * Returns a vector with the absolute values of the components of this
     * vector.
     *
     * @return An absolute vector.
     */
    fun abs(): Vec3i = Vec3i(abs(x), abs(y), abs(z))

    /**
     * Returns a vector with the components of this vector negated.
     *
     * @return A negated vector.
     */
    fun negate(): Vec3i = Vec3i(-x, -y, -z)

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
    fun distanceSquared(x: Int, y: Int, z: Int): Int {
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
    fun distanceSquared(other: Vec3i): Int = distanceSquared(other.x, other.y, other.z)

    /**
     * Calculates the squared distance between this vector and the
     * given [other] vector.
     *
     * @param other The other vector.
     *
     * @return The squared distance.
     */
    fun distanceSquared(other: Vec3d): Int = distanceSquared(other.floorX(), other.floorY(), other.floorZ())

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
    fun distance(x: Int, y: Int, z: Int): Double = sqrt(distanceSquared(x, y, z).toDouble())

    /**
     * Calculates the distance between this vector and the given [other]
     * vector.
     *
     * @param other The other vector.
     *
     * @return The distance.
     */
    fun distance(other: Vec3i): Double = distance(other.x, other.y, other.z)

    /**
     * Calculates the distance between this vector and the given [other]
     * vector.
     *
     * @param other The other vector.
     *
     * @return The distance.
     */
    fun distance(other: Vec3d): Double = distance(other.floorX(), other.floorY(), other.floorZ())

    /**
     * Computes the length of this vector.
     *
     * @return The length.
     */
    fun lengthSquared(): Int = x * x + y * y + z * z

    /**
     * Calculates the length of this vector.
     *
     * @return The length.
     */
    fun length(): Double = sqrt(lengthSquared().toDouble())

    /**
     * Converts this integer vector to an equivalent double vector.
     *
     * @return The converted vector.
     */
    fun asVec3d(): Vec3d = Vec3d(x.toDouble(), y.toDouble(), z.toDouble())

    /**
     * Converts this integer vector to an equivalent double vector, at the
     * center of a block.
     *
     * This is done by adding 0.5 to each of the components.
     *
     * @return The converted centered vector.
     */
    fun asCenteredVec3d(): Vec3d = Vec3d(x + 0.5, y + 0.5, z + 0.5)

    /**
     * Converts this integer vector to an equivalent position.
     *
     * @return The converted position.
     */
    fun asPosition(): Position = Position(x.toDouble(), y.toDouble(), z.toDouble())

    override fun compareTo(other: Vec3i): Int = lengthSquared().compareTo(other.lengthSquared())

    override fun toString(): String = "($x, $y, $z)"

    companion object {

        /**
         * The zero vector.
         */
        @JvmField
        val ZERO: Vec3i = Vec3i(0, 0, 0)
    }
}
