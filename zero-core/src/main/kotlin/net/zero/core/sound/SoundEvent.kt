package net.zero.core.sound

import net.kyori.adventure.sound.Sound
import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi

/**
 * A type of sound.
 */
@ImmutableType
@SidedApi(Side.BOTH)
interface SoundEvent : Sound.Type {

    /**
     * The range that this sound can be heard from.
     *
     * A value of 0 indicates the sound does not have a range.
     */
    val range: Float

}
