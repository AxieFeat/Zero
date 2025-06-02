package net.zero.common.startup;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class ArgumentBuilder {

    private final Map<String, StartupArgument> arguments = new HashMap<>();

    public ArgumentBuilder build(String[] args) {
        return this;
    }

    public <T> ArgumentBuilder add(String key, T defaultValue) {
        arguments.put(key, new StartupArgument<>(defaultValue));
        return this;
    }

    public StartupArgument get(String key) {
        return arguments.get(key);
    }

}
