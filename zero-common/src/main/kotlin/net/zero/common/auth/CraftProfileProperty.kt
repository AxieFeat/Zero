package net.zero.common.auth

import net.zero.core.auth.ProfileProperty

@JvmRecord
data class CraftProfileProperty(
    override val name: String,
    override val value: String,
    override val signature: String?
) : ProfileProperty {

    override fun withSignature(signature: String?): ProfileProperty = CraftProfileProperty(name, value, signature)

    override fun withoutSignature(): ProfileProperty = CraftProfileProperty(name, value, null)

    object Factory : ProfileProperty.Factory {

        override fun of(name: String, value: String, signature: String?): ProfileProperty = CraftProfileProperty(name, value, signature)
    }

}