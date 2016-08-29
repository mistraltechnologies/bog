package com.mistraltech.bog.core.picker;


import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static com.mistraltech.bog.core.picker.ArrayValuePicker.arrayValuePicker;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ArrayValuePickerTest {

    @Test(expected = NullPointerException.class)
    public void cannotConstructWithNullValues() {
        arrayValuePicker(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotConstructWithNoValues() {
        arrayValuePicker(new String[0]);
    }

    @Test
    public void picksFromSingleValueArray() {
        ArrayValuePicker<String> picker = arrayValuePicker(new String[]{"Bob"});

        assertThat(picker.pick(), is(equalTo("Bob")));
    }

    @Test(timeout = 1000L)
    public void picksAllValuesFromMultiValueArrayEventually() {
        Integer[] values = new Integer[]{1, 2, 3, 4, 5};

        ArrayValuePicker<Integer> picker = arrayValuePicker(values);
        Set<Integer> remaining = new TreeSet<>(Arrays.asList(values));

        while (remaining.size() > 0) {
            remaining.remove(picker.pick());
        }
    }
}