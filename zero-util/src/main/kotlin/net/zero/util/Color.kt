package net.zero.util

import net.kyori.adventure.util.HSVLike
import net.zero.annotation.ImmutableType
import net.zero.annotation.side.Side
import net.zero.annotation.side.SidedApi
import org.jetbrains.annotations.Range
import kotlin.math.floor

/**
 * A standard color in RGB format, with a red, green, and blue component.
 *
 * @param red the red component
 * @param green the green component
 * @param blue the blue component
 *
 * @throws IllegalArgumentException If any value is outside the 0..255 range.
 */
@SidedApi(Side.BOTH)
@JvmRecord
@ImmutableType
data class Color(
    val red:   @Range(from = 0, to = 255) Int = 0,
    val green: @Range(from = 0, to = 255) Int = 0,
    val blue:  @Range(from = 0, to = 255) Int = 0
) {

    companion object {
        @JvmStatic val BLACK = Color(0, 0, 0)
        @JvmStatic val DARK_BLUE = Color(0, 0, 170)
        @JvmStatic val DARK_GREEN = Color(0, 170, 0)
        @JvmStatic val DARK_AQUA = Color(0, 170, 170)
        @JvmStatic val DARK_RED = Color(170, 0, 0)
        @JvmStatic val DARK_PURPLE = Color(170, 0, 170)
        @JvmStatic val GOLD = Color(255, 170, 0)
        @JvmStatic val GRAY = Color(170, 170, 170)
        @JvmStatic val DARK_GRAY = Color(85, 85, 85)
        @JvmStatic val BLUE = Color(85, 85, 255)
        @JvmStatic val RED = Color(255, 85, 85)
        @JvmStatic val YELLOW = Color(255, 255, 85)
        @JvmStatic val GREEN = Color(85, 255, 85)
        @JvmStatic val AQUA = Color(85, 255, 255)
        @JvmStatic val LIGHT_PURPLE = Color(255, 85, 255)
        @JvmStatic val WHITE = Color(255, 255, 255)

        /**
         * Creates a new colour from the given hue, saturation, and value
         * components.
         *
         * @param hue The hue, between 0 and 1.
         * @param saturation The saturation, between 0 and 1.
         * @param value The value, between 0 and 1.
         *
         * @throws IllegalArgumentException If invalid hue section.
         *
         * @return The new instance of [Color].
         */
        @SidedApi(Side.BOTH)
        fun fromHsv(
            hue:        @Range(from = 0, to = 1) Float,
            saturation: @Range(from = 0, to = 1) Float,
            value:      @Range(from = 0, to = 1) Float
        ): Color {
            require(hue in 0f..1f) { "Hue must be between 0 and 1" }
            require(saturation in 0f..1f) { "Saturation must be between 0 and 1" }
            require(value in 0f..1f) { "Value must be between 0 and 1" }

            // This is taken from java.awt.Color#HSBtoRGB
            if (saturation == 0f) {
                val result = (value * 255f + 0.5f).toInt()
                return Color(result, result, result)
            }

            val h = (hue - floor(hue.toDouble()).toFloat()) * 6f
            val f = h - floor(h.toDouble()).toFloat()
            val p = value * (1f - saturation)
            val q = value * (1f - saturation * f)
            val t = value * (1f - (saturation * (1f - f)))

            return when (h.toInt()) {
                0 -> Color(
                    (value * 255f + 0.5f).toInt(),
                    (t * 255f + 0.5f).toInt(),
                    (p * 255f + 0.5f).toInt()
                )

                1 -> Color(
                    (q * 255f + 0.5f).toInt(),
                    (value * 255f + 0.5f).toInt(),
                    (p * 255f + 0.5f).toInt()
                )

                2 -> Color(
                    (p * 255f + 0.5f).toInt(),
                    (value * 255f + 0.5f).toInt(),
                    (t * 255f + 0.5f).toInt()
                )

                3 -> Color(
                    (p * 255f + 0.5f).toInt(),
                    (q * 255f + 0.5f).toInt(),
                    (value * 255f + 0.5f).toInt()
                )

                4 -> Color(
                    (t * 255f + 0.5f).toInt(),
                    (p * 255f + 0.5f).toInt(),
                    (value * 255f + 0.5f).toInt()
                )

                5 -> Color(
                    (value * 255f + 0.5f).toInt(),
                    (p * 255f + 0.5f).toInt(),
                    (q * 255f + 0.5f).toInt()
                )

                else -> throw IllegalArgumentException("Invalid hue section: $h")
            }
        }

        /**
         * Creates a new colour from the given hsv colour.
         *
         * @param hsv The hsv color.
         *
         * @return The new color.
         */
        @SidedApi(Side.BOTH)
        fun fromHsv(hsv: HSVLike): Color {
            return fromHsv(hsv.h(), hsv.s(), hsv.v())
        }

    }

    init {
        require(red in 0..255) { "Red value is not in 0..255 range!" }
        require(green in 0..255) { "Green value is not in 0..255 range!" }
        require(blue in 0..255) { "Blue value is not in 0..255 range!" }
    }

    /**
     * Gets this colour in encoded RGB form.
     *
     *
     *
     * The encoded form of an RGB colour is defined as follows:
     * - Bits 0-7 are the blue component
     * - Bits 8-15 are the green component
     * - Bits 16-23 are the red component
     * - Bits 24-31 are unused
     *
     *
     * @return The encoded RGB value.
     */
    fun encode(): Int {
        return (red shl 16) or (green shl 8) or blue
    }

    /**
     * Creates a new colour with the given new [red] value.
     *
     * @param red The new red value.
     *
     * @return The new color.
     */
    fun withRed(red: Int): Color {
        return Color(red, this.green, this.blue)
    }

    /**
     * Creates a new colour with the given new [green] value.
     *
     * @param green The new green value.
     *
     * @return The new color.
     */
    fun withGreen(green: Int): Color {
        return Color(this.red, green, this.blue)
    }

    /**
     * Creates a new colour with the given new [blue] value.
     *
     * @param blue The new blue value.
     *
     * @return The new color.
     */
    fun withBlue(blue: Int): Color {
        return Color(this.red, this.green, blue)
    }

    /**
     * Returns a copy of this [Color] instance.
     */
    fun copy(): Color {
        return Color(this.red, this.green, this.blue)
    }
}