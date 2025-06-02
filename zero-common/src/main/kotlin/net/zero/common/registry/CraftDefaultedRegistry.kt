package net.zero.common.registry

import net.zero.core.registry.DefaultedRegistry

/**
 * This type exists to unify the APIs of CraftRegistry and DefaultedRegistry, so we can use internal methods on defaulted registries
 * without having to use the implementation class, which is writable.
 */
interface CraftDefaultedRegistry<T> : CraftRegistry<T>, DefaultedRegistry<T>
