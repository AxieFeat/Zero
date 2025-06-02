package net.zero.world.chunk

import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.annotation.TypeFactory
import net.zero.core.Zero
import org.jetbrains.annotations.ApiStatus

/**
 * A set of flags used to determine what happens when a block is changed.
 */
@ImmutableType
@SidedApi(Side.BOTH)
interface BlockChangeFlags {

    /**
     * The raw value of this set of flags.
     */
    val raw: Int

    /**
     * If neighbours should be updated when the block is updated.
     */
    val updateNeighbours: Boolean

    /**
     * If clients with the block in render distance should be notified of its
     * update.
     */
    val notifyClients: Boolean

    /**
     * If observer blocks should be updated.
     */
    val updateNeighbourShapes: Boolean

    /**
     * If blocks can be destroyed as a result of updating neighbour shapes.
     */
    val neighbourDrops: Boolean

    /**
     * If the block change considers that blocks can be moved in the world.
     */
    val blockMoving: Boolean

    /**
     * If lighting updates should be performed.
     */
    val lighting: Boolean

    /**
     * Creates a new set of flags with the given [updateNeighbours] setting.
     *
     * @param updateNeighbours The new setting.
     *
     * @return The resulting flags.
     */
    @SidedApi(Side.SERVER)
    fun withUpdateNeighbours(updateNeighbours: Boolean): BlockChangeFlags

    /**
     * Creates a new set of flags with the given [notifyClients] setting.
     *
     * @param notifyClients The new setting.
     *
     * @return The resulting flags.
     */
    @SidedApi(Side.SERVER)
    fun withNotifyClients(notifyClients: Boolean): BlockChangeFlags

    /**
     * Creates a new set of flags with the given [updateNeighbourShapes]
     * setting.
     *
     * @param updateNeighbourShapes The new setting.
     *
     * @return The resulting flags.
     */
    @SidedApi(Side.SERVER)
    fun withUpdateNeighbourShapes(updateNeighbourShapes: Boolean): BlockChangeFlags

    /**
     * Creates a new set of flags with the given [neighbourDrops] setting.
     *
     * @param neighbourDrops The new setting.
     *
     * @return The resulting flags.
     */
    @SidedApi(Side.SERVER)
    fun withNeighbourDrops(neighbourDrops: Boolean): BlockChangeFlags

    /**
     * Creates a new set of flags with the given [blockMoving] setting.
     *
     * @param blockMoving The new setting.
     *
     * @return The resulting flags.
     */
    @SidedApi(Side.SERVER)
    fun withBlockMoving(blockMoving: Boolean): BlockChangeFlags

    /**
     * Creates a new set of flags with the given [lighting] setting.
     *
     * @param lighting The new setting.
     *
     * @return The resulting flags.
     */
    @SidedApi(Side.SERVER)
    fun withLighting(lighting: Boolean): BlockChangeFlags

    /**
     * Performs a bitwise NOT operation on this set of flags.
     *
     * @return The resulting flags.
     */
    fun not(): BlockChangeFlags

    /**
     * Performs a bitwise AND operation between this set of flags and the
     * given [other] set of flags.
     *
     * @param other The other flags.
     *
     * @return The resulting flags.
     */
    fun and(other: BlockChangeFlags): BlockChangeFlags

    /**
     * Performs a bitwise OR operation between this set of flags and the
     * given [other] set of flags.
     *
     * @param other The other flags.
     *
     * @return The resulting flags.
     */
    fun or(other: BlockChangeFlags): BlockChangeFlags

    @TypeFactory
    @ApiStatus.Internal
    @SidedApi(Side.SERVER)
    interface Factory {

        fun none(): BlockChangeFlags

        fun all(): BlockChangeFlags
    }

    companion object {

        /**
         * A set of flags with no flags set.
         *
         * @return A flag set with no flags set.
         */
        @JvmStatic
        @SidedApi(Side.SERVER)
        fun none(): BlockChangeFlags = Zero.factory<Factory>().none()

        /**
         * A set of flags with all the flags set.
         *
         * @return A flag set with all flags set.
         */
        @JvmStatic
        @SidedApi(Side.SERVER)
        fun all(): BlockChangeFlags = Zero.factory<Factory>().all()
    }
}
