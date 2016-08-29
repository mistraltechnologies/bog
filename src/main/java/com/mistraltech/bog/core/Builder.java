package com.mistraltech.bog.core;

/**
 * Responsible for constructing an instance of some type.
 * <p>
 * A builder will typically provide a fluent API for configuring the instance with property values
 * before it is constructed. Where a property value has been left unspecified, the builder will typically
 * apply a sensible default.
 *
 * @param <T> The type of object this builder returns.
 */
public interface Builder<T> {
    /**
     * Creates a newly constructed instance of type T.
     *
     * @return instance of T
     */
    T build();
}
