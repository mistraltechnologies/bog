package com.mistraltech.bog.core;

import com.mistraltech.bog.core.picker.NaturalDefaultValuePicker;

import java.util.HashMap;
import java.util.function.Supplier;

import static com.mistraltech.bog.core.PreFabricatedBuilder.preFabricated;
import static com.mistraltech.bog.core.picker.NaturalDefaultValuePicker.naturalDefault;
import static com.mistraltech.bog.core.picker.NullValuePicker.nullValuePicker;
import static com.mistraltech.bog.core.picker.SingleValuePicker.singleValuePicker;
import static java.util.Objects.requireNonNull;

/**
 * Responsible for holding and providing a value to a caller.
 * <p>
 * The value can be obtained with a call to {@link ValueContainer#value()} and the value returned will be either a
 * value explicitly assigned or otherwise a default value.
 * <p>
 * Values are explicitly assigned with a call to {@link ValueContainer#set(Builder)}
 * or {@link ValueContainer#set(T)}.
 * <p>
 * If the assigned value is a builder, the value returned by a call to value() will be the result of calling
 * the build() method on the builder. The value is then cached, so successive calls to value() will return the same
 * instance. Subsequently assigning a new value or builder or calling {@link ValueContainer#reset()} will cause
 * the cached value to be discarded, causing a subsequent call to value() to re-evaluate the result.
 * <p>
 * The default value returned by a ValueContainer will be chosen by the 'default picker'. A default picker can be
 * assigned when the ValueContainer is constructed either by passing a ValuePicker instance or by passing a
 * default value. A default picker can also be assigned after construction by calling
 * {@link ValueContainer#setDefault(Supplier)}, {@link ValueContainer#setDefault(T)}. If no default picker
 * is assigned, a default value of null is used for
 * object types and the natural java default values are used for primitive types.
 * <p>
 * Successive calls to value() will not cause a new default to be picked. However, assigning a new default picker or
 * default value or calling reset() (such as from a {@link AbstractBuilder#postUpdate()} method) will cause the
 * cached default value to be discarded. A subsequent call to value() will re-evaluate the default value.
 * <p>
 * Implements the BuilderProperty interface. Builders should avoid exposing instances as the ValueContainer type,
 * instead exposing instances as BuilderProperty.
 *
 * @param <T> the type of value to be supplied
 */
public class ValueContainer<T> implements BuilderProperty<T> {
    private Builder<? extends T> valueBuilder;

    private Supplier<? extends T> defaultPicker;

    private boolean evaluated;

    private T cachedValue;

    /**
     * Constructs an instance with a Supplier for providing the default value.
     *
     * @param defaultPicker the default value Supplier
     */
    public ValueContainer(Supplier<? extends T> defaultPicker) {
        this.defaultPicker = defaultPicker;
    }

    /**
     * Constructs an instance with a specified default value.
     *
     * @param defaultValue the default value
     */
    public ValueContainer(T defaultValue) {
        this(singleValuePicker(requireNonNull(defaultValue)));
    }

    /**
     * Constructs an instance that uses null as its default value.
     */
    public ValueContainer() {
        this(nullValuePicker());
    }

    /**
     * Constructs an instance that uses the natural default value for primitive types and null for object types.
     *
     * @param clazz the class of property
     */
    public ValueContainer(Class<T> clazz) {
        this(naturalDefault(clazz));
    }

    /**
     * Gets the effective value. The effective value is the assigned value if a value or has been assigned or builder
     * result if a builder has been assigned; otherwise gets a default value using the
     * assigned default picker. The same value will be returned by consecutive calls unless the assigned value or
     * value builder is changed or the default value picker is changed or reset() is invoked.
     *
     * @return a value
     */
    @Override
    public T value() {
        if (!evaluated) {
            cachedValue = evaluate();
            evaluated = true;
        }

        return cachedValue;
    }

    public void reset() {
        clearCachedValue();
    }

    private void clearCachedValue() {
        evaluated = false;
        cachedValue = null;
    }

    private T evaluate() {
        return isAssigned() ? valueBuilder.build() : defaultPicker.get();
    }

    /**
     * Assign a builder that will provide the value for this container.
     *
     * @param builder the value builder
     */
    public void set(Builder<? extends T> builder) {
        this.valueBuilder = builder;
        clearCachedValue();
    }

    /**
     * Assign a value for this container.
     *
     * @param value the value
     */
    public void set(T value) {
        set(preFabricated(value));
    }

    /**
     * Assigns a default picker strategy for choosing a default value.
     *
     * @param defaultPicker the default picker strategy
     */
    @Override
    public void setDefault(Supplier<? extends T> defaultPicker) {
        this.defaultPicker = defaultPicker;

        if (!isAssigned()) {
            // Only clear the cached value if it is a default value
            clearCachedValue();
        }
    }

    /**
     * Assigns a default picker strategy that returns only the supplied default value.
     *
     * @param defaultValue the new default value
     */
    @Override
    public void setDefault(T defaultValue) {
        setDefault(singleValuePicker(defaultValue));
    }

    /**
     * Whether an explicit value (or value builder) has been assigned.
     *
     * @return true if an explicit value has been assigned; false otherwise.
     */
    @Override
    public boolean isAssigned() {
        return valueBuilder != null;
    }

}
