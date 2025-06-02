package net.zero.annotation

/**
 * Indicates that the catalogue checker should ignore checking for the [Catalogue]d type having a corresponding [CataloguedBy] annotation.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class IgnoreNotCataloguedBy
