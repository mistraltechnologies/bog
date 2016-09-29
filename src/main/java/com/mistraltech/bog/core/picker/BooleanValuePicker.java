package com.mistraltech.bog.core.picker;

import java.util.Random;
import java.util.function.Supplier;

public final class BooleanValuePicker implements Supplier<Boolean> {

    private BooleanValuePicker() {
    }

    public static BooleanValuePicker booleanValuePicker() {
        return new BooleanValuePicker();
    }

    @Override
    public Boolean get() {
        int n = new Random().nextInt(2);
        return n == 0;
    }
}
