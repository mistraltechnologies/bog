package com.mistraltech.bog.core.picker;

import static java.util.Objects.requireNonNull;

public class RoundRobinValuePicker<T> implements ValuePicker<T> {

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
    public T pick() {
        T value = values[index];
        index = (index + 1) % values.length;
        return value;
    }
}
