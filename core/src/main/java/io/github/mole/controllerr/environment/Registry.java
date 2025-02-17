package io.github.mole.controllerr.environment;

import java.util.HashMap;
import java.util.Map;

public class Registry {
    static final Map<Class<?>, Object> registry = new HashMap<>();
    public Registry() {}

    public <T> void register(Class<T> clazz, T instance) {
        registry.put(clazz, instance);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(Class<T> clazz) {
        return (T) registry.get(clazz);
    }
}
