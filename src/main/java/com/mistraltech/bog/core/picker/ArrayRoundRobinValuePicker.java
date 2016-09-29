package com.mistraltech.bog.core.picker;

import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class ArrayRoundRobinValuePicker<T> implements Supplier<T> {

    private final T[] values;

    private int index = 0;

    private ArrayRoundRobinValuePicker(T[] values) {
        requireNonNull(values);

        if (values.length == 0) {
            throw new IllegalArgumentException("Values array must not be empty");
        }

        this.values = values;
    }

    public static <T> ArrayRoundRobinValuePicker<T> roundRobinValuePicker(T[] values) {
        return new ArrayRoundRobinValuePicker<>(values);
    }

    @Override
    public T get() {
        T value = values[index];
        index = (index + 1) % values.length;
        return value;
    }
}
