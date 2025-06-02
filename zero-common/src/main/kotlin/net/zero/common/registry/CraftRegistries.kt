package net.zero.common.registry

import com.google.common.collect.Collections2
import net.kyori.adventure.key.Key
import net.zero.common.registry.loader.RegistryLoader
import net.zero.common.resource.CraftResourceKeys
import net.zero.core.registry.DefaultedRegistry
import net.zero.core.registry.Registry
import net.zero.core.registry.RegistryHolder
import net.zero.core.resource.ResourceKey
import java.util.function.Supplier

/**
 * This class contains all of the built-in registries for Zero. These are required by the API to exist, and they exist in this class
 * because it is easier to not have to downcast the registries available in the API everywhere.
 */
object CraftRegistries {

    private val LOADERS = LinkedHashMap<Key, Runnable>()
    private val WRITABLE_PARENT: WritableRegistry<WritableRegistry<*>> = CraftSimpleRegistry.standard(CraftResourceKeys.PARENT)
    @JvmField
    val PARENT: CraftRegistry<out CraftRegistry<*>> = WRITABLE_PARENT

    /*
     * Registry constructor functions
     */

    @JvmStatic
    private fun <T> simple(key: ResourceKey<out Registry<T>>, bootstrap: Bootstrap<T>): CraftRegistry<T> =
        internalRegister(key, CraftSimpleRegistry.standard(key), bootstrap)

    @JvmStatic
    private fun <T> defaulted(key: ResourceKey<out Registry<T>>, defaultName: String, bootstrap: Bootstrap<T>): CraftDefaultedRegistry<T> =
        internalRegister(key, CraftDefaultedSimpleRegistry.standard(key, Key.key(defaultName)), bootstrap)

    @JvmStatic
    private fun <T> defaultedIntrusive(key: ResourceKey<out Registry<T>>, defaultName: String,
                                       bootstrap: Bootstrap<T>): CraftDefaultedRegistry<T> {
        return internalRegister(key, CraftDefaultedSimpleRegistry.intrusive(key, Key.key(defaultName)), bootstrap)
    }

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    private fun <T, R : WritableRegistry<T>> internalRegister(key: ResourceKey<out Registry<T>>, registry: R, loader: Bootstrap<T>): R {
        LOADERS.put(key.location) { loader.run(registry) }
        WRITABLE_PARENT.register(key as ResourceKey<WritableRegistry<*>>, registry)
        return registry
    }

    /*
     * Registry registration functions. Designed to stop having to leak the WritableRegistry API in to other parts of the system,
     * as well as controlling how registry values are registered.
     */

    @JvmStatic
    fun <T, V : T> register(registry: CraftRegistry<T>, key: Key, value: V): V =
        register(registry, ResourceKey.of(registry.key, key), value)

    @JvmStatic
    fun <T, V : T> register(registry: CraftRegistry<T>, key: ResourceKey<T>, value: V): V {
        (registry as WritableRegistry<T>).register(key, value)
        return value
    }

    /*
     * Bootstrap constructors. Used to create Bootstrap functions from registry loaders and data loaders.
     */

    @JvmStatic
    private fun <T> loader(loader: Supplier<RegistryLoader<T>>): Bootstrap<T> =
        Bootstrap { loader.get().forEach { key, value -> register(it, key, value) } }

    /*
     * Bootstrapping methods. Used to initialize all the registries at the right time from the Bootstrap class.
     */

    @JvmStatic
    fun bootstrap() {
        WRITABLE_PARENT.freeze()
        runLoaders()
        WRITABLE_PARENT.forEach { it.freeze() }
        validateAll(WRITABLE_PARENT)
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
    fun <T : Registry<*>> validateAll(parent: Registry<T>) {
        parent.forEach { registry ->
            if (registry.keys().isEmpty()) System.err.println("Registry ${registry.key} was empty after loading!")
            if (registry is CraftDefaultedRegistry<*>) {
                val defaultKey = registry.defaultKey
                checkNotNull(registry.get(defaultKey)) { "Default value for key $defaultKey in registry ${registry.key} was not loaded!" }
            }
        }
    }

    /**
     * A function used to bootstrap registries by preloading all the values in to them, available for use very early in the server
     * lifecycle, to avoid any failures in attempting to retrieve them.
     */
    private fun interface Bootstrap<T> {

        fun run(registry: CraftRegistry<T>)
    }

    private class RegistryInitializationException(message: String, cause: Throwable) : RuntimeException(message, cause)

    /**
     * The backend registry manager implementation. Moved inside CraftRegistries as it needs access to internals that would rather
     * not be otherwise published and available to other components.
     */
    object StaticHolder : RegistryHolder {

        override val registries: Collection<Registry<*>>
            get() = Collections2.transform(PARENT.holders()) { it.value() }

        @Suppress("UNCHECKED_CAST")
        override fun <E> getRegistry(key: ResourceKey<out Registry<E>>): Registry<E>? {
            return WRITABLE_PARENT.get(key as ResourceKey<WritableRegistry<*>>) as? Registry<E>
        }

        @Suppress("UNCHECKED_CAST")
        override fun <E> getDefaultedRegistry(key: ResourceKey<out Registry<E>>): DefaultedRegistry<E>? {
            return WRITABLE_PARENT.get(key as ResourceKey<WritableRegistry<*>>) as? DefaultedRegistry<E>
        }
    }
}
