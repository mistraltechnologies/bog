package com.mistraltech.bog.core.picker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mistraltech.bog.core.picker.ArrayRoundRobinValuePicker.arrayRoundRobinValuePicker;
import static com.mistraltech.bog.core.picker.IteratingValuePicker.iteratingValuePicker;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IteratingValuePickerTest {

    @Test(expected = NullPointerException.class)
    public void cannotConstructWithNullValues() {
        iteratingValuePicker(null);
    }

    @Test
    public void picksAllValuesInSequence() {
        Iterable<Integer> intIterable = Arrays.asList(1, 2, 3, 4);

        IteratingValuePicker<Integer> picker = iteratingValuePicker(intIterable);

        for (int i = 1; i <= 4; i++) {
            assertThat(picker.get(), is(i));
        }
    }

    @Test
    public void createsNewIteratorWhenExhausted() {
        Iterable<Integer> intIterable = Arrays.asList(1, 2, 3, 4);

        IteratingValuePicker<Integer> picker = iteratingValuePicker(intIterable);

        for (int i = 1; i <= 4; i++) {
            picker.get();
        }

        for (int i = 1; i <= 4; i++) {
            assertThat(picker.get(), is(i));
        }
    }
}