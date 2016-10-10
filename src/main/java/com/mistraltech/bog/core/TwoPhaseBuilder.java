package com.mistraltech.bog.core;

/**
 * A builder that allows finer-grained control over the build process by breaking it up into a create phase and
 * an update phase. The create phase maps to object construction and the update phase maps to mutator invocation.
 *
 * @param <T> type of object to be built
 */
public interface TwoPhaseBuilder<T> extends Builder<T> {

    T create();

    T update();
}
