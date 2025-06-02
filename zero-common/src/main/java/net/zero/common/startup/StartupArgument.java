package net.zero.common.startup;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class StartupArgument<T> {

    @Setter
    private T value;
    private final T defaultValue;

    public T getValue() {
        if(value == null) return defaultValue;

        return value;
    }

}
