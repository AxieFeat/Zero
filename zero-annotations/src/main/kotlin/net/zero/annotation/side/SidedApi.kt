package net.zero.annotation.side

/**
 * A separate API is tagged with this annotation.
 *
 * For example a class can be labeled as `@SidedApi(Side.SERVER)` -
 * this means that all its fields/methods etc. will refer to the server.
 * However, if the same class will have on some method,
 * for example, `@SidedApi(Side.CLIENT)` - it means that this particular method can be used
 * on the client side.
 *
 * @param side Side of API.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.SOURCE)
annotation class SidedApi(
    val side: Side
)
