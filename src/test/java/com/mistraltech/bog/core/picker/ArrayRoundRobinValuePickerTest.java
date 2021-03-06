package com.mistraltech.bog.core.picker;

import org.junit.Test;

import static com.mistraltech.bog.core.picker.ArrayRoundRobinValuePicker.arrayRoundRobinValuePicker;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ArrayRoundRobinValuePickerTest {

    @Test(expected = NullPointerException.class)
    public void cannotConstructWithNullValues() {
        arrayRoundRobinValuePicker(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotConstructWithNoValues() {
        arrayRoundRobinValuePicker(new String[0]);
    }

    @Test
    public void picksAllValuesInSequence() {
        Integer[] values = new Integer[]{0, 1, 2, 3, 4};

        ArrayRoundRobinValuePicker<Integer> picker = arrayRoundRobinValuePicker(values);

        for (int i = 0; i < 10; i++) {
            assertThat(picker.get(), is(i % 5));
        }
    }
}