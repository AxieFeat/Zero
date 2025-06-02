package net.zero.core

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.core.factory.FactoryProvider
import net.zero.core.registry.RegistryHolder

@SidedApi(Side.BOTH)
object Zero {

    @JvmStatic
    internal var factoryProvider: FactoryProvider? = null
    @JvmStatic
    internal var staticRegistryHolder: RegistryHolder? = null

    @JvmStatic
    fun factoryProvider(): FactoryProvider = factoryProvider!!

    @JvmStatic
    fun staticRegistryHolder(): RegistryHolder = staticRegistryHolder!!

    @JvmStatic
    @JvmSynthetic
    inline fun <reified T> factory(): T = factoryProvider().provide(T::class.java)

}