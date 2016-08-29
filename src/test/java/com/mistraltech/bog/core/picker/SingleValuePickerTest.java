package com.mistraltech.bog.core.picker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

public class SingleValuePickerTest {

    @Test
    public void canConstructWithNull() {
        // Don't do this. Use NullValuePicker instead.
        SingleValuePicker<Object> picker = SingleValuePicker.singleValuePicker(null);

        assertThat(picker.pick(), is(nullValue()));
    }

    @Test
    public void returnsConstructorParameter() {
        Object obj = new Object();

        SingleValuePicker<Object> picker = SingleValuePicker.singleValuePicker(obj);

        assertThat(picker.pick(), is(sameInstance(obj)));
    }
}