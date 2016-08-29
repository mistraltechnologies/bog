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

    protected T getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Not constructed");
        }

        return instance;
    }

    protected void setInstance(T instance) {
        this.instance = instance;
    }

    protected abstract T construct();

    public final T create() {
        setInstance(construct());
        return instance;
    }

    public final T build() {
        create();
        update();
        return getInstance();
    }

    public final T update() {
        assign(instance);
        return instance;
    }

    protected abstract void assign(T instance);
}
