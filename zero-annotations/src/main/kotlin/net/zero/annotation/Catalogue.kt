package net.zero.annotation

import kotlin.reflect.KClass

/**
 * Indicates that the annotated type is a catalogue type, usually meaning it
 * provides access to many static instances of the given catalogued [type].
 *
 * All types contained within this API that are annotated with this
 * annotation will meet certain requirements that are listed below, and it is
 * also highly recommended that users of this annotation also meet the same
 * requirements, as follows:
 * - The values made available within this type must remain constant for
 * the entire runtime.
 * - All values must not be references to null, or any other types that could
 * cause ambiguity or annoyance for users of the API.
 *
 * In addition, it is possible for two constant values to point to the same
 * object, for the purpose of creating aliases.
 *
 * Furthermore, not all catalogue types are exhaustive, there may be more
 * instances of the catalogued type available. Within the scope of this API,
 * all of the constants listed in the catalogue type will always be an
 * exhaustive list of all of the known constants that are guaranteed to always
 * be available.
 *
 * @param type The type the target is a catalogue of.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class Catalogue(val type: KClass<*>)
