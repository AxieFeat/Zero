package net.zero.core.sound

import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.annotation.TypeFactory
import net.zero.core.Zero
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract

/**
 * Music that may be played. This has a minimum and maximum delay before the
 * music will start playing, and the music can start playing anywhere in
 * between this time frame.
 */
@ImmutableType
@SidedApi(Side.BOTH)
interface Music {

    /**
     * The sound that will be played.
     */
    val sound: SoundEvent

    /**
     * The minimum delay before the music will start playing.
     */
    val minimumDelay: Int

    /**
     * The maximum delay before the music will start playing.
     */
    val maximumDelay: Int

    /**
     * If this music should replace any currently playing music.
     *
     * For example, the menu, credits do this.
     */
    val replaceCurrentMusic: Boolean

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        @SidedApi(Side.SERVER)
        fun of(sound: SoundEvent, minimumDelay: Int, maximumDelay: Int, replaceCurrentMusic: Boolean): Music
    }

    companion object {

        /**
         * Creates new music with the given values.
         *
         * @param sound The sound that will be played.
         * @param minimumDelay The minimum delay before starting.
         * @param maximumDelay The maximum delay before starting.
         *
         * @param replaceCurrentMusic If the current music should be replaced.
         */
        @JvmStatic
        @Contract("_, _, _, _ -> new", pure = true)
        @SidedApi(Side.SERVER)
        fun of(sound: SoundEvent, minimumDelay: Int, maximumDelay: Int, replaceCurrentMusic: Boolean): Music =
            Zero.factory<Factory>().of(sound, minimumDelay, maximumDelay, replaceCurrentMusic)
    }
}
