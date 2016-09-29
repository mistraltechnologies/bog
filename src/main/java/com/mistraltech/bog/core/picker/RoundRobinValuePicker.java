package com.mistraltech.bog.core.picker;

import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class RoundRobinValuePicker<T> implements Supplier<T> {

    private final T[] values;

    private int index = 0;

    private RoundRobinValuePicker(T[] values) {
        requireNonNull(values);

        if (values.length == 0) {
            throw new IllegalArgumentException("Values array must not be empty");
        }

        this.values = values;
    }

    public static <T> RoundRobinValuePicker<T> roundRobinValuePicker(T[] values) {
        return new RoundRobinValuePicker<>(values);
    }

    @Override
    public T get() {
        T value = values[index];
        index = (index + 1) % values.length;
        return value;
    }
}
