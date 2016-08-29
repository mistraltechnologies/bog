package com.mistraltech.bog.core.picker;


import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static com.mistraltech.bog.core.picker.ArrayValuePicker.arrayPicker;
import static com.mistraltech.bog.core.picker.EnumValuePicker.enumPicker;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EnumValuePickerTest {

    @Test(expected = NullPointerException.class)
    public void cannotConstructWithNullEnumClass() {
        enumPicker(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotConstructWithEmptyEnum() {
        enumPicker(X.class);
    }

    @Test(timeout = 1000L)
    public void picksAllValuesFromEnumEventually() {
        EnumValuePicker<Y> picker = enumPicker(Y.class);
        Set<Y> remaining = new TreeSet<>(Arrays.asList(Y.values()));

        while (remaining.size() > 0) {
            remaining.remove(picker.pick());
        }
    }
}

enum X {
}

enum Y {
    A, B, C;
}