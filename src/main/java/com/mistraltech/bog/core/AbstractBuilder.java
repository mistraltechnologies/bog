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
public abstract class AbstractBuilder<T> implements TwoPhaseBuilder<T> {

    private T instance;

    /**
     * Creates a newly constructed instance of type T.
     * <p>
     * This method is equivalent to calling create() followed by update().
     *
     * @return a fully constructed instance of T
     */
    @Override
    public final T build() {
        create();
        return update();
    }

    /**
     * Creates an instance of type T but does not invoke any setters. The reference to the most recently created
     * instance is retained and used in a subsequent call to update().
     *
     * @return a new instance of T
     */
    @Override
    public final T create() {
        instance = construct();
        return instance;
    }

    /**
     * Invokes the property setters on the most recently created instance.
     *
     * @return the most recently created instance of T
     */
    @Override
    public final T update() {
        if (instance == null) {
            throw new IllegalStateException("Not created");
        }

        assign(instance);

        return instance;
    }

    /**
     * Template method for constructing a new instance of T. Subclasses should implement this method by invoking an
     * appropriate constructor and passing values managed by the builder.
     *
     * @return a new instance of T
     */
    protected abstract T construct();

    /**
     * Template method for invoking property setters on an instance of T. Subclasses should implement this method
     * by calling all property setters with values managed by the builder.
     *
     * @param instance the instance to update
     */
    protected abstract void assign(T instance);
}
