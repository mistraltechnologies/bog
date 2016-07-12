package com.mistraltech.bog.core.picker;

public class NullValuePicker<T> implements ValuePicker<T> {

    private NullValuePicker() {
    }

    public static <T> NullValuePicker<T> nullValuePicker() {
        return new NullValuePicker<T>();
    }

    @Override
    public T pick() {
        return null;
    }
}
