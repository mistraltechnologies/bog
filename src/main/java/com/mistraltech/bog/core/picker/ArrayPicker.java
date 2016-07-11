package com.mistraltech.bog.core.picker;

import java.util.Random;

public class ArrayPicker<T> implements Picker<T> {
    private T[] values;

    public ArrayPicker(T[] values) {
        this.values = values;
    }

    @Override
    public T pick() {
        int n = new Random().nextInt(values.length);
        return values[n];
    }
}
