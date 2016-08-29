package com.mistraltech.bog.core.propertybuilder;

import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.PreFabricatedBuilder;
import com.mistraltech.bog.core.picker.ValuePicker;

import java.util.Objects;

import static com.mistraltech.bog.core.picker.NullValuePicker.nullValuePicker;
import static com.mistraltech.bog.core.picker.SingleValuePicker.singleValuePicker;
import static java.util.Objects.requireNonNull;

/**
 * Responsible for holding and providing a value to a caller.
 * <p>
 * The value can be obtained with a call to {@link ValueContainer#take()} and the value returned will either be a
 * value explicitly assigned or otherwise a default value.
 * <p>
 * Values are explicitly assigned with a call to {@link ValueContainer#set(Builder)}
 * or {@link ValueContainer#set(T)}.
 * <p>
 * If the assigned value is a builder, the value returned by successive calls to take() will be the result of calling
 * the build() method of the builder.
 * <p>
 * The default value returned by a ValueContainer will be chosen by the 'default picker'. A default picker can be
 * assigned when the ValueContainer is constructed either by passing a ValuePicker instance or by passing a
 * default value. A default picker can also be assigned after construction by calling
 * {@link ValueContainer#setDefault(ValuePicker)}, {@link ValueContainer#setDefault(T)} or
 * {@link ValueContainer#setDefaultNull()}. If no default picker is assigned, a default value of null is used.
 * <p>
 * If the default picker has a randomised algorithm for choosing a value, the caller may get a different result
 * each time take() is called. There are scenarios where it is desireable
 * to know what value will be used in advance, such as when the default value of one property depends on the
 * default value of another property. To accommodate this, the {@link ValueContainer#preview()} method will
 * return the value that will be returned by the next call to take().
 * Once the take() method has been called the value returned by preview() may change. Furthermore, assigning a value
 * with set() will cause that to be the value returned by preview().
 *
 * @param <T> the type of value to be supplied
 */
public final class ValueContainer<T> implements ValueProvider<T> {
    private Builder<? extends T> valueBuilder;

    private ValuePicker<? extends T> defaultPicker;

    private boolean preEvaluated;

    private T previewValue;

    private ValueContainer(ValuePicker<? extends T> defaultPicker) {
        this.defaultPicker = defaultPicker;
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
     * Gets the assigned value if a value or builder has been assigned, otherwise gets a default value using the
     * assigned default picker. Since the default picker may return a different value for successive calls, so too
     * may this method.
     *
     * @return a value
     */
    @Override
    public T take() {
        final T value;

        if (preEvaluated) {
            value = previewValue;
            clearPreviewValue();
        } else if (hasValue()) {
            value = valueBuilder.build();
        } else {
            value = defaultPicker.pick();
        }

        return value;
    }

    /**
     * Gets the value that will be returned by the next call to take(). Note that this value can change as a result
     * of assigning a new value by a call to either a set() method or a setDefault() method.
     *
     * @return a value, expected to be the next value returned by take().
     */
    public T preview() {
        if (!preEvaluated) {
            previewValue = evaluate();
            preEvaluated = true;
        }
        return previewValue;
    }

    private void clearPreviewValue() {
        preEvaluated = false;
        previewValue = null;
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
        return this;
    }

    /**
     * Assign a value for this container.
     *
     * @param value the value
     * @return reference to self
     */
    public ValueContainer<T> set(T value) {
        this.valueBuilder = new PreFabricatedBuilder<T>(value);
        clearPreviewValue();
        return this;
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
            clearPreviewValue();
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
     * Sets the default value to null.
     *
     * @return reference to self
     */
    public ValueContainer<T> setDefaultNull() {
        return setDefault(nullValuePicker());
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
