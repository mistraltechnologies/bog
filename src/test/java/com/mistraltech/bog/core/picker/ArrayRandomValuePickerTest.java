package com.mistraltech.bog.core.picker;


import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static com.mistraltech.bog.core.picker.ArrayRandomValuePicker.arrayRandomValuePicker;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ArrayRandomValuePickerTest {

    @Test(expected = NullPointerException.class)
    public void cannotConstructWithNullValues() {
        arrayRandomValuePicker(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotConstructWithNoValues() {
        arrayRandomValuePicker(new String[0]);
    }

    @Test
    public void picksFromSingleValueArray() {
        ArrayRandomValuePicker<String> picker = arrayRandomValuePicker(new String[]{"Bob"});

        assertThat(picker.get(), is(equalTo("Bob")));
    }

    @Test(timeout = 1000L)
    public void picksAllValuesFromMultiValueArrayEventually() {
        Integer[] values = new Integer[]{1, 2, 3, 4, 5};

        ArrayRandomValuePicker<Integer> picker = arrayRandomValuePicker(values);
        Set<Integer> remaining = new TreeSet<>(Arrays.asList(values));

        while (remaining.size() > 0) {
            remaining.remove(picker.get());
        }
    }
}