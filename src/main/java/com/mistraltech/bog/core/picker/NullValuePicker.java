package com.mistraltech.bog.core.picker;

import java.util.function.Supplier;

public class NullValuePicker<T> implements Supplier<T> {

    private NullValuePicker() {
    }

    public static <T> NullValuePicker<T> nullValuePicker() {
        return new NullValuePicker<>();
    }

    @Override
    public T get() {
        return null;
    }
}
