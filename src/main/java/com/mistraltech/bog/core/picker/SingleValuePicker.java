package com.mistraltech.bog.core.picker;

import java.util.function.Supplier;

public class SingleValuePicker<T> implements Supplier<T> {
    private T value;

    public SingleValuePicker(T value) {
        this.value = value;
    }

    public static <T> SingleValuePicker<T> singleValuePicker(T value) {
        return new SingleValuePicker<T>(value);
    }

    @Override
    public T get() {
        return value;
    }
}
