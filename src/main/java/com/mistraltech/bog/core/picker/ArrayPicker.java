package com.mistraltech.bog.core.picker;

import java.util.Random;

public class ArrayPicker<T> implements Picker<T> {
    private T[] values;

    protected ArrayPicker(T[] values) {
        this.values = values;
    }

    public static <T> ArrayPicker<T> arrayPicker(T[] values) {
        return new ArrayPicker<>(values);
    }

    @Override
    public T pick() {
        int n = new Random().nextInt(values.length);
        return values[n];
    }
}
