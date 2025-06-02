package net.zero.core.auth

import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.annotation.TypeFactory
import net.zero.core.Zero
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import org.jetbrains.annotations.Unmodifiable
import java.util.UUID

/**
 * The profile of an authenticated player.
 */
@ImmutableType
@SidedApi(Side.BOTH)
interface GameProfile {

    /**
     * The UUID of the profile.
     */
    val uuid: UUID

    /**
     * The name of the profile.
     */
    val name: String

    /**
     * All of the properties for this profile.
     */
    val properties: @Unmodifiable List<ProfileProperty>

    /**
     * Creates a new game profile with the given [properties].
     *
     * @param properties The new properties.
     *
     * @return A new game profile.
     */
    @Contract("_ -> new", pure = true)
    fun withProperties(properties: Collection<ProfileProperty>): GameProfile

    /**
     * Creates a new game profile with the given [property] added to the list
     * of properties.
     *
     * @param property The property to add.
     *
     * @return A new game profile.
     */
    @Contract("_ -> new", pure = true)
    fun withProperty(property: ProfileProperty): GameProfile

    /**
     * Creates a new game profile with the property at the given [index]
     * removed from the list of properties.
     *
     * @param index The index of the property to remove.
     *
     * @return A new game profile.
     */
    @Contract("_ -> new", pure = true)
    fun withoutProperty(index: Int): GameProfile

    /**
     * Creates a new game profile with the given [property] removed from the
     * list of properties.
     *
     * @param property The property to remove.
     *
     * @return A new game profile.
     */
    @Contract("_ -> new", pure = true)
    fun withoutProperty(property: ProfileProperty): GameProfile

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun of(name: String, uuid: UUID, properties: List<ProfileProperty>): GameProfile
    }

    companion object {

        /**
         * Creates a new game profile with the given [name], [uuid], and list
         * of profile [properties].
         *
         * @param name The name of the profile.
         * @param uuid The UUID of the profile.
         * @param properties The list of profile properties.
         *
         * @return A new profile.
         */
        @JvmStatic
        @Contract("_, _, _ -> new", pure = true)
        fun of(name: String, uuid: UUID, properties: List<ProfileProperty>): GameProfile =
            Zero.factory<Factory>().of(name, uuid, properties)

        /**
         * Creates a new game profile with the given [name] and [uuid].
         *
         * @param name The name of the profile.
         * @param uuid The UUID of the profile.
         *
         * @return A new profile.
         */
        @JvmStatic
        @Contract("_, _ -> new", pure = true)
        fun of(name: String, uuid: UUID): GameProfile = of(name, uuid, emptyList())
    }
}
