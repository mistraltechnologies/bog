package com.mistraltech.bog.core.picker;

import java.util.Iterator;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class IteratingValuePicker<T> implements Supplier<T> {

    private final Iterable<T> iterable;

    private Iterator<T> iterator;

    private IteratingValuePicker(Iterable<T> iterable) {
        requireNonNull(iterable);

        this.iterable = iterable;
        iterator = iterable.iterator();
    }

    public static <T> IteratingValuePicker<T> iteratingValuePicker(Iterable<T> iterable) {
        return new IteratingValuePicker<>(iterable);
    }

    @Override
    public T get() {
        if (! iterator.hasNext()) {
            iterator = iterable.iterator();

            if (! iterator.hasNext()) {
                throw new IllegalStateException("Cannot iterate");
            }
        }

        return iterator.next();
    }
}
