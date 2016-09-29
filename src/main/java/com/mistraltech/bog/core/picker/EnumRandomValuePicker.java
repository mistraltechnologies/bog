package com.mistraltech.bog.core.picker;

public class EnumRandomValuePicker<E extends Enum<E>> extends ArrayRandomValuePicker<E> {
    protected EnumRandomValuePicker(Class<E> enumClass) {
        super(enumClass.getEnumConstants());
    }

    public static <E extends Enum<E>> EnumRandomValuePicker<E> enumPicker(Class<E> enumClass) {
        return new EnumRandomValuePicker<>(enumClass);
    }
}
