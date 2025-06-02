package net.zero.common.sound

import net.zero.core.sound.Music
import net.zero.core.sound.SoundEvent

data class CraftMusic(
    override val sound: SoundEvent,
    override val minimumDelay: Int,
    override val maximumDelay: Int,
    override val replaceCurrentMusic: Boolean
) : Music {

    object Factory : Music.Factory {

        override fun of(sound: SoundEvent, minimumDelay: Int, maximumDelay: Int, replaceCurrentMusic: Boolean): Music = CraftMusic(sound, minimumDelay, maximumDelay, replaceCurrentMusic)
    }

}