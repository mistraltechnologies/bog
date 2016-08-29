package com.mistraltech.bog.core;

/**
 * A partially implemented builder with template methods.
 * <p>
 * The build() method is decomposed into a create() phase and an update() phase. The create()
 * phase creates a new instance by passing property values to a constructor. The update() phase
 * sets additional property values through setter methods. Dividing the build process in this way
 * allows construction of objects with cyclic references, such as two Person objects with a
 * bi-directional spouse relationship.
 * <p>
 * Subclasses are required to implement {@link AbstractBuilder#construct()}, which is called in the create phase,
 * and {@link AbstractBuilder#assign(Object)}, which is called in the update phase.
 *
 * @param <T> The type of object this builder returns.
 */
public abstract class AbstractBuilder<T> implements Builder<T> {
    private T instance;

    public final T create() {
        instance = construct();
        return instance;
    }

    public final T build() {
        create();
        return update();
    }

    public final T update() {
        if (instance == null) {
            throw new IllegalStateException("Not created");
        }

        assign(instance);

        return instance;
    }

    protected abstract T construct();

    protected abstract void assign(T instance);
}
