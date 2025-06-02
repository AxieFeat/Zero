package net.zero.annotation

/**
 * Indicates that the marked field should be ignored by the immutability checker.
 *
 * This is for special fields that have certain properties that still work but conflict with the immutability checker,
 * to avoid having to remove the checker entirely, which could lead to issues with other properties in the type.
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ImmutableTypeIgnore
