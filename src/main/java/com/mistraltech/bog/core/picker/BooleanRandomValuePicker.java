package com.mistraltech.bog.core.picker;

import java.util.Random;
import java.util.function.Supplier;

public final class BooleanRandomValuePicker implements Supplier<Boolean> {
    private Random random = new Random();

    private BooleanRandomValuePicker() {
    }

    public static BooleanRandomValuePicker booleanValuePicker() {
        return new BooleanRandomValuePicker();
    }

    @Override
    public Boolean get() {
        int n = random.nextInt(2);
        return n == 0;
    }
}
