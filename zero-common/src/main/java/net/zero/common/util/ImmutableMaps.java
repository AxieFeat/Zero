package net.zero.common.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Map;

public final class ImmutableMaps {

    public static <K, V> @NotNull @Unmodifiable Map<K, V> copyOf(Map<? extends K, ? extends V> map) {
        return Map.copyOf(map);
    }

    public static <K, V> @NotNull @Unmodifiable Map<K, V> of() {
        return Map.of();
    }

    public static <K, V> @NotNull @Unmodifiable Map<K, V> of(K k1, V v1) {
        return Map.of(k1, v1);
    }

    public static <K, V> @NotNull @Unmodifiable Map<K, V> of(K k1, V v1, K k2, V v2) {
        return Map.of(k1, v1, k2, v2);
    }

    public static <K, V> @NotNull @Unmodifiable Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return Map.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5);
    }

    private ImmutableMaps() {
        throw new AssertionError("This class cannot be instantiated!");
    }
}