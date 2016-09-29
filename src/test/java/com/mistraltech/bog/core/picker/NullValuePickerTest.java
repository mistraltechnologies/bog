package com.mistraltech.bog.core.picker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class NullValuePickerTest {

    @Test
    public void picksNull() {
        NullValuePicker<Object> picker = NullValuePicker.nullValuePicker();

        assertThat(picker.get(), is(nullValue()));
    }
}