package com.mistraltech.bog.core.picker;

import java.util.Random;

public class ArrayValuePicker<T> implements ValuePicker<T> {
    private T[] values;

    protected ArrayValuePicker(T[] values) {
        this.values = values;
    }

    public static <T> ArrayValuePicker<T> arrayPicker(T[] values) {
        return new ArrayValuePicker<>(values);
    }

    @Override
    public T pick() {
        int n = new Random().nextInt(values.length);
        return values[n];
    }
}
