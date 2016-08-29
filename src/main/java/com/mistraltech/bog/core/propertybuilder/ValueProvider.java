package com.mistraltech.bog.core.propertybuilder;

/**
 * A source of values.
 * <p>
 * Callers can obtain values by calling {@link ValueProvider#take()}. Whether the same value of different values
 * are returned by each call is implementation dependent.
 * <p>
 * A caller can obtain the value that will be returned by the next call to take(), all other things being equal,
 * by calling {@link ValueProvider#preview()}.
 *
 * @param <T> the type of value provided
 */
public interface ValueProvider<T> {
    /**
     * Gets a value according to some implementation-specific algorithm.
     *
     * @return a value
     */
    T take();

    /**
     * Gets the value that will be returned by the next call to {@link ValueProvider#take()}.
     * <p>
     * Note: This method does not provide a guarantee to return the same value for all invocations between calls
     * to take(), allowing state changes in the underlying implementation to change the result. Any such guarantee would
     * therefore be implementation-specific.
     *
     * @return the next value
     */
    T preview();
}
