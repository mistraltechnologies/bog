package com.mistraltech.bog.core.picker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

public class BooleanValuePickerTest {

    @Test(timeout = 1000L)
    public void picksBothTrueAndFalseEventually() {
        boolean truePicked = false;
        boolean falsePicked = false;

        BooleanValuePicker picker = BooleanValuePicker.booleanValuePicker();

        while (!(truePicked && falsePicked)) {
            Boolean pick = picker.pick();
            truePicked |= pick;
            falsePicked |= !pick;
        }
    }

    @Test
    public void picksTrueWithApproximateFrequencyOfOneHalf() {
        final int iterations = 10000;
        int trueCount = 0;

        BooleanValuePicker picker = BooleanValuePicker.booleanValuePicker();

        for (int i = 0; i < iterations; i++) {
            if (picker.pick()) {
                trueCount++;
            }
        }

        assertThat(trueCount / (double) iterations, is(closeTo(0.5, 0.1)));
    }
}