package net.zero.world.damage

import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.core.Zero
import net.zero.entity.Entity
import org.jetbrains.annotations.Contract
import net.zero.world.damage.type.DamageType

/**
 * A damage source that affects an entity.
 */
@ImmutableType
@SidedApi(Side.BOTH)
interface EntityDamageSource : DamageSource {

    /**
     * The entity that caused the damage.
     */
    val entity: Entity

    companion object {

        /**
         * Creates a new damage source with the given [type], where the damage
         * originated from the given [entity].
         *
         * @param type The type of damage the source will cause.
         * @param entity The source of the damage.
         *
         * @return A new entity damage source.
         */
        @JvmStatic
        @Contract("_, _ -> new", pure = true)
        @SidedApi(Side.SERVER)
        fun of(type: DamageType, entity: Entity): EntityDamageSource = Zero.factory<DamageSource.Factory>().entity(type, entity)
    }
}
