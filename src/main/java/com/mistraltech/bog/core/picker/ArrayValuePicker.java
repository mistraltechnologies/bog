package com.mistraltech.bog.core.picker;

import java.util.Random;

import static java.util.Objects.requireNonNull;

public class ArrayValuePicker<T> implements ValuePicker<T> {
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
    public T pick() {
        int n = new Random().nextInt(values.length);
        return values[n];
    }
}
