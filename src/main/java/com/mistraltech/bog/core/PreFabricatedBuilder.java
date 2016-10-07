package com.mistraltech.bog.core;

/**
 * A builder that returns the same pre-existing instance for all invocations of {@link Builder#build()}.
 * <p>
 * Useful as an adapter that allows an instance of a type to be used wherever a builder of an instance of that type
 * is expected.
 *
 * @param <T> The type of object this builder returns.
 */
public class PreFabricatedBuilder<T> implements Builder<T> {
    private T value;

    private PreFabricatedBuilder(T value) {
        this.value = value;
    }

    public static <T> PreFabricatedBuilder<T> preFabricated(T value) {
        return new PreFabricatedBuilder<>(value);
    }

    @Override
    public T build() {
        return value;
    }
}
