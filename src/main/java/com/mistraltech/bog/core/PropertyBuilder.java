package com.mistraltech.bog.core;

import static com.mistraltech.bog.core.PreFabricatedBuilder.preFabricated;

public class PropertyBuilder<T> {
    private Builder<? extends T> valueBuilder;

    public PropertyBuilder() {
    }

    public T get() {
        return getOrDefault(null);
    }

    public T getOrDefault(T defaultValue) {
        return hasValue() ? valueBuilder.build() : defaultValue;
    }

    public void set(Builder<? extends T> builder) {
        this.valueBuilder = builder;
    }

    public void set(T value) {
        this.valueBuilder = preFabricated(value);
    }

    public boolean hasValue() {
        return valueBuilder != null;
    }
}
