package net.zero.annotation

import java.lang.annotation.Inherited
import kotlin.reflect.KClass

/**
 * Indicates that the annotated type is catalogued by the
 * given [catalogue] type.
 *
 * For requirements of catalogue types, see the [Catalogue] annotation.
 *
 * @param catalogue The type of the catalogue.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@Inherited
@MustBeDocumented
annotation class CataloguedBy(val catalogue: KClass<*>)
