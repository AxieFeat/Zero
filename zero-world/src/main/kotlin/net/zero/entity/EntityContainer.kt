package net.zero.entity

import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import java.util.function.Predicate

/**
 * Something that contains entities.
 */
@SidedApi(Side.BOTH)
interface EntityContainer {

    /**
     * All entities contained within this container.
     */
    val entities: Collection<Entity>

    /**
     * Gets all entities of the given [type] contained within this container.
     *
     * @param E The entity type.
     * @param type The entity type.
     *
     * @return All entities of the given type.
     */
    fun <E : Entity> getEntitiesOfType(type: Class<E>): Collection<E>

    /**
     * Gets all entities of the given [type] matching the given [predicate]
     * contained within this container.
     *
     * @param E The entity type.
     * @param type The entity type.
     * @param predicate The predicate to filter entities with.
     *
     * @return The entities.
     */
    fun <E : Entity> getEntitiesOfType(type: Class<E>, predicate: Predicate<E>): Collection<E>
}
