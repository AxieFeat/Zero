package net.zero.common.registry

import com.google.common.collect.Collections2
import net.kyori.adventure.key.Key
import net.zero.common.resource.CraftResourceKeys
import net.zero.core.registry.DefaultedRegistry
import net.zero.core.registry.Registry
import net.zero.core.registry.RegistryHolder
import net.zero.core.resource.ResourceKey

object CraftDynamicRegistries {

    private val LOADERS = LinkedHashMap<Key, Runnable>()
    private val WRITABLE_PARENT: WritableRegistry<WritableRegistry<*>> = CraftSimpleRegistry.standard(CraftResourceKeys.PARENT)
    @JvmField
    val PARENT: CraftRegistry<out CraftRegistry<*>> = WRITABLE_PARENT

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    private fun <T> register(key: ResourceKey<out Registry<T>>, loader: Bootstrap<T>): CraftRegistry<T> {
        val registry = CraftSimpleRegistry.standard(key)
        LOADERS.put(key.location) { loader.run(registry) }
        WRITABLE_PARENT.register(key as ResourceKey<WritableRegistry<*>>, registry)
        return registry
    }

    @JvmStatic
    fun bootstrap() {
        WRITABLE_PARENT.freeze()
        runLoaders()
        WRITABLE_PARENT.forEach { it.freeze() }
        CraftRegistries.validateAll(WRITABLE_PARENT)
    }

    @JvmStatic
    private fun runLoaders() {
        LOADERS.forEach { (key, action) ->
            try {
                action.run()
            } catch (exception: Exception) {
                throw RegistryInitializationException("Failed to bootstrap registry $key!", exception)
            }
            requireNotNull(WRITABLE_PARENT.get(key)) { "Cannot find registry for key $key in loading!" }.freeze()
        }
    }

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    fun <T> getRegistry(key: ResourceKey<out Registry<T>>): Registry<T>? {
        return WRITABLE_PARENT.get(key as ResourceKey<WritableRegistry<*>>) as? Registry<T>
    }

    private fun interface Bootstrap<T> {

        fun run(registry: CraftRegistry<T>)
    }

    private class RegistryInitializationException(message: String, cause: Throwable) : RuntimeException(message, cause)

    object DynamicHolder : RegistryHolder {

        override val registries: Collection<Registry<*>>
            get() = Collections2.transform(PARENT.holders()) { it.value() }

        override fun <E> getRegistry(key: ResourceKey<out Registry<E>>): Registry<E>? =
            CraftDynamicRegistries.getRegistry(key)

        @Suppress("UNCHECKED_CAST")
        override fun <E> getDefaultedRegistry(key: ResourceKey<out Registry<E>>): DefaultedRegistry<E>? {
            return WRITABLE_PARENT.get(key as ResourceKey<WritableRegistry<*>>) as? DefaultedRegistry<E>
        }
    }
}
