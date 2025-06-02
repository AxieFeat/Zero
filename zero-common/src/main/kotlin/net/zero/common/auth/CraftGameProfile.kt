package net.zero.common.auth

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import net.zero.core.auth.GameProfile
import net.zero.core.auth.ProfileProperty
import java.util.*

@JvmRecord
data class CraftGameProfile(
    override val uuid: UUID,
    override val name: String,
    override val properties: PersistentList<ProfileProperty>
) : GameProfile {

    override fun withProperties(properties: Collection<ProfileProperty>): GameProfile = CraftGameProfile(uuid, name, properties.toPersistentList())

    override fun withProperty(property: ProfileProperty): GameProfile = CraftGameProfile(uuid, name, properties.add(property))

    override fun withoutProperty(index: Int): GameProfile = CraftGameProfile(uuid, name, properties.removeAt(index))

    override fun withoutProperty(property: ProfileProperty): GameProfile = CraftGameProfile(uuid, name, properties.remove(property))

    object Factory : GameProfile.Factory {

        override fun of(name: String, uuid: UUID, properties: List<ProfileProperty>): GameProfile = CraftGameProfile(uuid, name, properties.toPersistentList())
    }

}