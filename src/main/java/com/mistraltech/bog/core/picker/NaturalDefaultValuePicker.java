package com.mistraltech.bog.core.picker;

import java.util.HashMap;
import java.util.function.Supplier;

public class NaturalDefaultValuePicker<T> implements Supplier<T> {
    private static final HashMap<Class<?>, Object> PRIMITIVES_TO_DEFAULTS = new HashMap<>();

    static {
        PRIMITIVES_TO_DEFAULTS.put(boolean.class, Boolean.FALSE);
        PRIMITIVES_TO_DEFAULTS.put(byte.class, (byte) 0);
        PRIMITIVES_TO_DEFAULTS.put(char.class, (char) 0);
        PRIMITIVES_TO_DEFAULTS.put(double.class, 0D);
        PRIMITIVES_TO_DEFAULTS.put(float.class, 0F);
        PRIMITIVES_TO_DEFAULTS.put(int.class, 0);
        PRIMITIVES_TO_DEFAULTS.put(long.class, 0L);
        PRIMITIVES_TO_DEFAULTS.put(short.class, (short) 0);
    }

    private final T value;

    @SuppressWarnings("unchecked")
    private static <T> T getDefaultForPrimitive(Class<T> clazz) {
        return (T) PRIMITIVES_TO_DEFAULTS.get(clazz);
    }

    private NaturalDefaultValuePicker(Class<T> clazz) {
        value = getDefaultForPrimitive(clazz);
    }

    public static <T> NaturalDefaultValuePicker<T> naturalDefault(Class<T> clazz) {
        return new NaturalDefaultValuePicker<>(clazz);
    }

    @Override
    public T get() {
        return value;
    }
}
