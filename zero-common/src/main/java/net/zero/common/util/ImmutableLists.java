package net.zero.common.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Collection;
import java.util.List;

/*
 * A wrapper around the immutable lists from Java 9.
 *
 * Kotlin doesn't like us using these directly, since it thinks `listOf` is a better option, which it isn't, as `listOf` returns a mutable
 * ArrayList, and we want a fully immutable list, not just a read-only list.
 */
public final class ImmutableLists {

    public static <E> @NotNull @Unmodifiable List<E> copyOf(Collection<E> collection) {
        return List.copyOf(collection);
    }

    public static <E> @NotNull @Unmodifiable List<E> of() {
        return List.of();
    }

    public static <E> @NotNull @Unmodifiable List<E> of(E e1) {
        return List.of(e1);
    }

    public static <E> @NotNull @Unmodifiable List<E> of(E e1, E e2) {
        return List.of(e1, e2);
    }

    public static <E> @NotNull @Unmodifiable List<E> of(E e1, E e2, E e3) {
        return List.of(e1, e2, e3);
    }

    @SafeVarargs
    public static <E> @NotNull @Unmodifiable List<E> of(E... elements) {
        return List.of(elements);
    }

    public static <E> @NotNull @Unmodifiable List<E> ofArray(E[] elements) {
        return List.of(elements);
    }

    private ImmutableLists() {
        throw new AssertionError("This class cannot be instantiated!");
    }
}