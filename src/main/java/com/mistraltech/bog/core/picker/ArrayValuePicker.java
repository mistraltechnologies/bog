package com.mistraltech.bog.core.picker;

import java.util.Random;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class ArrayValuePicker<T> implements Supplier<T> {
    private T[] values;

    protected ArrayValuePicker(T[] values) {
        requireNonNull(values);

        if (values.length == 0) {
            throw new IllegalArgumentException("Values array must not be empty");
        }

        this.values = values;
    }

    public static <T> ArrayValuePicker<T> arrayValuePicker(T[] values) {
        return new ArrayValuePicker<>(values);
    }

    @Override
    public T get() {
        int n = new Random().nextInt(values.length);
        return values[n];
    }
}
