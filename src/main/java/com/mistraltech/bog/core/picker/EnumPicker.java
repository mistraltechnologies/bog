package com.mistraltech.bog.core.picker;

public class EnumPicker<E extends Enum<E>> extends ArrayPicker<E> {
    protected EnumPicker(Class<E> enumClass) {
        super(enumClass.getEnumConstants());
    }

    public static <E extends Enum<E>> EnumPicker<E> enumPicker(Class<E> enumClass) {
        return new EnumPicker<>(enumClass);
    }
}
