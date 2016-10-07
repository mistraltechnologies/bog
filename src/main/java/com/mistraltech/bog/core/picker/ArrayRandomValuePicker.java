package com.mistraltech.bog.core.picker;

import java.util.Random;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class ArrayRandomValuePicker<T> implements Supplier<T> {
    private T[] values;
    private Random random = new Random();

    protected ArrayRandomValuePicker(T[] values) {
        requireNonNull(values);

        if (values.length == 0) {
            throw new IllegalArgumentException("Values array must not be empty");
        }

        this.values = values;
    }

    public static <T> ArrayRandomValuePicker<T> arrayRandomValuePicker(T[] values) {
        return new ArrayRandomValuePicker<>(values);
    }

    @Override
    public T get() {
        int n = random.nextInt(values.length);
        return values[n];
    }
}
