package net.zero.annotation.dsl

/**
 * Indicates the annotated element is part of the DSL for item metadata.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
@DslMarker
annotation class MetaDsl
