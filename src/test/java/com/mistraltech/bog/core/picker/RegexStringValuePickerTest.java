package com.mistraltech.bog.core.picker;

import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static com.mistraltech.bog.core.picker.RegexStringValuePicker.regexStringValuePicker;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertTrue;

public class RegexStringValuePickerTest {

    @Test(expected = NullPointerException.class)
    public void cannotConstructWithNull() {
        regexStringValuePicker(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotConstructWithInvalidPattern() {
        regexStringValuePicker("[");
    }

    @Test
    public void picksDifferentValuesMatchingPattern() {
        final int iterations = 10;
        final String pattern = "[a-z]{5}[0-9]{5}";

        RegexStringValuePicker picker = regexStringValuePicker(pattern);
        Set<String> picks = new TreeSet<>();

        for (int i = 0; i < iterations; i++) {
            String pick = picker.get();

            assertTrue(pick.matches(pattern));

            picks.add(pick);
        }

        // Arbitrarily expect that there will be at least (iterations/2) distinct picks
        assertThat(picks.size(), is(greaterThanOrEqualTo(iterations / 2)));
    }
}