package com.mistraltech.bog.core.picker;

public class EnumValuePicker<E extends Enum<E>> extends ArrayValuePicker<E> {
    protected EnumValuePicker(Class<E> enumClass) {
        super(enumClass.getEnumConstants());
    }

    public static <E extends Enum<E>> EnumValuePicker<E> enumPicker(Class<E> enumClass) {
        return new EnumValuePicker<>(enumClass);
    }
}
