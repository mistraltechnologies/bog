package com.mistraltech.bog.core;

import com.mistraltech.bog.core.picker.ValuePicker;

import static com.mistraltech.bog.core.picker.NullValuePicker.nullValuePicker;
import static com.mistraltech.bog.core.picker.SingleValuePicker.singleValuePicker;

public final class PropertyBuilder<T> {
    private Builder<? extends T> valueBuilder;
    private ValuePicker<? extends T> defaultPicker;

    private PropertyBuilder(ValuePicker<? extends T> defaultPicker) {
        this.defaultPicker = defaultPicker;
    }

    public static <T> PropertyBuilder<T> propertyBuilder(ValuePicker<? extends T> defaultPicker) {
        return new PropertyBuilder<>(defaultPicker);
    }

    public static <T> PropertyBuilder<T> propertyBuilder(T defaultValue) {
        return new PropertyBuilder<>(singleValuePicker(defaultValue));
    }

    public static <T> PropertyBuilder<T> propertyBuilder() {
        return new PropertyBuilder<>(nullValuePicker());
    }

    public T get() {
        return hasValue() ? valueBuilder.build() : defaultPicker.pick();
    }

    public PropertyBuilder<T> set(Builder<? extends T> builder) {
        this.valueBuilder = builder;
        return this;
    }

    public PropertyBuilder<T> set(T value) {
        this.valueBuilder = new PreFabricatedBuilder<T>(value);
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
