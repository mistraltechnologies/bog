package com.mistraltech.bog.core.propertybuilder;

import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.PreFabricatedBuilder;
import com.mistraltech.bog.core.picker.ValuePicker;

import static com.mistraltech.bog.core.picker.NullValuePicker.nullValuePicker;
import static com.mistraltech.bog.core.picker.SingleValuePicker.singleValuePicker;

public final class PropertyBuilder<T> implements PropertyValue<T> {
    private Builder<? extends T> valueBuilder;

    private ValuePicker<? extends T> defaultPicker;

    private PropertyBuilder(ValuePicker<? extends T> defaultPicker) {
        this.defaultPicker = defaultPicker;
    }

    private boolean preEvaluated;

    private T previewValue;

    public static <T> PropertyBuilder<T> propertyBuilder(ValuePicker<? extends T> defaultPicker) {
        return new PropertyBuilder<>(defaultPicker);
    }

    public static <T> PropertyBuilder<T> propertyBuilder(T defaultValue) {
        return propertyBuilder(singleValuePicker(defaultValue));
    }

    public static <T> PropertyBuilder<T> propertyBuilder() {
        return propertyBuilder(nullValuePicker());
    }

    @Override
    public T get() {
        final T value;

        if (preEvaluated) {
            value = previewValue;
            preEvaluated = false;
            previewValue = null;
        } else if (hasValue()) {
            value = valueBuilder.build();
        } else {
            value = defaultPicker.pick();
        }

        return value;
    }

    public T preview() {
        if (!preEvaluated) {
            previewValue = evaluate();
            preEvaluated = true;
        }
        return previewValue;
    }

    private T evaluate() {
        return hasValue() ? valueBuilder.build() : defaultPicker.pick();
    }

    public PropertyBuilder<T> set(Builder<? extends T> builder) {
        this.valueBuilder = builder;
        return this;
    }

    public PropertyBuilder<T> set(T value) {
        this.valueBuilder = new PreFabricatedBuilder<T>(value);
        preEvaluated = false;
        previewValue = null;
        return this;
    }

    public PropertyBuilder<T> setDefault(ValuePicker<? extends T> defaultPicker) {
        this.defaultPicker = defaultPicker;
        return this;
    }

    public PropertyBuilder<T> setDefault(T defaultValue) {
        return setDefault(singleValuePicker(defaultValue));
    }

    public PropertyBuilder<T> setDefaultNull() {
        return setDefault(nullValuePicker());
    }

    public boolean hasValue() {
        return valueBuilder != null;
    }

}
