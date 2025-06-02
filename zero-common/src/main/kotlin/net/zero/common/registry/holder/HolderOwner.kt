package net.zero.common.registry.holder

interface HolderOwner<T> {

    fun canSerializeIn(owner: HolderOwner<T>): Boolean = owner === this
}
