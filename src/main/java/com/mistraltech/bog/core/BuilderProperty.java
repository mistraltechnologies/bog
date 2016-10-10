package com.mistraltech.bog.core;

import java.util.function.Supplier;

public interface BuilderProperty<T> {
    T value();

    void setDefault(Supplier<? extends T> defaultPicker);

    void setDefault(T defaultValue);

    boolean isAssigned();
}
