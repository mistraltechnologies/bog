package com.mistraltech.bog.core;

public class PropertyBuilder<T> {
    private Builder<? extends T> valueBuilder;

    public PropertyBuilder() {
    }

    public T get() {
        return valueBuilder.build();
    }

    public T getOrDefault(T defaultValue) {
        return hasValue() ? valueBuilder.build() : defaultValue;
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
