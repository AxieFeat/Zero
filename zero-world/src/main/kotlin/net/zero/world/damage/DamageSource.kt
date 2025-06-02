package net.zero.world.damage

import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import net.zero.annotation.TypeFactory
import net.zero.core.Zero
import net.zero.entity.Entity
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import net.zero.world.damage.type.DamageType

/**
 * A source of damage to something.
 */
@ImmutableType
@SidedApi(Side.BOTH)
interface DamageSource {

    /**
     * The type of damage that has been caused by this source.
     */
    val type: DamageType

    @ApiStatus.Internal
    @TypeFactory
    @SidedApi(Side.SERVER)
    interface Factory {

        fun of(type: DamageType): DamageSource

        fun entity(type: DamageType, entity: Entity): EntityDamageSource

        fun indirectEntity(type: DamageType, entity: Entity, indirectEntity: Entity): IndirectEntityDamageSource
    }

    companion object {

        /**
         * Creates a new damage source with the given [type].
         *
         * @param type The type.
         *
         * @return A new damage source.
         */
        @JvmStatic
        @Contract("_ -> new", pure = true)
        @SidedApi(Side.SERVER)
        fun of(type: DamageType): DamageSource = Zero.factory<Factory>().of(type)
    }
}
