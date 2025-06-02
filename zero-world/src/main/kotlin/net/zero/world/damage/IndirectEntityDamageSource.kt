package net.zero.world.damage

import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.core.Zero
import net.zero.entity.Entity
import org.jetbrains.annotations.Contract
import net.zero.world.damage.type.DamageType

/**
 * A damage source that indirectly affects an entity.
 */
@ImmutableType
@SidedApi(Side.BOTH)
interface IndirectEntityDamageSource : EntityDamageSource {

    /**
     * The entity that indirectly caused the damage.
     */
    val indirectEntity: Entity?

    companion object {

        /**
         * Creates a new damage source that causes the given [type] of damage,
         * and that is caused directly by the given [entity], and indirectly by
         * the given [indirectEntity].
         *
         * @param type The type of damage the source will inflict.
         * @param entity The entity that directly caused the damage.
         * @param indirectEntity The entity that indirectly caused the damage.
         *
         * @return A new indirect entity damage source.
         */
        @JvmStatic
        @Contract("_, _, _ -> new", pure = true)
        @SidedApi(Side.SERVER)
        fun of(type: DamageType, entity: Entity, indirectEntity: Entity): IndirectEntityDamageSource =
            Zero.factory<DamageSource.Factory>().indirectEntity(type, entity, indirectEntity)
    }
}
