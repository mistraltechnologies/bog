package com.mistraltech.bog.core.propertybuilder;

public interface PropertyValue<T> {
    T get();

    T preview();
}
