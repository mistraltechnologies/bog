package com.mistraltech.bog.core;

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

    protected T construct() {
        throw new UnsupportedOperationException("No implementation provided");
    }

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
