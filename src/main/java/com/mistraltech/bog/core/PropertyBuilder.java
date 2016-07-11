package com.mistraltech.bog.core;

import com.mistraltech.bog.core.picker.Picker;

public class PropertyBuilder<T> {
    private Builder<? extends T> valueBuilder;

    public PropertyBuilder() {
    }

    public T get() {
        return valueBuilder.build();
    }

    public T getOrDefault(T defaultValue) {
        return hasValue() ? get() : defaultValue;
    }

    public T getOrNull() {
        return hasValue() ? get() : null;
    }

    public T getOrDefault(Picker<T> picker) {
        return hasValue() ? get() : picker.pick();
    }

    public void set(Builder<? extends T> builder) {
        this.valueBuilder = builder;
    }

    public void set(T value) {
        this.valueBuilder = new PreFabricatedBuilder<T>(value);
    }

    public boolean hasValue() {
        return valueBuilder != null;
    }

}
