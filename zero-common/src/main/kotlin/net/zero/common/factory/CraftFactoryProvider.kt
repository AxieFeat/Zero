package net.zero.common.factory

import net.zero.core.factory.FactoryProvider
import net.zero.core.factory.TypeNotFoundException

object CraftFactoryProvider : FactoryProvider {

    private val factories = mutableMapOf<Class<*>, Any>()

    @Suppress("UNCHECKED_CAST")
    override fun <T> provide(type: Class<T>): T {
        return factories[type] as? T ?: throw TypeNotFoundException("Type $type has no factory registered!")
    }

    override fun <T> register(type: Class<T>, factory: T) {
        require(!factories.contains(type)) { "Duplicate registration for type $type!" }
        factories[type] = factory as Any
    }

    @JvmStatic
    fun bootstrap() {

    }

}