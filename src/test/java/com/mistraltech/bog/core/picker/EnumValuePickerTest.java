package com.mistraltech.bog.core.picker;


import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static com.mistraltech.bog.core.picker.EnumRandomValuePicker.enumRandomValuePicker;

public class EnumValuePickerTest {

    @Test(expected = NullPointerException.class)
    public void cannotConstructWithNullEnumClass() {
        enumRandomValuePicker(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotConstructWithEmptyEnum() {
        enumRandomValuePicker(X.class);
    }

    @Test(timeout = 1000L)
    public void picksAllValuesFromEnumEventually() {
        EnumRandomValuePicker<Y> picker = enumRandomValuePicker(Y.class);
        Set<Y> remaining = new TreeSet<>(Arrays.asList(Y.values()));

        while (remaining.size() > 0) {
            remaining.remove(picker.get());
        }
    }

    private enum X {
    }

    private enum Y {
        A, B, C
    }
}
