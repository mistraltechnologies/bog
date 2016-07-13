package com.mistraltech.bog.core.picker;

import java.util.Random;

public class ArrayValuePicker<T> extends CachedValuePicker<T> {
    private T[] values;

    protected ArrayValuePicker(T[] values) {
        this.values = values;
    }

    public static <T> ArrayValuePicker<T> arrayPicker(T[] values) {
        return new ArrayValuePicker<>(values);
    }

    @Override
    protected T pickOnce() {
        int n = new Random().nextInt(values.length);
        return values[n];
    }
}
