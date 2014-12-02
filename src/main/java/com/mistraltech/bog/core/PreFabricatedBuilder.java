package com.mistraltech.bog.core;

public class PreFabricatedBuilder<T> implements Builder<T> {
    private T value;

    public PreFabricatedBuilder(T value) {
        this.value = value;
    }

    public static <T> PreFabricatedBuilder preFabricated(T value)
    {
        return new PreFabricatedBuilder<T>(value);
    }

    @Override
    public T build() {
        return value;
    }
}
