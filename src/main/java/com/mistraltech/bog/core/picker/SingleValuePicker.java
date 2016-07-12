package com.mistraltech.bog.core.picker;

public class SingleValuePicker<T> implements ValuePicker<T> {
    private T value;

    public SingleValuePicker(T value) {
        this.value = value;
    }

    public static <T> SingleValuePicker<T> singleValuePicker(T value) {
        return new SingleValuePicker<T>(value);
    }

    @Override
    public T pick() {
        return value;
    }
}
