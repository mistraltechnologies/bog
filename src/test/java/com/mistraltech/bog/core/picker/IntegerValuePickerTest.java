package com.mistraltech.bog.core.picker;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class IntegerValuePickerTest {

    @Test(expected = IllegalArgumentException.class)
    public void cannotConstructWithMaxValueLessThanMinValue() {
        IntegerValuePicker.integerValuePicker(2, 1);
    }

    @Test
    public void canConstructWithMinValueEqualToMaxValue() {
        IntegerValuePicker picker = IntegerValuePicker.integerValuePicker(-3, -3);

        assertThat(picker.pick(), is(equalTo(-3)));
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

        IntegerValuePicker picker = IntegerValuePicker.integerValuePicker(minValue, maxValue);

        while(remaining.size() > 0) {
            Integer pick = picker.pick();
            assertThat(pick, is(greaterThanOrEqualTo(minValue)));
            assertThat(pick, is(lessThanOrEqualTo(maxValue)));

            remaining.remove(pick);
        }
    }

}