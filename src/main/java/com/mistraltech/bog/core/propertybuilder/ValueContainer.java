package com.mistraltech.bog.core.propertybuilder;

import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.picker.ValuePicker;

import java.util.HashMap;

import static com.mistraltech.bog.core.PreFabricatedBuilder.preFabricated;
import static com.mistraltech.bog.core.picker.NullValuePicker.nullValuePicker;
import static com.mistraltech.bog.core.picker.SingleValuePicker.singleValuePicker;
import static java.util.Objects.requireNonNull;

/**
 * Responsible for holding and providing a value to a caller.
 * <p>
 * The value can be obtained with a call to {@link ValueContainer#get()} and the value returned will be either a
 * value explicitly assigned or otherwise a default value.
 * <p>
 * Values are explicitly assigned with a call to {@link ValueContainer#set(Builder)}
 * or {@link ValueContainer#set(T)}.
 * <p>
 * If the assigned value is a builder, the value returned by a call to get() will be the result of calling
 * the build() method of the builder. Successive calls to get() will not cause new instances to be built.
 * Instead the value is cached and this cached value returned each time. However, assigning a new value or
 * builder or calling {@link ValueContainer#reset()} will cause
 * the cached value to be discarded. A subsequent call to get() will then re-evaluate the result.
 * <p>
 * The default value returned by a ValueContainer will be chosen by the 'default picker'. A default picker can be
 * assigned when the ValueContainer is constructed either by passing a ValuePicker instance or by passing a
 * default value. A default picker can also be assigned after construction by calling
 * {@link ValueContainer#setDefault(ValuePicker)}, {@link ValueContainer#setDefault(T)}. If no default picker
 * is assigned, a default value of null is used for
 * object types and the natural java default values are used for primitive types.
 * <p>
 * Successive calls to get() will not cause a new default to be picked. However, assigning a new default picker or
 * default value or calling postUpdate() will cause the cached default value to be discarded. A subsequent call to get()
 * will re-evaluate the default value.
 *
 * @param <T> the type of value to be supplied
 */
public final class ValueContainer<T> {
    private static final HashMap<Class<?>, Object> PRIMITIVES_TO_DEFAULTS = new HashMap<>();

    private Builder<? extends T> valueBuilder;

    private ValuePicker<? extends T> defaultPicker;

    private boolean evaluated;

    private T cachedValue;

    private ValueContainer(ValuePicker<? extends T> defaultPicker) {
        this.defaultPicker = defaultPicker;
    }

    static {
        PRIMITIVES_TO_DEFAULTS.put(boolean.class, Boolean.FALSE);
        PRIMITIVES_TO_DEFAULTS.put(byte.class, (byte) 0);
        PRIMITIVES_TO_DEFAULTS.put(char.class, (char) 0);
        PRIMITIVES_TO_DEFAULTS.put(double.class, 0D);
        PRIMITIVES_TO_DEFAULTS.put(float.class, 0F);
        PRIMITIVES_TO_DEFAULTS.put(int.class, 0);
        PRIMITIVES_TO_DEFAULTS.put(long.class, 0L);
        PRIMITIVES_TO_DEFAULTS.put(short.class, (short) 0);
    }

    /**
     * Factory method returning an instance with an assigned default picker.
     *
     * @param defaultPicker the default picker
     * @param <T>           the type of value to be supplied
     * @return an instance configured with the supplied default picker
     */
    public static <T> ValueContainer<T> valueContainer(ValuePicker<? extends T> defaultPicker) {
        return new ValueContainer<>(requireNonNull(defaultPicker));
    }

    /**
     * Factory method returning an instance with an assigned default value.
     *
     * @param defaultValue the default value
     * @param <T>          the type of value to be supplied
     * @return an instance configured with the supplied default value
     */
    public static <T> ValueContainer<T> valueContainer(T defaultValue) {
        return valueContainer(singleValuePicker(requireNonNull(defaultValue)));
    }

    /**
     * Factory method returning an instance without an assigned default picker or default value. The instance
     * will use null as its default value.
     *
     * @param <T> the type of value to be supplied
     * @return an instance with a default value of null
     */
    public static <T> ValueContainer<T> valueContainer() {
        return valueContainer(nullValuePicker());
    }

    /**
     * Factory method returning an instance with an assigned default picker that picks a value
     * appropriate to the supplied class. If the class is a primitive, the natural default value of
     * the primitive is returned. If the class is an object type, the default will be null.
     *
     * @param <T> the type of value to be supplied
     * @return an instance with a default value of null
     */
    public static <T> ValueContainer<T> valueContainer(Class<T> clazz) {
        ValuePicker<T> defaultPicker;

        if (clazz.isPrimitive()) {
            defaultPicker = singleValuePicker(getDefaultForPrimitive(clazz));
        } else {
            defaultPicker = nullValuePicker();
        }

        return valueContainer(defaultPicker);
    }

    private static <T> T getDefaultForPrimitive(Class<T> clazz) {
        return (T) PRIMITIVES_TO_DEFAULTS.get(clazz);
    }

    /**
     * Gets the assigned value if a value or builder has been assigned, otherwise gets a default value using the
     * assigned default picker. Since the default picker may return a different value for successive calls, so too
     * may this method.
     *
     * @return a value
     */
    public T get() {
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
        return hasValue() ? valueBuilder.build() : defaultPicker.pick();
    }

    /**
     * Assign a builder that will provide the value for this container.
     *
     * @param builder the value builder
     * @return reference to self
     */
    public ValueContainer<T> set(Builder<? extends T> builder) {
        this.valueBuilder = builder;
        clearCachedValue();
        return this;
    }

    /**
     * Assign a value for this container.
     *
     * @param value the value
     * @return reference to self
     */
    public ValueContainer<T> set(T value) {
        return set(preFabricated(value));
    }

    /**
     * Assigns a default picker strategy for choosing a default value.
     *
     * @param defaultPicker the default picker strategy
     * @return reference to self
     */
    public ValueContainer<T> setDefault(ValuePicker<? extends T> defaultPicker) {
        this.defaultPicker = defaultPicker;

        if (!hasValue()) {
            // Only clear the cached value if it is a default value
            clearCachedValue();
        }

        return this;
    }

    /**
     * Assigns a default picker strategy that returns only the supplied default value.
     *
     * @param defaultValue the new default value
     * @return reference to self
     */
    public ValueContainer<T> setDefault(T defaultValue) {
        return setDefault(singleValuePicker(defaultValue));
    }

    /**
     * Whether an explicit value has been assigned.
     *
     * @return true if an explicit value has been assigned; false otherwise.
     */
    public boolean hasValue() {
        return valueBuilder != null;
    }

}
