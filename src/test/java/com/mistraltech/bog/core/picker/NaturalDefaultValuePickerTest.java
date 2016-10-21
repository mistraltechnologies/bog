package com.mistraltech.bog.core.picker;

import org.junit.Test;

import static com.mistraltech.bog.core.picker.NaturalDefaultValuePicker.naturalDefault;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class NaturalDefaultValuePickerTest {
    @Test
    public void getReturnsFalseForPrimitiveBooleanTypeWithoutDefault() {
        NaturalDefaultValuePicker<Boolean> picker = naturalDefault(boolean.class);

        assertThat(picker.get(), is(false));
    }

    @Test
    public void getReturnsFalseForPrimitiveByteTypeWithoutDefault() {
        NaturalDefaultValuePicker<Byte> picker = naturalDefault(byte.class);

        assertThat(picker.get(), is((byte) 0));
    }

    @Test
    public void getReturnsFalseForPrimitiveCharTypeWithoutDefault() {
        NaturalDefaultValuePicker<Character> picker = naturalDefault(char.class);

        assertThat(picker.get(), is((char) 0));
    }

    @Test
    public void getReturnsFalseForPrimitiveDoubleTypeWithoutDefault() {
        NaturalDefaultValuePicker<Double> picker = naturalDefault(double.class);

        assertThat(picker.get(), is(0D));
    }

    @Test
    public void getReturnsFalseForPrimitiveFloatTypeWithoutDefault() {
        NaturalDefaultValuePicker<Float> picker = naturalDefault(float.class);

        assertThat(picker.get(), is(0F));
    }

    @Test
    public void getReturnsFalseForPrimitiveIntTypeWithoutDefault() {
        NaturalDefaultValuePicker<Integer> picker = naturalDefault(int.class);

        assertThat(picker.get(), is(0));
    }

    @Test
    public void getReturnsFalseForPrimitiveLongTypeWithoutDefault() {
        NaturalDefaultValuePicker<Long> picker = naturalDefault(long.class);

        assertThat(picker.get(), is(0L));
    }

    @Test
    public void getReturnsFalseForPrimitiveShortTypeWithoutDefault() {
        NaturalDefaultValuePicker<Short> picker = naturalDefault(short.class);

        assertThat(picker.get(), is((short) 0));
    }

    @Test
    public void getReturnsNullForObject() {
        NaturalDefaultValuePicker<Object> picker = naturalDefault(Object.class);

        assertThat(picker.get(), is(nullValue()));
    }

}
