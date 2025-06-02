package net.zero.common.sound

import net.kyori.adventure.key.Key
import net.zero.core.sound.SoundEvent

data class CraftSoundEvent(
    private val key: Key,
    override val range: Float
) : SoundEvent {

    override fun key(): Key = key

}