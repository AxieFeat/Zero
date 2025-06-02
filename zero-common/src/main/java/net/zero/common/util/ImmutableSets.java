package net.zero.common.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Collection;
import java.util.Set;

public final class ImmutableSets {

    public static <E> @NotNull @Unmodifiable Set<E> copyOf(Collection<E> collection) {
        return Set.copyOf(collection);
    }

    public static <E> @NotNull @Unmodifiable Set<E> of() {
        return Set.of();
    }

    public static <E> @NotNull @Unmodifiable Set<E> of(E e1, E e2, E e3) {
        return Set.of(e1, e2, e3);
    }

    private ImmutableSets() {
        throw new AssertionError("This class cannot be instantiated!");
    }
}
