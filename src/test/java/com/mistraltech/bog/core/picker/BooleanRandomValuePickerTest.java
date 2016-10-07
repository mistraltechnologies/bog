package com.mistraltech.bog.core.picker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

public class BooleanRandomValuePickerTest {

    @Test(timeout = 1000L)
    public void picksBothTrueAndFalseEventually() {
        boolean truePicked = false;
        boolean falsePicked = false;

        BooleanRandomValuePicker picker = BooleanRandomValuePicker.booleanRandomValuePicker();

        while (!(truePicked && falsePicked)) {
            Boolean pick = picker.get();
            truePicked |= pick;
            falsePicked |= !pick;
        }
    }

    @Test
    public void picksTrueWithApproximateFrequencyOfOneHalf() {
        final int iterations = 10000;
        int trueCount = 0;

        BooleanRandomValuePicker picker = BooleanRandomValuePicker.booleanRandomValuePicker();

        for (int i = 0; i < iterations; i++) {
            if (picker.get()) {
                trueCount++;
            }
        }

        assertThat(trueCount / (double) iterations, is(closeTo(0.5, 0.1)));
    }
}