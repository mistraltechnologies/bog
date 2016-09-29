package com.mistraltech.bog.core.picker;

import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class IntegerRandomValuePickerTest {

    @Test(expected = IllegalArgumentException.class)
    public void cannotConstructWithMaxValueLessThanMinValue() {
        IntegerRandomValuePicker.integerValuePicker(2, 1);
    }

    @Test
    public void canConstructWithMinValueEqualToMaxValue() {
        IntegerRandomValuePicker picker = IntegerRandomValuePicker.integerValuePicker(-3, -3);

        assertThat(picker.get(), is(equalTo(-3)));
    }

    @Test(timeout = 1000L)
    public void picksAllValuesInRangeEventually() {
        Set<Integer> remaining = new TreeSet<>();
        final int elements = 10;
        final int minValue = 5;
        final int maxValue = minValue + elements - 1;

        for (int i = 0; i < elements; i++) {
            remaining.add(i + minValue);
        }

        IntegerRandomValuePicker picker = IntegerRandomValuePicker.integerValuePicker(minValue, maxValue);

        while (remaining.size() > 0) {
            Integer pick = picker.get();
            assertThat(pick, is(greaterThanOrEqualTo(minValue)));
            assertThat(pick, is(lessThanOrEqualTo(maxValue)));

            remaining.remove(pick);
        }
    }

}